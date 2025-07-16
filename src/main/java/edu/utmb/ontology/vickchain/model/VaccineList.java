/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author mac
 */
public enum VaccineList {
    HPV ("http://purl.obolibrary.org/obo/VO_0000667"),
    Gardasil("http://purl.obolibrary.org/obo/VO_0000049"),
    Influenza ("http://purl.obolibrary.org/obo/VO_0000642"),
    MMR ("http://purl.obolibrary.org/obo/VO_0000731");
    
    private String id;
    
    VaccineList(String identifier){
        id = identifier;
    }
    
    public String getId(){
        return id;
    }
    
    public Resource getResource(Model model){
        Resource resource = model.createResource(id);
        
        return resource;
    }
}
