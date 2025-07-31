/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import java.util.Date;
import java.util.Set;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author mac
 */
public class PatientVaccinationModel {
    
    private Model model; //Apache Jena
    
    private String vaccine_group;
    private Resource vaccine;
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
        
    }
    
    void initResourceModel(Model target_model) {
        this.model = target_model;
    }
    
    public PatientVaccinationModel(Model target_model){
        model = target_model;
    }
    
    public void addPatientVaccination(VaccineList vaccine){
        
        
    }
    
    public void setVaxDate(Date date){
        
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
}

