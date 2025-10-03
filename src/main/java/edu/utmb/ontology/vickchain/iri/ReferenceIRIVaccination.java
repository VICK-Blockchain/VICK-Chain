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
public class ReferenceIRIVaccination {

    public static final String VACCINATION = "http://purl.obolibrary.org/obo/VO_0000002";

    public static final String MMR_VACCINATION = "http://purl.obolibrary.org/obo/VICO_0000055";

    static public class VaccineRoute {
        
        public static final String INTRAMUSCULAR = "http://purl.obolibrary.org/obo/VO_0000340";

    }
    
    static public class VaccineSite{
        public static final String DELTOID_UPPER_ARM = VICK_NAME_SPACE + "#" + "VICK_6698";
    }
    
    static public class VaccineAdministrator{
        public static final String PROVIDER = "http://purl.org/vick/vick.owl#VICK_0000222";
        public static final String PHYSICIAN = "http://purl.org/vick/vick.owl#VICK_0000224";
    }
    
    static public class Organization{
        public static final String ORGANIZATION = "http://purl.obolibrary.org/obo/OBI_0000245";
    }

}
