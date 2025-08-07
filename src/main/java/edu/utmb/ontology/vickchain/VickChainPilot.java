package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.exchange.ImmunizationExchange;
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
    
    private VICKChain vick_chain;
        
    private LinkedList<ImmunizationExchange> records = null;
    
    public VICKChainPilot(){
        
        vick_chain = new VICKChain();
        
        synthPatientRecords = VICKManagerSynth.getInstance();
        synthPatientRecords.createNTExport();
        
        crypto_utility = CryptUtil.getInstance();
        
        records = new LinkedList<ImmunizationExchange>();
        
    }
    
    public LinkedList<String> getSyntheticData(){
        
        return synthPatientRecords.getSynthData();
    }

    public LinkedList<ImmunizationExchange> getRecords() {
        return records;
    }
    
    private void addNewBlock (LinkedList<ImmunizationExchange> values){
        
        Block block = new Block(0, null, values);
        
        //mineblock
        
        vick_chain.insertBlock(block);
        
    }

    public ImmunizationExchange transmitImmunizationRecord(Agent sender, Agent receiver, String record){
        
        ImmunizationExchange exchange_record = new ImmunizationExchange(sender.getPublicKey(), receiver.getPublicKey(), record);
        
        exchange_record.signRecord(sender.getPrivateKey());
        
        records.add(exchange_record);
        
        this.addNewBlock(records);
        
        return exchange_record;
    }
    
    //validate chain
    
    //validate block
    
    //genesis block
    
    public static void main(String[] args) {
        
        Agent agent1 = new Agent();
        Agent agent2 = new Agent();
        
        VICKChainPilot sim = new VICKChainPilot();
        
        LinkedList<String> patient_data = sim.getSyntheticData();
        
        sim.transmitImmunizationRecord(agent1, agent2, patient_data.get(7));
        
        
        
    }
}
