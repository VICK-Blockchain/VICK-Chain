/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

import edu.utmb.ontology.vickchain.iri.ReferenceIRI;
import edu.utmb.ontology.vickchain.iri.ReferenceIRIProperty;
import static edu.utmb.ontology.vickchain.ontology.VICKManager.NAME_SPACE;
import edu.utmb.ontology.vickchain.util.IDCounter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author mac
 */
public abstract class VICKEncoderImpl implements VICKEncoder {

    @Override
    public void saveDataAsNT(Model exportModel, String file_name) {

        try {
            exportModel.write(new FileWriter(file_name + ".nt"), "NT");
        } catch (IOException ex) {
            Logger.getLogger(VICKEncoderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void encodePatientVaccineResource() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resource encodePatientResource(Model exportModel) {
        IDCounter idcounter = IDCounter.getInstance();

        Resource patient_node = exportModel.createResource(NAME_SPACE + idcounter.getLatestIdentifier());
        Resource patient_type = exportModel.getResource(ReferenceIRI.VACCINE_PATIENT);
        patient_node.addProperty(RDF.type, patient_type);
        
        return patient_node;

    }

    @Override
    public Resource encodePatientFamilyName(Model exportModel, String label) {

        IDCounter idcounter = IDCounter.getInstance();

        Resource family_name_node = exportModel.createResource(NAME_SPACE + idcounter.getLatestIdentifier());
        Resource f_n_type = exportModel.createResource(ReferenceIRI.GIVEN_NAME);
        family_name_node.addProperty(RDF.type, f_n_type);
        
        family_name_node.addLiteral(RDFS.label, label);
        
        return family_name_node;

    }

    @Override
    public Resource encodePatientGivenName(Model exportModel, String label) {
        
        IDCounter idcounter = IDCounter.getInstance();

        Resource given_name_node = exportModel.createResource(NAME_SPACE + idcounter.getLatestIdentifier());
        Resource f_n_type = exportModel.createResource(ReferenceIRI.FAMILY_NAME);
        given_name_node.addProperty(RDF.type, f_n_type);
        
        given_name_node.addLiteral(RDFS.label, label);
        
        return given_name_node;
    }

    @Override
    public void encodeLinkBetweenPatientAndNames(Property denoted_by, Resource patient, Resource given_name, Resource family_name) {
        patient.addProperty(denoted_by, given_name);
        patient.addProperty(denoted_by, family_name);
        
        
    }

}
