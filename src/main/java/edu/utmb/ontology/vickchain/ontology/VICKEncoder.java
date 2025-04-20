/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author tuan
 */
public interface VICKEncoder  {
    
    public void saveDataAsNT();
    public void encodePatientResource();
    public void encodePatientFamilyName();
    public void encodePatientGivenName();
    public void encodeLinkBetweenPatientAndNames(Resource patient, Resource given_name, Resource family_name);
    
}
