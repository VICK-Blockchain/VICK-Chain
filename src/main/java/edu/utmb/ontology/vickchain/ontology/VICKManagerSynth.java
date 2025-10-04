/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import edu.utmb.ontology.vickchain.model.SynthDataModel;
import edu.utmb.ontology.vickchain.util.ImportSynthData;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.jena.iri.IRI;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

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
    
    private Multimap<String, String> patients = null;
    
    private Multimap<String, String> providers = null;
    
    private VICKManagerSynth() {
       
        model = ModelFactory.createDefaultModel();
        
    }
    
    public Multimap<String, String> getProviders(){
        return this.providers;
    }
    
    public Multimap<String, String> getPatients(){
        return this.patients;
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
            
            //System.out.println(nt);
           
            providers = extractProviderResource(nt);
            
            patients = extractPatientResource(nt);
            
        }

    }
    
    private Multimap<String, String> extractProviderResource(String nt) {

        Multimap<String, String> provider_collection = ArrayListMultimap.create();

        String query
                = "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX obo: <http://purl.obolibrary.org/obo/>\n"
                + "SELECT ?subject ?label \n"
                + "WHERE {\n"
                + "?subject rdf:type <http://purl.org/vick/vick.owl#VICK_0000224>  . \n "
                + "?subject rdfs:label ?label . \n"
                + "}";

        Model parse_model = ModelFactory.createDefaultModel();

        parse_model.read(IOUtils.toInputStream(nt, "UTF-8"), null, "N-TRIPLES");

        Query _query = QueryFactory.create(query);

        QueryExecution qe = QueryExecutionFactory.create(_query, parse_model);

        ResultSet rs = qe.execSelect();

        while (rs.hasNext()) {

            QuerySolution qs = rs.nextSolution();

            Resource subject = qs.getResource("subject");
            Literal label = qs.getLiteral("label");

            provider_collection.put(subject.getURI(), label.getString());

        }
        qe.close();

        return provider_collection;
    }

    private Multimap<String, String> extractPatientResource(String nt) {

        Multimap<String, String> patient_collection = ArrayListMultimap.create();

        String query
                = "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX obo: <http://purl.obolibrary.org/obo/>\n"
                + "SELECT ?subject ?label \n"
                + "WHERE {\n"
                + "?subject rdf:type <http://purl.obolibrary.org/obo/VICO_0000016>  . \n "
                + "?subject rdfs:label ?label . \n"
                + "}";

        Model parse_model = ModelFactory.createDefaultModel();

        parse_model.read(IOUtils.toInputStream(nt, "UTF-8"), null, "N-TRIPLES");

        Query _query = QueryFactory.create(query);

        QueryExecution qe = QueryExecutionFactory.create(_query, parse_model);

        ResultSet rs = qe.execSelect();

        while (rs.hasNext()) {

            QuerySolution qs = rs.nextSolution();

            Resource subject = qs.getResource("subject");
            Literal label = qs.getLiteral("label");

            patient_collection.put(subject.getURI(), label.getString());

        }

        return patient_collection;
    }

    public static void main(String[] args) {
        
        VICKManagerSynth v = VICKManagerSynth.getInstance();
        
        v.createNTExport();
        
        v.parseIndividualNTData();
        
       
    }
}
