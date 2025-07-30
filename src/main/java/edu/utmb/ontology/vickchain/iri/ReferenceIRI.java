/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.iri;

import static edu.utmb.ontology.vickchain.ontology.VICKManager.NAME_SPACE;
import static edu.utmb.ontology.vickchain.ontology.VICKManagerSynth.VICK_NAME_SPACE;

/**
 *
 * @author mac
 */
public class ReferenceIRI {
    
    

    public static final String GENESIS = "000000";
    
    //family name
    
    public static final String FAMILY_NAME ="http://purl.obolibrary.org/obo/IAO_0020017";
    public static final String GIVEN_NAME ="http://purl.obolibrary.org/obo/IAO_0020016";
    
    public static final String POSTAL_ADDRESS ="http://purl.obolibrary.org/obo/IAO_0000422";
    
    public static final String POSTAL_CODE = "http://purl.obolibrary.org/obo/IAO_0000646";
    
    public static final String EMAIL_ADDRESS="http://purl.obolibrary.org/obo/IAO_0000429";
    
    public static final String FEMALE_SEX="http://purl.obolibrary.org/obo/PATO_0000383";
    public static final String MALE_SEX="http://purl.obolibrary.org/obo/PATO_0000384";
    public static final String UNKNOWN_SEX = VICK_NAME_SPACE + "#" +"VICK_0993";
    
    public static final String VACCINE_PATIENT="http://purl.obolibrary.org/obo/VICO_0000016";
    
    
    public static class RaceIRI{
        
        //OMRSE_00000219    #white
        //OMRSE_00000217    #black and AA
        //VICK_0997 #other race
        //OMRSE_00000216    #asian
        //OMRSE_00000218    #PI
        //OMRSE_00000215    #first nation
        //NCIT_C43866       #Middle Eastern

        
        public static final String WHITE_RACE = "http://purl.obolibrary.org/obo/OMRSE_00000219";
        public static final String BLACK_RACE = "http://purl.obolibrary.org/obo/OMRSE_00000217";
        public static final String OTHER_RACE = VICK_NAME_SPACE +"#"+"VICK_0997";
        public static final String ASIAN_RACE = "http://purl.obolibrary.org/obo/OMRSE_00000216";
        public static final String PACIFIC_RACE = "http://purl.obolibrary.org/obo/OMRSE_00000218";
        public static final String NATIVE_RACE = "http://purl.obolibrary.org/obo/OMRSE_00000215";
        public static final String ME_RACE = "http://purl.obolibrary.org/obo/NCIT_C77820";
    }
    
    public static class EthnicityIRI{
        //OMRSE_00000208 not hispanice
        //OMRSE_00000207 hispanic
        
        public static final String HISPANIC = "http://purl.obolibrary.org/obo/OMRSE_00000207"; 
        public static final String NON_HISPANIC = "http://purl.obolibrary.org/obo/OMRSE_00000208"; 
    }
    
    public static class LanguageIRI{
        //VICK_0993 unknown
        //OMRSE_00000610 English
        
        public static final String ENGLISH = "http://purl.obolibrary.org/obo/OMRSE_00000610";
        public static final String SPANISH = "http://purl.obolibrary.org/obo/OMRSE_00000849";
        public static final String GERMAN = VICK_NAME_SPACE + "#" + "VICK_1006";
        public static final String VIETNAMESE = VICK_NAME_SPACE + "#" + "VICK_1007";
        public static final String SWAHILI = VICK_NAME_SPACE + "#" + "VICK_1008";
        public static final String UNKNOWN = VICK_NAME_SPACE + "#" + "VICK_0993";
    }

}
