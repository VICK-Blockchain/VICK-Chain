/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.vickchain.util;

import edu.utmb.ontology.vickchain.model.SampleDataModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
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
public class ImportData {
    
    private Map<Integer, String> header_row = null;
    
    public ImportData(){
        
        header_row = new HashMap<Integer, String>();
        
    }
    
    public void readExcelSheet(String filePath){
        
        //header_row.clear();
       
        try {
            FileInputStream excel_file = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excel_file);

            Sheet sheet = workbook.getSheetAt(0);
            int row_num = 0;
            for(Row row: sheet){
                
                row_num = row.getRowNum();
                
                if(row_num == 0){
                    
                    for(Cell cell: row){
                        
                        CellReference cell_reference = new CellReference(row.getRowNum(), cell.getColumnIndex());
                        
                        if(cell.getCellType() != CellType.BLANK){
                            header_row.put(cell.getColumnIndex(), cell.getStringCellValue());
                        }
                       
                        
                        
                    }
                    
                    
                }
                else{
                    
                    for(Cell cell: row){
                        
                        if(cell.getCellType() != CellType.BLANK){
                            
                            String information_type = header_row.get(cell.getColumnIndex());
                            
                            SampleDataModel sample_data = new SampleDataModel();
                            
                            if(information_type.equalsIgnoreCase("ID")){
                                sample_data.id = Integer.valueOf(cell.getStringCellValue());
                            }
                            else if(information_type.equalsIgnoreCase("FIRST NAME")){
                                sample_data.first_name = cell.getStringCellValue();
                            }
                            else if(information_type.equalsIgnoreCase("LAST NAME")){
                                sample_data.last_name = cell.getStringCellValue();
                            }
                            else if(information_type.equalsIgnoreCase("POSTAL ADDRESS")){
                                sample_data.postal_address = cell.getStringCellValue();
                            }
                            else if(information_type.equalsIgnoreCase("POSTAL CODE")){
                                sample_data.postal_code = cell.getStringCellValue();
                            }
                            else if(information_type.equalsIgnoreCase("EMAIL")){
                                sample_data.email = cell.getStringCellValue();
                            }
                            else if(information_type.equalsIgnoreCase("BIOLOGICAL SEX")){
                                sample_data.biological_sex = cell.getStringCellValue();
                            }
                            else if(information_type.equalsIgnoreCase("VACCINE")){
                                //sample_data.vaccines = cell.getStringCellValue();
                                sample_data.vaccines.add(cell.getStringCellValue());
                            }
                        }
                        
                    }
                    
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
