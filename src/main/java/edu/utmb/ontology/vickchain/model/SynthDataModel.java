/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import edu.utmb.ontology.vickchain.model.PatientModel.PatientGender;

/**
 *
 * @author mac
 */
public class SynthDataModel {
    
    private PatientVaccinationModel patient_vaccination;
    private PatientModel patient;
    
    
    
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
    
    
}
