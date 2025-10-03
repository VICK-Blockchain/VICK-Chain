/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import edu.utmb.ontology.vickchain.iri.ReferenceIRIVaccination;
import edu.utmb.ontology.vickchain.iri.ReferenceIRIVaccine;
import static edu.utmb.ontology.vickchain.ontology.VICKManagerSynth.VICK_NAME_SPACE;
import edu.utmb.ontology.vickchain.util.IDCounter;
import java.util.Date;
import java.util.Set;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author mac
 */
public class PatientVaccinationModel {
    
    private Model resource_model =null; //Apache Jena
    
    private Resource vaccine_node = null;
    private Resource vaccine_manufacturer_node = null;
    private Resource vaccine_route_node = null;
    private Resource vaccine_date_node = null;
    private Resource vaccine_administrator_node = null;
    private Resource clinic_node = null;
    //private Resource VIS_given_date_node = null;
    //private String vaccine_group;
    
    private String vaccine_manufacturer;
    private Clinic clinic;
    private Date vaccine_date;
    
    private Date VIS_given_date;
    
    private Vaccine_Administrator vax_admin;
    
    private boolean hasInsurance = false;
    
    private String vaccine_lot_number;
    
    private int vaccine_series;
    
    private String vaccination_injection_site;
    
    private String vaccine_route;
    
    public PatientVaccinationModel(){
        clinic = new Clinic();
        vax_admin = new Vaccine_Administrator();
    }
    
    public void initResourceModel(Model model) {
        this.resource_model = model;
        
        initVaccineResource(model);
        initVaxManufacturerResource(model);
        initVaxRouteResource(model);
        initVaxDateResource(model);
        initVaccineAdminResource(model);
        initClinicResource(model);
    }
    
    public Model getResourceModel(){
        return this.resource_model;
    }
    
    private void initClinicResource(Model model){
        
        String id = clinic.id;
        String name = clinic.name;
        
        if(id.contains(VICK_NAME_SPACE)){
            clinic_node = model.getResource(id);
        }
        else{
            clinic_node = model.getResource(VICK_NAME_SPACE + "#" + id);
        }
        
        clinic_node.addProperty(RDF.type, ReferenceIRIVaccination.Organization.ORGANIZATION);
        clinic_node.addProperty(RDFS.label, name);
        
    }
    
    private void initVISGivenDateResource(Model model){
        
    }
    
    private void initVaccineAdminResource(Model model){
        
        
        String id = vax_admin.id;
        String name = vax_admin.name;
        
        if(id.contains(VICK_NAME_SPACE)){
            vaccine_administrator_node = model.getResource(id);
        }
        else{
            vaccine_administrator_node = model.getResource(VICK_NAME_SPACE + "#" + id);
        }
        
        vaccine_administrator_node.addProperty(RDF.type, model.getResource(ReferenceIRIVaccination.VaccineAdministrator.PHYSICIAN));
        vaccine_administrator_node.addProperty(RDFS.label, name);
        
        //TODO add property link
    }
    
    public Resource getVaccineAdministratorNode(){
        
        return this.vaccine_administrator_node;
        
    }
    
    private void initVaccineResource(Model model){
        
        IDCounter idcounter = IDCounter.getInstance();
        
        vaccine_node = model.getResource(VICK_NAME_SPACE + "#" + idcounter.getLatestIdentifier());
        vaccine_node.addProperty(RDF.type, model.getResource(ReferenceIRIVaccine.GARDASIL));
        
        //TODO add property link
        
    }
    
    private void initVaxDateResource(Model model){
        IDCounter idcounter = IDCounter.getInstance();
        
        vaccine_date_node = model.getResource(VICK_NAME_SPACE + "#" + idcounter.getLatestIdentifier());
        //vaccine_date_node.addProperty(RDF.type, ReferenceIRIVaccination.) TODO create ontology class 
        vaccine_date_node.addProperty(RDFS.label, vaccine_date.toString());
        
        //TODO add property link
    }
    
    private void initVaxRouteResource(Model model){
        IDCounter idcounter = IDCounter.getInstance();
        
        vaccine_route_node = model.getResource(VICK_NAME_SPACE + "#" + idcounter.getLatestIdentifier());
        vaccine_route_node.addProperty(RDF.type, model.getResource(ReferenceIRIVaccination.VaccineRoute.INTRAMUSCULAR));
        vaccine_route_node.addProperty(RDFS.label, vaccine_route);
    }
    
    private void initVaxManufacturerResource(Model model){
        
        IDCounter idcounter = IDCounter.getInstance();
        
        vaccine_manufacturer_node = model.getResource(VICK_NAME_SPACE + "#" + idcounter.getLatestIdentifier());
        vaccine_manufacturer_node.addProperty(RDF.type, ReferenceIRIVaccine.Manufacturer.MERCK);
        vaccine_manufacturer_node.addProperty(RDFS.label, vaccine_manufacturer);
        
         //TODO add property link
    }
    
    
    public void addPatientVaccination(VaccineList vaccine){
        
        
    }
    
    public void setVaxDate(Date date){
        vaccine_date = date;
    }
    
    public void addVaxDose(int dose_number, String id){
        
    }
    
    public void setVaxSeries(int vax_series){
        vaccine_series = vax_series;
    }
    
    public void setVaxLotNumber(String lot){
        this.vaccine_lot_number = lot;
    }
    
    public void setVISGivenDate(Date value){
        VIS_given_date = value;
    }
    
    public void setVaccineManufacturer(String identifier){
        
        
        
        this.vaccine_manufacturer= identifier;
    }
    
    public void setClinicName(String clinic_name){
        
        if(clinic == null){
            clinic = new Clinic();
        }
        
        clinic.name = clinic_name;
        
    }
    
    public void setVaxAdminName(String admin_name){
        if(this.vax_admin == null){
            vax_admin = new Vaccine_Administrator();
        }
        
        vax_admin.name = admin_name;
    }
    
    public void setClinicID(String clinic_id){
        
        if(clinic == null){
            clinic = new Clinic();
        }
        
        clinic.id = clinic_id;
        
    }
    
    
    public void setVaxAdminID(String admin_id){
        if(this.vax_admin == null){
            vax_admin = new Vaccine_Administrator();
        }
        
        vax_admin.id = admin_id;
    }
    
    
    
    
    public void setVaxInjectionSite(String id){
        this.vaccination_injection_site = id;
    }
    
    
    
    public static void main(String[] args) {
        
    }

    void setVaxRoute(String value) {
        
        this.vaccine_route = value;
    }

    
    
    class Clinic{
        
        public Clinic(){
            
        }
        
        public String id;
        public String name;
        
     
        
    }
    
    class Vaccine_Administrator{
        
        public Vaccine_Administrator(){
            
        }
        
        public String name;
        public String id;
    }

    public Resource getVaccine_node() {
        return vaccine_node;
    }

    public Resource getVaccine_manufacturer_node() {
        return vaccine_manufacturer_node;
    }

    public Resource getVaccine_route_node() {
        return vaccine_route_node;
    }

    public Resource getVaccine_date_node() {
        return vaccine_date_node;
    }

    public Resource getVaccine_administrator_node() {
        return vaccine_administrator_node;
    }

    public Resource getClinic_node() {
        return clinic_node;
    }

  
    
}

