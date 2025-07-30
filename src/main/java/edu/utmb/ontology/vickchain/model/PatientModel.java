/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.model;

import edu.utmb.ontology.vickchain.iri.ReferenceIRI;
import static edu.utmb.ontology.vickchain.ontology.VICKManagerSynth.VICK_NAME_SPACE;
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
public class PatientModel {
    
    private Resource resource_patient_node = null;
    private Resource resource_gender_node = null;
    private Resource resource_race_node = null;
    private Resource resource_ethnicity_node = null;
    
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
    private Date date_of_birth;
    
    private String address;
    
    private boolean hasInsurance = false;
    
    public void initResourceModel(Model model) {
        
        this.resource_patient_node = model.createResource(VICK_NAME_SPACE + this.patient_id);
        
        this.resource_patient_node.addProperty(RDF.type, model.getResource(ReferenceIRI.VACCINE_PATIENT));
        
        this.resource_patient_node.addProperty(RDFS.label, first_name + " " + last_name);
    }
    
    private void initGenderResource(Model model){
        
        
        //gender.getIRI();
        this.resource_gender_node = model.createResource(gender.getIRI());
        
        //TODO: addProperty
    }
    
    private void initRaceEthnicityResource(Model model){
        
        this.resource_race_node = model.createResource(race.getIRI());
        
        
        this.resource_ethnicity_node = model.createResource(ethnicity.getIRI());
        
    }

    
    
    public void setEthnicity(PatientEthnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

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
    
    public void setAddress(String address_value){
        this.address = address_value;
    }

    public void setAge(int i) {
        age = i;
    }

    public void setDOB(Date date) {
        date_of_birth = date;
    }
    
    public void setInsurance(boolean value){
        hasInsurance = value;
    }

    void setPhoneNumber(String value) {
        
        this.phone_number = value;
    }
    
    void setEmail(String value){
        this.email = value;
    }


    
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
            
            else if(valueString == "Male"){
                return ReferenceIRI.MALE_SEX;
            }
            
            else {
                return ReferenceIRI.UNKNOWN_SEX;
            }
            
            //return ReferenceIRI.VACCINE_PATIENT;
        }
        
    }
    
    public enum PatientRace{
        American_Indian_or_Alaskan_Native ("American Indian or Alaska Native"),
        Asian ("Asian"),
        Black_or_African_American ("Black or African American"),
        Native_Hawaiian_or_Pacific_Islander ("Native Hawaiian or Other Pacific Islander"),
        White ("White"),
        Middle_Eastern ("Middle Eastern or North African"),
        Other("Some other race, ethnicity, or origin");
        
        private String valueString;
        
        PatientRace(String value){
            valueString = value;
        }
        
        public String getStringValue(){
            return valueString;
        }
        
        public String getIRI(){
            if(valueString.equalsIgnoreCase("American Indian or Alaska Native")){
                return ReferenceIRI.RaceIRI.NATIVE_RACE;
            }
            
            if(valueString.equalsIgnoreCase("Asian")){
                return ReferenceIRI.RaceIRI.ASIAN_RACE;
            }
            
            if(valueString.equalsIgnoreCase("Black or African American")){
                return ReferenceIRI.RaceIRI.BLACK_RACE;
            }
            
            if(valueString.equalsIgnoreCase("Native Hawaiian or Other Pacific Islander")){
                return ReferenceIRI.RaceIRI.PACIFIC_RACE;
            }
            
            if(valueString.equalsIgnoreCase("White")){
                return ReferenceIRI.RaceIRI.WHITE_RACE;
            }
            
            if(valueString.equalsIgnoreCase("Middle Eastern or North African")){
                return ReferenceIRI.RaceIRI.ME_RACE;
            }
            
            return ReferenceIRI.RaceIRI.OTHER_RACE;
        }
        
    }
    
    public enum PatientEthnicity{
        Hispanic_or_Latino("Hispanic or Latino"),
        Not_Hispanic_or_Latino("Not Hispanic or Latino");
        
        private String valueString;
        
        public String getStringValue(){
            return valueString;
        }
        
        PatientEthnicity(String value){
            valueString = value;
        }
        
        public String getIRI(){
            
            if(valueString.trim().equalsIgnoreCase("Hispanic or Latino")){
                return ReferenceIRI.EthnicityIRI.HISPANIC;
            }
            
            return ReferenceIRI.EthnicityIRI.NON_HISPANIC;
        }
    }
    
    public enum PatientLanguage{
        English ("English"),
        Spanish ("Spanish"),
        German("German"),
        Vietnamese("Vietnamese"),
        Swahili("Swahili"),
        Other ("Other"),
        Unknown ("Unknown");
        
        private String valueString;
        
        PatientLanguage(String value){
            valueString = value;
        }
        
        public String getStringValue(){
            return valueString;
        }
        
        public String getIRI(){
            
            if(valueString.equalsIgnoreCase("English")){
                return ReferenceIRI.LanguageIRI.ENGLISH;
            }
            
            if(valueString.equalsIgnoreCase("German")){
                return ReferenceIRI.LanguageIRI.GERMAN;
            }
            
            if(valueString.equalsIgnoreCase("Swahili")){
                return ReferenceIRI.LanguageIRI.SWAHILI;
            }
            
            if(valueString.equalsIgnoreCase("Vietnamese")){
                return ReferenceIRI.LanguageIRI.VIETNAMESE;
            }
            
            if(valueString.equalsIgnoreCase("Spanish")){
                return ReferenceIRI.LanguageIRI.SPANISH;
            }
            
            return ReferenceIRI.LanguageIRI.UNKNOWN;
        }
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
