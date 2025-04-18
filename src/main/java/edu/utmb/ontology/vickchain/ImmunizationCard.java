/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.model.Block;
import edu.utmb.ontology.vickchain.model.ImmunizationRecording;
import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.LinkedList;

/**
 *
 * @author mac
 */
public class ImmunizationCard {
    
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    private LinkedList<Block> vickchain;
    
    public ImmunizationCard(LinkedList <Block> vick_chain){
        
        this.produceKeys();
        
        this.vickchain = new LinkedList<Block>();
        
        this.vickchain.addAll(vick_chain);
        
        
        
    }
    
    private void produceKeys(){
        
        CryptUtil util = CryptUtil.getInstance();
        KeyPair keys = util.produceKeys();
        privateKey = keys.getPrivate();
        publicKey = keys.getPublic();
        
    }
    
    public ImmunizationRecording record(String receiver){
        
        //DocumentImmunization di = new Dcou
        
        return null;
    }
    
}
