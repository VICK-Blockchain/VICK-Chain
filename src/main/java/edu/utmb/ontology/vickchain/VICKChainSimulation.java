/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain;

import com.google.common.collect.Multimap;
import edu.utmb.ontology.vickchain.exchange.ImmunizationExchange;
import edu.utmb.ontology.vickchain.model.Block;
import edu.utmb.ontology.vickchain.ontology.VICKManagerSynth;
import edu.utmb.ontology.vickchain.util.CryptUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author mac
 */
public class VICKChainSimulation {
    
    //TODO: multithreaded actions
    
    private CryptUtil crypto_utility = null;
    
    int block_count = 0;
    
    private VICKManagerSynth synthPatientRecords = null;
    
    private VICKChain vick_chain;
        
    private LinkedList<ImmunizationExchange> records = null;
    
    private Model model;
    
    public VICKChainSimulation(){
        
        block_count++;
        
        vick_chain = new VICKChain();
        
        synthPatientRecords = VICKManagerSynth.getInstance();
        synthPatientRecords.createNTExport();
        synthPatientRecords.parseIndividualNTData();
        
        crypto_utility = CryptUtil.getInstance();
        
        records = new LinkedList<ImmunizationExchange>();
        
        model = ModelFactory.createDefaultModel();
        
    }
    
    public Multimap<String, String> getSimulatedPatients(){
        
        return synthPatientRecords.getPatients();
    }
    
    public Multimap<String, String> getSimulatedProviders(){
        
        return synthPatientRecords.getProviders();
    }
    
    public LinkedList<String> getSyntheticDataNT(){
        
        return synthPatientRecords.getSynthDataNT();
    }

    public LinkedList<ImmunizationExchange> getRecords() {
        return records;
    }
    
    private void addNewBlock (LinkedList<ImmunizationExchange> values){
        
        String last_hash;
        if(vick_chain.getLastBlock() == null){
            last_hash = vick_chain.getORIGIN_HASH();
        }
        else{
            last_hash = vick_chain.getLastBlock().getCurrentHashData();
        }
        
        
        Block block = new Block(0, last_hash, values);
        
        //mineblock
        Block minedBlock = vick_chain.mineBlock(block);
        
        vick_chain.insertBlock(minedBlock);
        
    }

    public ImmunizationExchange transmitImmunizationRecord(Agent sender, Agent receiver, String record){
        
        ImmunizationExchange exchange_record = new ImmunizationExchange(sender.getPublicKey(),
                receiver.getPublicKey(), record);
        
        exchange_record.signRecord(sender.getPrivateKey());
        
        records.add(exchange_record);
        
        this.addNewBlock(records);
        
        return exchange_record;
    }
    

    
    
    public void findRelatedAgents(String nt, LinkedList<ProviderAgent> providers, LinkedList<PatientAgent> patients){
        
        model = ModelFactory.createDefaultModel();
        
        String string_query
                = "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX obo: <http://purl.obolibrary.org/obo/>\n"
                + "SELECT ?provider ?patient \n"
                + "WHERE {\n"
                + "?provider rdf:type <http://purl.org/vick/vick.owl#VICK_0000224>  . \n "
                + "?patient rdf:type <http://purl.obolibrary.org/obo/VICO_0000016> . \n"
                + "}";
        
        model.read(IOUtils.toInputStream(nt, "UTF-8"), null, "N-TRIPLES");
        
        Query query = QueryFactory.create(string_query);

        QueryExecution qe = QueryExecutionFactory.create(query, model);
        
        ResultSet rs = qe.execSelect();
        
        Resource provider_resource = null;
        Resource patient_resource = null;
        while (rs.hasNext()) {
            QuerySolution qs = rs.nextSolution();
            
            provider_resource = qs.getResource("provider");
            patient_resource = qs.getResource("patient");
            
            
        }
        qe.close();
        
        System.out.println("Provider: \t"+provider_resource.toString());
        System.out.println("Patient: \t"+ patient_resource.toString());
        
        
        ProviderAgent provider = null;
        for(ProviderAgent p : providers){
            if (p.isIdentifierMatch(provider_resource.getURI())){
                provider = p;
            }
        }
        
        PatientAgent patient = null;
        for(PatientAgent p: patients){
            if(p.isIdentifierMatch(patient_resource.getURI())){
                patient = p;
            }
        }
        
        
        System.out.println( "Patient " + patient.getLabels().get(0) +" shared their immunization info to Provider " + provider.getLabels().get(0) );
        this.transmitImmunizationRecord(patient, provider, nt);
        
        
    }

    
    
    public static void main(String[] args) {
        

        
        VICKChainSimulation sim = new VICKChainSimulation();
        
        LinkedList<String> patient_data = sim.getSyntheticDataNT();
        
        //Create Agents
        Multimap<String, String> simulatedPatients = sim.getSimulatedPatients();
        LinkedList<PatientAgent> patients = new LinkedList<PatientAgent>();
        
        for(var patient : simulatedPatients.asMap().entrySet()){
            
            PatientAgent patient_agent = new PatientAgent();
            
            //add data to patient agent
            patient_agent.setIdentifier(patient.getKey());
            
            patient_agent.setLabels(new ArrayList<> (patient.getValue()));
            
            patients.add(patient_agent);
            
        }

        Multimap<String, String> simulatedProviders = sim.getSimulatedProviders();
        LinkedList<ProviderAgent> providers = new LinkedList<ProviderAgent>();
        
        for( var provider : simulatedProviders.asMap().entrySet()){
            
            ProviderAgent provider_agent = new ProviderAgent();
            
            //add data to provider agent
            provider_agent.setIdentifier(provider.getKey());
            
            provider_agent.setLabels(new ArrayList<> (provider.getValue()));
            
            providers.add(provider_agent);
            
        }

        
        for(String patient_datum : patient_data){
            
            System.out.println(patient_datum);
            
            sim.findRelatedAgents(patient_datum, providers, patients);
            
            System.out.println("\n\n----------------------------\n\n");
            
        
        }

    }
    
}
