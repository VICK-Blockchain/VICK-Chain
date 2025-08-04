/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

import edu.utmb.ontology.vickchain.model.SynthDataModel;
import edu.utmb.ontology.vickchain.util.ImportSynthData;
import java.io.FileOutputStream;
import java.io.IOException;
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
    
    private final String path_file = "/Users/mac/Desktop/SyntheticData+ID.xlsx";
    
    private Model model = null;

    
    private VICKManagerSynth() {
       
        model = ModelFactory.createDefaultModel();
        
    }
    
    public static VICKManagerSynth getInstance() {
        return VICKManagerSynthHolder.INSTANCE;
    }
    
    private static class VICKManagerSynthHolder {

        private static final VICKManagerSynth INSTANCE = new VICKManagerSynth();
    }
    
    public void createNTExport(){
        ImportSynthData instance = ImportSynthData.getInstance();
        instance.readExcelSpreadSheet(path_file);
        Set<SynthDataModel> synthData = instance.getSynthData();
        
        
        System.out.println(synthData.size());
        
        int i = 1;
        for(SynthDataModel dm : synthData){
            model = ModelFactory.createDefaultModel();
            dm.initModel(model);
            //System.out.println("\n\n=========================\n");
            try {
                RDFDataMgr.write(new FileOutputStream(i + ".nt"), model, Lang.NT);
                i++;
             
            } catch (IOException ex) {
                Logger.getLogger(VICKManagerSynth.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        }
        
    }
    
    public static void main(String[] args) {
        
        VICKManagerSynth v = VICKManagerSynth.getInstance();
        
        v.createNTExport();
    }
}
