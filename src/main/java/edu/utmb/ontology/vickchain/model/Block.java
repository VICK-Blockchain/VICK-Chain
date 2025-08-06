/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import edu.utmb.ontology.vickchain.exchange.ImmunizationRecording;
import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 *
 * @author mac
 */
public class Block {
    
    private String datetime;
    
    private String hash;
    private String previous_hash;
    
    private long index;
    
    private LinkedList<ImmunizationRecording> records;
    
    
    
    public Block(long index, String previous_hash, LinkedList <ImmunizationRecording> records_input)
    {
        
        this.index = index;
        this.datetime = this.generateDateTime();
        this.previous_hash = previous_hash;
       
        this.records = new LinkedList<ImmunizationRecording>();
        this.records.addAll(records_input);
        
        
        this.hash = calculateHash();
        
    }
    
    public String calculateHash(){
       CryptUtil util = CryptUtil.getInstance();
        
        
       String hash_result = util.hashTransform(records);
       
       return hash_result;
    }
    
    private String generateDateTime(){
        return LocalDateTime.now().toString();
    }
    
    
    
}
