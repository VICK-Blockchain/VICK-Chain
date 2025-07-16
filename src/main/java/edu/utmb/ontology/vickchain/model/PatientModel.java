/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import edu.utmb.ontology.vickchain.iri.ReferenceIRI;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import org.apache.jena.iri.IRI;

/**
 *
 * @author mac
 */
public class PatientModel {

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setRace(PatientRace race) {
        this.race = race;
    }
    
    private String patient_id;
    private String first_name, last_name;
    private PatientGender gender;
    private PatientRace race;
  
    private PatientEthnicity ethnicity;
    private PatientLanguage language;
    
    private int age;
    private String email;
    private String phone_number;
    
    private Set<PatientModel> children;
    private Date date;
    
    public enum PatientGender{
        FEMALE ("Female"),
        MALE ("Male"),
        OTHER ("Other"),
        UNKNOWN ("Unknown");
        
        private String valueString;
        
        PatientGender(String value){
            valueString = value;
        }
        
        public String getStringValue(){
            return valueString;
        }
        
        public String getIRI(){
            if(valueString == "Female"){
                return ReferenceIRI.FEMALE_SEX;
            }
            
            if(valueString == "Male"){
                return ReferenceIRI.MALE_SEX;
            }
            
            if(valueString == "Other"){
                
            }
            
            return ReferenceIRI.VACCINE_PATIENT;
        }
        
    }
    
    public enum PatientRace{
        American_Indian_or_Alaskan_Native ("American Indian or Alaskan Native"),
        Asian ("Asian"),
        Black_or_African_American ("Black or African American"),
        Native_Hawaiian_or_Pacific_Islander ("Native Hawaiian or Pacific Islander"),
        White ("White");
        
        private String valueString;
        
        PatientRace(String value){
            valueString = value;
        }
        
        public String getStringValue(){
            return valueString;
        }
        
        public String getIRI(){
            if(valueString.equalsIgnoreCase("American Indian or Alaskan Native")){
                return ReferenceIRI.RaceIRI.NATIVE_RACE;
            }
            
            if(valueString.equalsIgnoreCase("Asian")){
                return ReferenceIRI.RaceIRI.ASIAN_RACE;
            }
            
            if(valueString.equalsIgnoreCase("Black or African American")){
                return ReferenceIRI.RaceIRI.BLACK_RACE;
            }
            
            if(valueString.equalsIgnoreCase("Native Hawaiian or Pacific Islander")){
                return ReferenceIRI.RaceIRI.PACIFIC_RACE;
            }
            
            if(valueString.equalsIgnoreCase("White")){
                return ReferenceIRI.RaceIRI.WHITE_RACE;
            }
            
            return ReferenceIRI.RaceIRI.OTHER_RACE;
        }
        
    }
    
    public enum PatientEthnicity{
        Hispanic_or_Latino,
        Not_Hispanic_or_Latino
    }
    
    public enum PatientLanguage{
        English,
        Spanish,
        Other,
        Unknown
    }
    
    public void setGender(PatientGender gender_value){
        gender = gender_value;
    }
    
    public void setPatientRace(PatientRace race_value){
        race = race_value;
    }
    
    public void setPatientEthnicity(PatientEthnicity ethnicity_value){
        ethnicity = ethnicity_value;
    }
    
    public void setPatientLanguage(PatientLanguage language_value){
        language = language_value;
    }
}
