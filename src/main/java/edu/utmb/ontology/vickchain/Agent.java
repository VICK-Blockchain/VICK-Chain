/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 *
 * @author mac
 */
public abstract class Agent {
    
    private String identifier;
    
    //PENDING: presuming that might be multiple labels
    private ArrayList labels;
    
    private PrivateKey private_key;
    private PublicKey public_key;
    
    private CryptUtil crypt_util = null;
    
    public Agent(){
        crypt_util = CryptUtil.getInstance();
        
        KeyPair key_pair = crypt_util.produceKeys();
        
        private_key = key_pair.getPrivate();
        public_key = key_pair.getPublic();
        
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public PrivateKey getPrivateKey(){
        return private_key;
    }
    
    public PublicKey getPublicKey(){
        return public_key;
    }

    public ArrayList getLabels() {
        return labels;
    }

    public void setLabels(ArrayList labels) {
        
        if(labels == null){
            labels = new ArrayList();
        }
        
        this.labels = labels;
    }
    
    public static void main(String[] args) {
        
    }
    
}
