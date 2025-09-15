/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.exchange;

import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author tuan
 */
public class ImmunizationExchange implements Serializable {
   
   private String exchange_id; 
    
   private PublicKey sender_public_key;
   private PublicKey receiver_public_key;
    
   private String record;
   
   private byte [] signature;
   
   
   
   public ImmunizationExchange(PublicKey sender, PublicKey receiver, String data){
       
       
       this.sender_public_key = sender;
       this.receiver_public_key = receiver;
       this.record = data;
       
       //String uuid = UUID.randomUUID().toString();
       
       
       exchange_id = CryptUtil.getInstance().convertSHA256(this.getData());
       
   }
           
   private String getData(){
       
       String result = this.sender_public_key.toString() + this.receiver_public_key.toString() + this.record;
       return result;
   }
   
   public void signRecord(PrivateKey privateKey){
       this.signature = CryptUtil.getInstance().signImmunizationRecordData(privateKey, this.getData());
   }
   
   //TODO:Signature Verification
   
   /*
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    private LinkedList<String> immunization_records;
    
  
    
    public ImmunizationExchange(String record_data, PublicKey sender, PublicKey receiver, LinkedList<String> immunization_records){
        
        record = record_data;
        sender_public_key = sender;
        receiver_public_key = receiver;
        this.immunization_records = immunization_records;
        
        
        
    }
    
    public ImmunizationExchange(String record_data, PublicKey sender, PublicKey receiver){
        
        record = record_data;
        sender_public_key = sender;
        receiver_public_key = receiver;
        
    }
    
    private void produceKeys(){
        
        CryptUtil util = CryptUtil.getInstance();
        KeyPair keys = util.produceKeys();
        privateKey = keys.getPrivate();
        publicKey = keys.getPublic();
        
    }

    public LinkedList<String> getImmunization_records() {
        return immunization_records;
    }
    */
    public String generateRDFModel(){
        return null;
    }

    public PublicKey getSender_public_key() {
        return sender_public_key;
    }

    public void setSender_public_key(PublicKey sender_public_key) {
        this.sender_public_key = sender_public_key;
    }

    public PublicKey getReceiver_public_key() {
        return receiver_public_key;
    }

    public void setReceiver_public_key(PublicKey receiver_public_key) {
        this.receiver_public_key = receiver_public_key;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
    
    
}
