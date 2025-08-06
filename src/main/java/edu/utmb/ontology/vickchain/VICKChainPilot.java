package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.exchange.ImmunizationRecord;
import edu.utmb.ontology.vickchain.model.Block;
import edu.utmb.ontology.vickchain.ontology.VICKManagerSynth;
import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 */
public class VICKChainPilot {
    
    private CryptUtil crypto_utility = null;
    
    final private String ORIGIN_HASH = "000000000000000000000000000000000000000000000000000000000000000";
    
    private VICKManagerSynth synthPatientRecords = null;
    
    private ArrayList<Block> vick_chain = new ArrayList<Block>();
    
    private LinkedList<String> synthData = null;
    
    private LinkedList<ImmunizationRecord> records = null;
    
    public VICKChainPilot(){
        
        synthPatientRecords = VICKManagerSynth.getInstance();
        synthPatientRecords.createNTExport();
        
        crypto_utility = CryptUtil.getInstance();
        
        records = new LinkedList<ImmunizationRecord>();
        
    }
    
    public void signIndividualImmunizationRecord(){
        
        synthData = synthPatientRecords.getSynthData();
        
        Agent agent1 = new Agent();
        Agent agent2 = new Agent();
        
        PrivateKey privateKey = agent2.getPrivateKey();
        PublicKey publicKey = agent1.getPublicKey();
        
        System.out.println("private key: " + privateKey);
        System.out.println("public key: " + publicKey);
        
        
        
    }
    
    //validate chain
    
    //validate block
    
    public static void main(String[] args) {
        
        VICKChainPilot sim = new VICKChainPilot();
        
        sim.signIndividualImmunizationRecord();
        
        
    }
}
