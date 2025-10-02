/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.util;

import edu.utmb.ontology.vickchain.exchange.ImmunizationExchange;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
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
    
    public byte[] signImmunizationRecordData(PrivateKey private_key, String input) {
        
        byte[] output = new byte[0];
        
        try {
            Signature signature = Signature.getInstance("ECDSA", "BC");
            
            signature.initSign(private_key);
            
            byte[] string_bytes = input.getBytes();
            
            signature.update(string_bytes);
            
            byte[] byte_signature = signature.sign();
            
            output = byte_signature;
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return output;
    }
    
    public boolean legitSignedImmunizationRecord(PublicKey publicKey, String data, byte[] byte_signature) {
        
        boolean is_legit = false;
        
        try {
            Signature signature = Signature.getInstance("ECDSA", "BC");
            
            signature.initVerify(publicKey);
            
            signature.update(data.getBytes());
            
            is_legit = signature.verify(byte_signature);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return is_legit;
    }
    
    public synchronized static CryptUtil getInstance(){
        
        if(INSTANCE == null){
            
            INSTANCE = new CryptUtil();
        }
        
        
        return INSTANCE;
    }
    
    /*
    public String hashTransform(LinkedList<ImmunizationExchange> documents){
        String input_data ="";
        
        for(int i=0; i<documents.size();i++){
            ImmunizationExchange ex = documents.get(i);
        }
        
        String input = input_data; //TODO add additional... previous hash, timedate, index
        
        return convertSHA256(input);
    }
    */
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
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
	    ECGenParameterSpec params = new ECGenParameterSpec("secp192k1");
            generator.initialize(params, secureRandom);
            
            key_pairs = generator.genKeyPair();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(CryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(CryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return key_pairs;
        
    }
    
}
