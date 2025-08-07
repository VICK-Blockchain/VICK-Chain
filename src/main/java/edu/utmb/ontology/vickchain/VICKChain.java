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
    
    private LinkedList<Block> VICK_Chain;
    
    public VICKChain(){
        VICK_Chain = new LinkedList<Block>();
    }
    
    public void insertBlock(Block block){
        VICK_Chain.add(block);
    }
    
    
    private void mineBlock(Block block){
        
    }
    
    public static void main(String[] args) {
        
    }
    
}
