/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mac
 */
public class ImportData {
    
    public ImportData(){
        
    }
    
    public void readExcelSheet(String filePath){
        
       
        try {
            FileInputStream excel_file = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excel_file);

            Sheet sheet = workbook.getSheetAt(0);
            int row_num = 0;
            for(Row row: sheet){
                
                row_num = row.getRowNum();
                
                if(row_num == 0){
                    
                }
                else{
                    
                }
                
                
            }
            
            excel_file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImportData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImportData.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
}
