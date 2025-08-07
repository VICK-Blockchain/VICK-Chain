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
    
    
    int difficulty_value = 5;
    
    private LinkedList<Block> VICK_Chain;
    
    public VICKChain(){
        VICK_Chain = new LinkedList<Block>();
    }
    
    public void insertBlock(Block block){
        
        VICK_Chain.add(mineBlock(block));
    }
    
    
    private Block mineBlock(Block block){
        
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
    
    public static void main(String[] args) {
        
    }
    
}
