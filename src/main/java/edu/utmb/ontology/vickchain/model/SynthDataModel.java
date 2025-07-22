/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import edu.utmb.ontology.vickchain.iri.ReferenceIRIVaccine;
import edu.utmb.ontology.vickchain.model.PatientModel.PatientGender;
import edu.utmb.ontology.vickchain.model.PatientVaccinationModel.Clinic;
import static edu.utmb.ontology.vickchain.ontology.VICKManager.NAME_SPACE;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author mac
 */
public class SynthDataModel {
    
    private PatientVaccinationModel patient_vaccination;
    private PatientModel patient;
    
    private Clinic clinic;
    
    public SynthDataModel(){
       patient = new PatientModel();
       patient_vaccination = new PatientVaccinationModel();
    }
    
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
        
        patient_vaccination.setVaccineManufacturer(ReferenceIRIVaccine.Manufacturer.MERCK.toString());
        
    }

    public void addVaxAdministratorName(String value) {
        
        patient_vaccination.setVaxAdminName(value);
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addVaxAdiminstratorID(String value) {
        
        patient_vaccination.setVaxAdminID(value);
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
