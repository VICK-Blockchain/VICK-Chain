/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain;

import edu.utmb.ontology.vickchain.exchange.ImmunizationExchange;
import edu.utmb.ontology.vickchain.model.Block;
import edu.utmb.ontology.vickchain.ontology.VICKManagerSynth;
import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.util.LinkedList;

/**
 *
 * @author mac
 */
public class VICKChainSimulation {
    
    //TODO: multithreaded actions
    
    private CryptUtil crypto_utility = null;
    
    final private String ORIGIN_HASH = "000000000000000000000000000000000000000000000000000000000000000";
    
    private VICKManagerSynth synthPatientRecords = null;
    
    private VICKChain vick_chain;
        
    private LinkedList<ImmunizationExchange> records = null;
    
    public VICKChainSimulation(){
        
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
        
        
        String last_hash = vick_chain.getLastBlock().getCurrentHashData();
        
        Block block = new Block(0, last_hash, values);
        
        //mineblock
        Block minedBlock = vick_chain.mineBlock(block);
        
        vick_chain.insertBlock(minedBlock);
        
    }

    public ImmunizationExchange transmitImmunizationRecord(Agent sender, Agent receiver, String record){
        
        ImmunizationExchange exchange_record = new ImmunizationExchange(sender.getPublicKey(), receiver.getPublicKey(), record);
        
        exchange_record.signRecord(sender.getPrivateKey());
        
        records.add(exchange_record);
        
        this.addNewBlock(records);
        
        return exchange_record;
    }
    

    
    public static void main(String[] args) {
        
        Agent agent1 = new Agent();
        Agent agent2 = new Agent();
        
        VICKChainSimulation sim = new VICKChainSimulation();
        
        LinkedList<String> patient_data = sim.getSyntheticData();
        
        sim.transmitImmunizationRecord(agent1, agent2, patient_data.get(7));
        
        
        
    }
    
}
