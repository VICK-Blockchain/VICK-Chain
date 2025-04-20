/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mac
 */
public class SampleDataModel {
    
    public int id;
    public String first_name;
    public String last_name;
    public String postal_address;
    public String postal_code;
    public String email;
    public String biological_sex;
    public Set<String> vaccines;
    
    public SampleDataModel(){
        vaccines = new HashSet<String>();
    }
    
    @Override
    public String toString(){
        
        StringBuilder data = new StringBuilder();
        
        data.append("\nid : " + this.id);
        data.append("\nfirst name: " + this.first_name);
        data.append("\nlast name: " + this.last_name);
        data.append("\npostal address: " + this.postal_address);
        data.append("\npostal code: " + this.postal_code);
        data.append("\nemail: " + this.email);
        data.append("\nbiological sex: " + this.biological_sex);
        data.append("\nvaccines: " + vaccines.toString());
        
        return data.toString();
        
    }
    
}
