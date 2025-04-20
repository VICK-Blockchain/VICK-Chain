/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.ontology;

/**
 *
 * @author mac
 */
public class VICKManager {
    
    private static VICKManager INSTANCE = null;
    
    private VICKManager(){
        
    }
    
    public static VICKManager getInstance(){
        
        if(INSTANCE == null){
            INSTANCE = new VICKManager();
        }
        
        return INSTANCE;
        
    }
    
}
