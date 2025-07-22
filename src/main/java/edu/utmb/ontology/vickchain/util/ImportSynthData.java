/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package edu.utmb.ontology.vickchain.util;

import edu.utmb.ontology.vickchain.model.PatientModel.PatientEthnicity;
import edu.utmb.ontology.vickchain.model.PatientModel.PatientGender;
import edu.utmb.ontology.vickchain.model.PatientModel.PatientLanguage;
import edu.utmb.ontology.vickchain.model.PatientModel.PatientRace;
import edu.utmb.ontology.vickchain.model.SynthDataModel;
import static edu.utmb.ontology.vickchain.ontology.VICKManager.NAME_SPACE;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mac
 */
public class ImportSynthData {
    
    private Map<Integer, String> header_row = null;
    
    final int ROW_HEADER_ID = 1;
    
    private ImportSynthData() {
    }
    
    public static ImportSynthData getInstance() {
        return ImportSynthDataHolder.INSTANCE;
    }
    
    private static class ImportSynthDataHolder {

        private static final ImportSynthData INSTANCE = new ImportSynthData();
    }
    
    
    public void retrieveHeaderPairs(){
        
    }
    
    public void readExcelSpreadSheet(String filePath){
        
        Set<SynthDataModel> synth_data = new HashSet<>();
        
        try {
            
            InputStream is = Files.newInputStream(Paths.get(filePath));
            
            Workbook workbook = new XSSFWorkbook(is);
            
            Sheet sheet = workbook.getSheetAt(0);
            
            for(Row row: sheet){
                
                
                if(row.getRowNum()> ROW_HEADER_ID){
                    
                    SynthDataModel data_container = extractRowData(row);
                    
                    synth_data.add(data_container);
                }
                
                
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ImportSynthData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private SynthDataModel extractRowData(Row row){
        
        SynthDataModel data_model = new SynthDataModel();
        
        for(Cell cell : row){
            System.out.println(row.getRowNum());
            if(cell !=null ||cell.getCellType() != CellType.BLANK){
                
                
                String column_id = CellReference.convertNumToColString(cell.getColumnIndex());
                
                //patient name "A"
                if(column_id.contentEquals("A")){
                    String patient_name = cell.getStringCellValue();
                    
                    data_model.addName(patient_name);
                    
                }
                
                //patient id "B"
                if(column_id.contentEquals("B")){
                    String patient_id = cell.getStringCellValue();
                    
                    data_model.addPatientIRI(patient_id);
                }
                
                if(column_id.contentEquals("D")){
                    
                    Optional<PatientGender> result = Arrays.stream(
                            PatientGender.values()).filter(p->
                                    p.getStringValue().equalsIgnoreCase(cell.getStringCellValue())).findAny();

                    PatientGender pg = result.get();
                    
                    data_model.addPatientGender(pg);
                    
                }
                
                if(column_id.contentEquals("G")){
                    //System.out.println(cell.getStringCellValue());
                    Optional<PatientRace> result = Arrays.stream(PatientRace.values()).filter(p->
                            p.getStringValue().equalsIgnoreCase(cell.getStringCellValue())).findAny();
                    
                    PatientRace pr = result.get();
                        data_model.addPatientRace(pr);
    
                }
                
                if(column_id.contentEquals("J")){
                    //System.out.println(cell.getStringCellValue());
                    
                    //PatientEthnicity[] values = PatientEthnicity.values();
                    //System.out.println(values[0] + " and " + values [1]);
                    
                    Optional<PatientEthnicity> result = Arrays.stream(PatientEthnicity.values()).filter(p-> 
                            p.getStringValue().equalsIgnoreCase(cell.getStringCellValue())).findAny();
                    
                    PatientEthnicity pe = result.get();
                    
                    data_model.addPatientEthnicity(pe);
                }
                
                if(column_id.contentEquals("M")){
                    //System.out.println(cell.getStringCellValue());
                    Optional<PatientLanguage> result = Arrays.stream(PatientLanguage.values()).filter(p->
                    p.getStringValue().equalsIgnoreCase(cell.getStringCellValue())).findAny();
                    
                    PatientLanguage pl = result.get();
                    
                    data_model.addPatientLanguage(pl);
                }
                
                if(column_id.contentEquals("P")){
                    
                    double cell_value = cell.getNumericCellValue();
                    
                    data_model.addAge((int) cell_value);
                    
                }
                
                if(column_id.contentEquals("S")){
                    String date_string_value = cell.getStringCellValue();
                    data_model.addBirthDate(date_string_value);
                    
                }
                
                if(column_id.contentEquals("V")){
                    
                    int numericCellValue = (int)cell.getNumericCellValue();
                    
                    data_model.addInsurance(numericCellValue);
                    
                }
                
                if(column_id.contentEquals("AC")){
                    String clinic = cell.getStringCellValue();
                    
                    data_model.addVaxClinicName(clinic);
                }
                
                if(column_id.contentEquals("AD")){
                    
                    String clinic_id = NAME_SPACE + "#" + cell.getStringCellValue();
                    
                    data_model.addVaxClinicID(clinic_id);
                    
                }
                
                if(column_id.contentEquals("AE")){
                    String date_string = cell.getStringCellValue();
                    data_model.addVaxDate(date_string);
                }
                
            }
            
        }
        
        
        return data_model;
    }
    
    private int getMaxLenthSpreadsheet(String filePath){
        
        int length = 0;
        
        
        try {
            
            InputStream resourceAsStream = Files.newInputStream(Paths.get(filePath));
            
            Workbook workbook = new XSSFWorkbook(resourceAsStream);

            Sheet sheet = workbook.getSheetAt(0);
            
            Row header_row = sheet.getRow(ROW_HEADER_ID);
            
            for(Cell cell : header_row){
                
                
                if(cell !=null ||cell.getCellType() != CellType.BLANK){
                    length++;
                    
                }
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ImportSynthData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return length;
    }
    
    
    private int getMaxLenthSpreadsheetResource(String fileResourceName){
        
        int length = 0;
        
        InputStream resourceAsStream = ImportData.class.getClassLoader().getResourceAsStream(fileResourceName);

        try {

            Workbook workbook = new XSSFWorkbook(resourceAsStream);

            Sheet sheet = workbook.getSheetAt(0);
            
            Row header_row = sheet.getRow(ROW_HEADER_ID);
            
            for(Cell cell : header_row){
                
                
                if(cell !=null ||cell.getCellType() != CellType.BLANK){
                    length++;
                    
                }
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ImportSynthData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return length;
    }
    
    private String getValueAsString(Cell cell){
        
        if(CellType.NUMERIC == cell.getCellType()){
            
            int value = (int)cell.getNumericCellValue();
            
            String valueOf = String.valueOf(value);
            
            return valueOf;
            
        }
        
        else if(CellType.STRING == cell.getCellType()){
            
            String stringCellValue = cell.getStringCellValue();
            
            return stringCellValue;
            
        }
        
        
        return null;
    }
    
    public static void main(String[] args) {
        
        //int length = ImportSynthData.getInstance().getMaxLenthSpreadsheet("/Users/mac/Desktop/SyntheticData+ID.xlsx");
        //System.out.println(length);
        ImportSynthData instance = ImportSynthData.getInstance();
        
        instance.readExcelSpreadSheet("/Users/mac/Desktop/SyntheticData+ID.xlsx");
        
        
        
    }
}
