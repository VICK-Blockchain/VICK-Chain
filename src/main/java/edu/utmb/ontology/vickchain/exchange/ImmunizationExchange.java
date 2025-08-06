/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.exchange;

import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.LinkedList;

/**
 *
 * @author tuan
 */
public class ImmunizationExchange {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    private LinkedList<String> immunization_record;
    
    public ImmunizationExchange(LinkedList <String> imm_record){
        this.produceKeys();
        
    }
    
    private void produceKeys(){
        
        CryptUtil util = CryptUtil.getInstance();
        KeyPair keys = util.produceKeys();
        privateKey = keys.getPrivate();
        publicKey = keys.getPublic();
        
    }
    
    public String generateRDFModel(){
        return null;
    }
    
    
}
