/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

import java.io.FileOutputStream;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;

/**
 *
 * @author tuan
 */
public interface VICKEncoder  {
    
    public void saveData(FileOutputStream fos, Model model, Lang langformat);
    public void saveDataAsNT(Model exportModel, String file_name);
    public Resource encodePatientResource(Model exportModel);
    public Resource encodePatientFamilyName(Model exportModel, String label);
    public Resource encodePatientGivenName(Model exportModel, String label);
    public void encodeLinkBetweenPatientAndNames(Property denoted_by, Resource patient, Resource given_name, Resource family_name);
    public void encodePatientVaccineResource();
    
}
