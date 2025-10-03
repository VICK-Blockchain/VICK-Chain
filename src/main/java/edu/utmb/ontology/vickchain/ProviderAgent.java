/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.iri.ReferenceIRIVaccination.VaccineAdministrator;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author mac
 */
public class ProviderAgent extends Agent {
    
    private Model resourceModel;
    
    private Resource vaccine_administrator;
    
    public ProviderAgent(Model resourceModel, Resource vaccine_administrator_node){
        
        this.resourceModel = resourceModel;
        
        this.vaccine_administrator = vaccine_administrator_node;
    }
    
    @Override
    public String toString(){
        
        
       return this.vaccine_administrator.toString();
        
    }
}
