/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import edu.utmb.ontology.vickchain.iri.ReferenceIRIVaccine;
import edu.utmb.ontology.vickchain.model.PatientModel.PatientGender;
import edu.utmb.ontology.vickchain.model.PatientVaccinationModel.Clinic;
import edu.utmb.ontology.vickchain.ontology.AbstractDataModel;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.jena.rdf.model.Model;

/**
 *
 * @author mac
 */
public class SynthDataModel extends AbstractDataModel{
    
    //private Model model = null;
    
    private PatientVaccinationModel patient_vaccination;
    private PatientModel patient;
    
    //private Clinic clinic;
    
    public SynthDataModel(){
       patient = new PatientModel();
       patient_vaccination = new PatientVaccinationModel();
    }
    
    @Override
    public String toString(){
        
        StringBuilder content = new StringBuilder();
        
        content.append(patient.toString());
        
        
        return content.toString();
        
    }
    
    public PatientVaccinationModel getPatientVaccinationModel(){
        
        return this.patient_vaccination;
        
    }
   
    /*
    public void initModel(Model import_model){
        model = import_model;
        patient.initResourceModel(model);
    }
    */
    public void addName(String name){
       
        String[] f_l = name.split(" ");
        
        patient.setFirst_name(f_l[0].trim());
        patient.setLast_name(f_l[1].trim());
        
    }

    public void addPatientIRI(String patient_id) {
        
        patient.setPatient_id(patient_id);
        
    }
    
    public void addPatientGender(PatientGender g_value){
        patient.setGender(g_value);
    }

    public void addPatientRace(PatientModel.PatientRace pr) {
        patient.setRace(pr);
    }

    public void addPatientEthnicity(PatientModel.PatientEthnicity pe) {
        patient.setEthnicity(pe);
    }

    public void addPatientLanguage(PatientModel.PatientLanguage pl) {
        patient.setPatientLanguage(pl);
    }

    public void addAge(int i) {
        
        patient.setAge(i);
        
    }
    
    public void addPatientPhoneNumber(String value){
        patient.setPhoneNumber(value);
    }
    
    public void addPatientEmail(String value){
        patient.setEmail(value);
    }

    public void addBirthDate(String date_string_value) {
        
        try {
            Date dob = DateUtils.parseDate(date_string_value, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
            patient.setDOB(dob);
            
        } catch (ParseException ex) {
            Logger.getLogger(SynthDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addInsurance(int value) {
        
        if(value == 0){
            patient.setInsurance(false);
        }
        else{
            patient.setInsurance(true);
        }
        
        
        
    }

    public void addVaxClinicName(String clinic_name) {
        
        patient_vaccination.setClinicName(clinic_name);
       
    }
    
    public void addVaxClinicID(String identifier){
        
        patient_vaccination.setClinicID(identifier);
        
    }

    public void addVaxDate(String date_string) {
        
        try {
            Date vax_date = DateUtils.parseDate(date_string, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
            
            patient_vaccination.setVaxDate(vax_date);
        } catch (ParseException ex) {
            Logger.getLogger(SynthDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addVaxManufacturer(String value) {
        
        patient_vaccination.setVaccineManufacturer(ReferenceIRIVaccine.Manufacturer.MERCK);
        
    }

    public void addVaxAdministratorName(String value) {
        
        patient_vaccination.setVaxAdminName(value);
        
    }

    public void addVaxAdiminstratorID(String value) {
        
        patient_vaccination.setVaxAdminID(value);
        
    }

    public void addAddress(String value) {
        
        patient.setAddress(value);
        
    }

    public void addLotNumber(String value) {
        
        patient_vaccination.setVaxLotNumber(value);
    }

    public void addVaxSeries(int value) {
       
        patient_vaccination.setVaxSeries(value);
    }

    public void addVISGivenDate(String date_string) {
        
        try {
            Date value = DateUtils.parseDate(date_string, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
            
            patient_vaccination.setVISGivenDate(value);
        } catch (ParseException ex) {
            Logger.getLogger(SynthDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addVaxInjectionSite(String value) {
        patient_vaccination.setVaxInjectionSite(value);
    }

    public void addVaxRoute(String value) {
        patient_vaccination.setVaxRoute(value);
    }

    @Override
    public void initModel(Model import_model) {
        model = import_model;
        patient.initResourceModel(model);
        patient_vaccination.initResourceModel(model);
    }

   

    
    
    
}
