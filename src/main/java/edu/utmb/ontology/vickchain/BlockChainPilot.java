package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.ontology.VICKManagerSynth;
import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.util.LinkedList;

/**
 * Hello world!
 */
public class BlockChainPilot {
    
    private CryptUtil crypto_utility = null;
    
    final private String ORIGIN_HASH = "000000000000000000000000000000000000000000000000000000000000000";
    
    private VICKManagerSynth synthPatientRecords = null;
    
    public BlockChainPilot(){
        
        synthPatientRecords = VICKManagerSynth.getInstance();
        synthPatientRecords.createNTExport();
        
        crypto_utility = CryptUtil.getInstance();
        
        
        
    }
    
    public void signIndividualImmunizationRecord(){
        
        LinkedList<String> synthData = synthPatientRecords.getSynthData();
        
        for(String d : synthData){
            
        }
        
    }
    
    //validate chain
    
    //validate block
    
    public static void main(String[] args) {
        
        BlockChainPilot sim = new BlockChainPilot();
        
        sim.signIndividualImmunizationRecord();
        
        
    }
}
