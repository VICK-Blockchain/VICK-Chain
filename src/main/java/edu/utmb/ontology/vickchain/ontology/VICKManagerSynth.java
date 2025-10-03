/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

import edu.utmb.ontology.vickchain.model.PatientVaccinationModel;
import edu.utmb.ontology.vickchain.model.SynthDataModel;
import edu.utmb.ontology.vickchain.util.ImportSynthData;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

/**
 *
 * @author mac
 */
public class VICKManagerSynth extends VICKEncoderImpl{
    
    
    public static final String VICK_NAME_SPACE = "http://purl.org/vick/vick.owl";
    
    private final String path_file = "/Users/mac/Library/CloudStorage/OneDrive-UTHealthHouston/SyntheticData+ID.xlsx";
    
    private Model model = null;
    
    private LinkedList<String> vick_synth_data = null;
    private Set<SynthDataModel> synthData = null;
    
    private VICKManagerSynth() {
       
        model = ModelFactory.createDefaultModel();
        
    }
    
    public Set<SynthDataModel> getSynthData(){
        return this.synthData;
    }
    
    public LinkedList<String> getSynthDataNT(){
        return vick_synth_data;
    }
    
    public static VICKManagerSynth getInstance() {
        return VICKManagerSynthHolder.INSTANCE;
    }
    
    private static class VICKManagerSynthHolder {

        private static final VICKManagerSynth INSTANCE = new VICKManagerSynth();
    }
    
    public void createNTExport(){
        
        vick_synth_data  = new LinkedList<String>();
        
        ImportSynthData instance = ImportSynthData.getInstance();
        instance.readExcelSpreadSheet(path_file);
        synthData = instance.getSynthData();
        
        
        System.out.println(synthData.size());
        
        int i = 1;
        for(SynthDataModel dm : synthData){
            model = ModelFactory.createDefaultModel();
            dm.initModel(model);
          
            //RDFDataMgr.write(new FileOutputStream(i + ".nt"), model, Lang.NT);
            
            //
            ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();
            model.write(byte_stream, "NT");
            vick_synth_data.add(byte_stream.toString(Charset.forName("UTF-8")));
            i++;
            
           
        }
        
    }
    
    public void parseIndividualNTData(){
       
        
        for(String nt: vick_synth_data){
            //parse_model.read(IOUtils.toInputStream(nt, "UTF-8"), null, "N-TRIPLES");
            
            //StmtIterator listStatements = parse_model.listStatements();
            
            //System.out.println(nt + "\n========================================");
            extractProviderResource(nt);
            //parse_model.close();
        }
        
        
        //parse_model.read(IOUtils.toInputStream(path_file, charset))
        //model.read(IOUtils.)
    }
    
    private void extractProviderResource( String nt){
        
        String query = "SELECT ?subject ?predicate ?object\n" +
"WHERE {\n" +
"?subject ?predicate ?object\n" +
"}";
        
        
         Model parse_model = ModelFactory.createDefaultModel();
         
         parse_model.read(IOUtils.toInputStream(nt, "UTF-8"), null, "N-TRIPLES");
         
         Query _query = QueryFactory.create(query);
         
        QueryExecution qe = QueryExecutionFactory.create(_query, parse_model);
        
        ResultSet rs = qe.execSelect();
        
        while(rs.hasNext()){
            
            QuerySolution qs = rs.nextSolution();
            
            RDFNode node = qs.get("subject");
            
            
            
            System.out.println(node.toString());
            
        }
         qe.close();
    }
    
    public static void main(String[] args) {
        
        VICKManagerSynth v = VICKManagerSynth.getInstance();
        
        v.createNTExport();
        
        v.parseIndividualNTData();
        
       
    }
}
