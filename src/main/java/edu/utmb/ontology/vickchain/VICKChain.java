/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.model.Block;
import java.util.LinkedList;

/**
 *
 * @author mac
 */
public class VICKChain {
    
    final private String ORIGIN_HASH = "000000000000000000000000000000000000000000000000000000000000000";
    
    int difficulty_value = 5;
    
    private LinkedList<Block> VICK_Chain;
    
    public VICKChain(){
        VICK_Chain = new LinkedList<Block>();
    }
    
    public void insertBlock(Block block){
        
        VICK_Chain.add(block);
    }
    public Block getLastBlock(){
        
        if(VICK_Chain.size() ==0) return null;
        
        return VICK_Chain.getLast();
    }
    
    public String getLastBockHash(){
        if(VICK_Chain.size() ==0) return ORIGIN_HASH;
        
        return VICK_Chain.getLast().getCurrentHashData();
    }
    
    public Block mineBlock(Block block){
        
        System.out.println("mining...");
       
        
        String target_challenge = new String(new char[difficulty_value]).replace('\0', '0');
        
        while(!block.getHashData().substring(0, difficulty_value).equals(target_challenge)){
            //nonce++;
            block.incrementNonce();
            String calculateHash = block.calculateHash();
            System.out.println(calculateHash);
            block.setHash(calculateHash);
            
        }
        
        
        return block;
        
    }
    
    //TODO: Validate Chain
    private void validateChain(){
        
        if(VICK_Chain.size() == 1) return;
        
        String target_hash = "00000";
        
        for(Block b : VICK_Chain){
            
        }
        
        
    }
    
    public static void main(String[] args) {
        
    }
    
}
