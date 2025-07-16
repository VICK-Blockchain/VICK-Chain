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
    
    private Model model;
    
    private String vaccine_group;
    private Resource vaccine;
    private Resource vaccine_manufacturer;
    private Clinic clinic;
    private Date vaccine_date;
    
    private boolean hasInsurance = false;
    
    public PatientVaccinationModel(){
        
    }
    
    public PatientVaccinationModel(Model target_model){
        model = target_model;
    }
    
    public void addPatientVaccination(VaccineList vaccine){
        
        
    }
    
    public void addVaxDate(Date date, String id){
        
    }
    
    public void addVaxDose(int dose_number, String id){
        
    }
    
    public void addVaxSeries(int dose_series, String id){
        
    }
    
    public void addVaxLotNumber(String lot, String lot_id){
        
    }
    
    public void addVISDate(Date date, String id){
        
    }
    
    public void addClinicLocation(Clinic clinic){
        
    }
    
    public void addVaccineAdministrator(Vaccine_Administrator va){
        
    }
    
    public void addVaxInjectionSite(String id){
        
    }
    
    
    
    public static void main(String[] args) {
        
    }
    
    class Clinic{
        
        private String id;
        private String name;
        
     
        
    }
    
    class Vaccine_Administrator{
        private String name;
        private String id;
    }
}

