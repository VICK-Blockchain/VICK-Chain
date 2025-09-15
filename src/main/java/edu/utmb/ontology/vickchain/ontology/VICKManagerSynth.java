/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

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
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
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

    
    private VICKManagerSynth() {
       
        model = ModelFactory.createDefaultModel();
        
    }
    
    public LinkedList<String> getSynthData(){
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
        Set<SynthDataModel> synthData = instance.getSynthData();
        
        
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
    
    public static void main(String[] args) {
        
        VICKManagerSynth v = VICKManagerSynth.getInstance();
        
        v.createNTExport();
    }
}
