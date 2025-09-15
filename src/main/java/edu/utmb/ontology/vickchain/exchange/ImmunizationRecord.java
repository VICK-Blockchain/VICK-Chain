/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.exchange;

import java.security.PublicKey;

/**
 * PENDING: Delete file
 * @author mac
 */
public class ImmunizationRecord {
    
   private PublicKey sender_public_key;
   private PublicKey receiver_public_key;
   private byte[] byte_data;
    
   public ImmunizationRecord(byte [] byte_data, PublicKey sender, PublicKey receiver){
       this.byte_data = byte_data;
       this.sender_public_key = sender;
       this.receiver_public_key = receiver;
   }
    
 
}
