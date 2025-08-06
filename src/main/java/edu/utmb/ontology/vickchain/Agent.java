/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author mac
 */
public class Agent {
    
    private PrivateKey private_key;
    private PublicKey public_key;
    
    private CryptUtil crypt_util = null;
    
    public Agent(){
        crypt_util = CryptUtil.getInstance();
        
        KeyPair key_pair = crypt_util.produceKeys();
        
        private_key = key_pair.getPrivate();
        public_key = key_pair.getPublic();
        
    }
    
    public PrivateKey getPrivateKey(){
        return private_key;
    }
    
    public PublicKey getPublicKey(){
        return public_key;
    }
    
    public static void main(String[] args) {
        
    }
    
}
