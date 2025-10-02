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
    
    public String getORIGIN_HASH(){
        return this.ORIGIN_HASH;
    }
    
    public void insertBlock(Block block){
        
        VICK_Chain.add(block);
    }
    public Block getLastBlock(){
        
        if(VICK_Chain.size() ==0) return null;
        
        return VICK_Chain.getLast();
    }
    
    public Block getFirstBlock(){
        return VICK_Chain.getFirst();
    }
    
    public LinkedList<Block> getVICKChain(){
        
        return this.VICK_Chain;
        
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
    
    //XXX: Validate Chain
    private boolean validateVICK_Chain(VICKChain vickchain){
        
        if(!isVICK_BlockValid(vickchain.getFirstBlock(),null)){
            return false;
        }
        
        /*
        for(Block block : vickchain.getVICKChain()){
            
        }
        */
        
        for(int b = 1; b < vickchain.getVICKChain().size(); b++){
            
            Block current = vickchain.getVICKChain().get(b);
            Block previous = vickchain.getVICKChain().get(b-1);
            
            if(!isVICK_BlockValid(current, previous)){
                return false;
            }
            
        }
        
        return true;
    }
    
    
    private boolean isVICK_BlockValid(Block currentBlock, Block previousBlock){
        //String target_hash = "00000";
        
        //PENDING: Consider checking if the hash has been mined
        
        if(previousBlock == null) //origin block
        {
            
            //conditions: 1) origin block must have property of previous hash of null; 2) origin block must have a property of current block have a value
            // 3) caluclated hash is the same
            
            if(currentBlock.getPreviousHashData() != null){
                return false;
            }
            
            if(currentBlock.getCurrentHashData() == null){
                return false;
            }
            
            if(!currentBlock.calculateHash().equals(currentBlock.getCurrentHashData())){
                return false;
            }
            
            return true;
            
            
        }
        else if (currentBlock != null)
        {
            //conditions: 1) current block has a previous hash data 2) current block's previous hash data is equal to previous block's hash data
            // 3) current block has its own hash data 4) current block's caluclate hash is the same as current hash.
            
            if(currentBlock.getPreviousHashData() == null) return false;
            
            if(!currentBlock.getPreviousHashData().equals(previousBlock.getCurrentHashData())) return false;
            
            if(currentBlock.getCurrentHashData() == null) return false;
            
            if(!currentBlock.calculateHash().equals(currentBlock.getCurrentHashData())) return false;
            
            return true;
            
        }
        
        
        return false; //default false (something must be wrong)
    }
    
    public static void main(String[] args) {
        
    }
    
}
