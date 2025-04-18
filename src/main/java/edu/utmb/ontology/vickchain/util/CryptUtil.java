/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.util;

import edu.utmb.ontology.vickchain.model.ImmunizationRecording;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author tuan
 */
public class CryptUtil {
    
    private static CryptUtil INSTANCE = null;
    
    private CryptUtil(){
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public synchronized static CryptUtil getInstance(){
        
        if(INSTANCE == null){
            
            INSTANCE = new CryptUtil();
        }
        
        
        return INSTANCE;
    }
    
    public String hashTransform(LinkedList<ImmunizationRecording> documents){
        String input_data ="";
        
        for(int i=0; i<documents.size();i++){
            ImmunizationRecording ex = documents.get(i);
        }
        
        String input = input_data; //TODO add additional... previous hash, timedate, index
        
        return convertSHA256(input);
    }
    
    public String convertSHA256(String information){
        
        StringBuffer hex_result = new StringBuffer();
        
        try {
            
            MessageDigest message_digest = MessageDigest.getInstance("SHA-256");
            
            byte[] hash_byte = message_digest.digest(information.getBytes("UTF-8"));
            
            for(int i=0; i < hash_byte.length; i++){
                String hex_string = Integer.toHexString(0xff & hash_byte[i]);
                
                if(hex_string.length() == 1){
                    hex_result.append('0');
                }
                
                hex_result.append(hex_string);
            }
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        return hex_result.toString();
    }
    
    public KeyPair produceKeys(){
        KeyPair key_pairs = null;
        
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDSA","BC");
            
            key_pairs = generator.genKeyPair();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(CryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return key_pairs;
        
    }
    
}
