/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

import edu.utmb.ontology.vickchain.iri.ReferenceIRI;
import edu.utmb.ontology.vickchain.iri.ReferenceIRIProperty;
import edu.utmb.ontology.vickchain.model.SampleDataModel;
import edu.utmb.ontology.vickchain.util.IDCounter;
import edu.utmb.ontology.vickchain.util.ImportData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author mac
 */
public class VICKManager {

    public static final String NAME_SPACE = "http://purl.org/utmb/vick-lite.ttl#";
    
    public static String temp_url = "http://purl.org/utmb/vick-lite.ttl";

    private static VICKManager INSTANCE = null;

    private Model model = null;

    private Model nt_model_export = null;

    private VICKManager() {

        model = ModelFactory.createDefaultModel();

    }

    public void readFile(String file_name) {

        model.read(file_name, "TTL");

        //model.write(System.out, "NT");
    }

    public static VICKManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new VICKManager();
        }

        return INSTANCE;

    }

    public void createNTExport() {
        
        
        ImportData instance = ImportData.getInstance();
        ArrayList<SampleDataModel> sampleData = instance.getSampleData();

        //patient
        IDCounter id_counter = IDCounter.getInstance();

        for (SampleDataModel sample : sampleData) {
            
            nt_model_export = ModelFactory.createDefaultModel();

            String patient_id = id_counter.getLatestIdentifier();

            String patient_fn = sample.first_name;

            String patient_ln = sample.last_name;

            
            //create vaccination patient node with type
            Resource patient_node = nt_model_export.createResource(NAME_SPACE +patient_id);
            Resource patient_type = model.getResource(ReferenceIRI.VACCINE_PATIENT);
            patient_node.addProperty(RDF.type, patient_type);
            
            //create family name node with type
            Resource family_name_node = nt_model_export.createResource(NAME_SPACE + id_counter.getLatestIdentifier());
            Resource f_n_type = nt_model_export.createResource(ReferenceIRI.FAMILY_NAME);
            family_name_node.addProperty(RDF.type, f_n_type);
            
            //link vaccination patient node with family name node using dentoed by
            Property denoted_by = model.getProperty(ReferenceIRIProperty.DENOTED_BY);
            patient_node.addProperty(denoted_by, family_name_node);
            
            
            //link patient family name with family name node
            family_name_node.addLiteral(RDFS.label, patient_ln);
            
            
            
            nt_model_export.write(System.out, "NT");
            
            try 
            {
                nt_model_export.write(new FileWriter( sample.id+".nt" ), "NT");
            } catch (IOException ex) {
                Logger.getLogger(VICKManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("---------------------\n");
             
            
        }
        
       

    }

    public static void main(String[] args) {

        VICKManager vm = new VICKManager();

        vm.readFile(temp_url);

        vm.createNTExport();
    }

}
