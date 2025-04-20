/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author mac
 */
public class IDCounter {
    
    private final AtomicInteger id_counter = new AtomicInteger(0);
    
    private final String PREFIX = "VICK_";
    
    private String latest_identifier = "";
    
    public IDCounter(){
        
    }
    
    private void incrementCounter(){
        id_counter.incrementAndGet();
    }
    
    public String getIdentifierCounter(){
        String identifier = String.format("%07d", id_counter);
        
        return PREFIX + identifier;
        
    }
    
    public String getLatestIdentifier(){
        
        this.incrementCounter();
        
        latest_identifier = this.getLatestIdentifier();
        
        return latest_identifier;
        
    }
    
    
    
}
