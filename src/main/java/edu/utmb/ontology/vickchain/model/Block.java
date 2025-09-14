/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import edu.utmb.ontology.vickchain.exchange.ImmunizationExchange;
import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 *
 * @author mac
 */
public class Block {
    
    //final private String ORIGIN_HASH = "000000000000000000000000000000000000000000000000000000000000000";
    
    private int nonce;
    
    private String datetime;
    
    private String hash;
    private String previous_hash;
    
    private long index;
    
    private LinkedList<ImmunizationExchange> records;
    
    public Block(long index, String previous_hash, LinkedList <ImmunizationExchange> records_input)
    {
        
        this.index = index;
        this.datetime = this.generateDateTime();
        this.previous_hash = previous_hash;
       
        this.records = new LinkedList<ImmunizationExchange>();
        this.records.addAll(records_input);
        
        
        this.hash = calculateHash();
        
    }
    
    
    
    public void addImmunizationRecord(ImmunizationExchange immunization_exchange){
        
        //check if it is origin block or verified, otherwise return
        
        records.add(immunization_exchange);
        
    }
    
    public void incrementNonce(){
        nonce++;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String calculateHash(){
       CryptUtil util = CryptUtil.getInstance();
       
       //String hash_result;
       
       
        String hash_result = util.convertSHA256(Long.toString(index) + previous_hash + datetime + records.toString() + Integer.toString(nonce));
       
       //String hash_result = util.hashTransform(records);
       
       return hash_result;
    }
    
    public String getHashData(){
        
        return this.hash;
    }
    
    public String getPreviousHashData(){
        return this.previous_hash;
    }
    
    public String getCurrentHashData(){
        return this.hash;
    }
    
    private String generateDateTime(){
        return LocalDateTime.now().toString();
    }
    
    
    
}
