/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rainbowSix.editExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Lenovo
 */
public class EditExcel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try{
            File excel = new File("C://Users/Lenovo/Desktop/Mangas.xlsx");
           FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook libro = new XSSFWorkbook(fis);
            XSSFSheet hoja = libro.getSheetAt(0);
             Iterator<Row> itr = hoja.iterator();
             
             while(itr.hasNext()){
                 Row fila = itr.next();
                 Iterator<Cell> cellIterator = fila.cellIterator();
                 
                 while(cellIterator.hasNext()){
                     Cell celda=cellIterator.next();
                     
                     switch(celda.getCellType()){
                         case STRING:
                         System.out.print(celda.getStringCellValue() + "\t");    
                         break;
                         case NUMERIC:
                         System.out.print(celda.getNumericCellValue() + "\t");
                         break;
                         case BOOLEAN:
                          System.out.print(celda.getBooleanCellValue() + "\t");
                          break;
                     }
                    
                 }
                  System.out.println("");
             }
              Map<String, Object[]> newData = new HashMap<String, Object[]>();

              newData.put("8",new Object[]{7d,"Food Wars!","Comedia",36,"A"});
              newData.put("9",new Object[]{8d,"Fire Force ","Ciencia Ficción",25,"A"});
              newData.put("10",new Object[]{9d,"Welcome to Demon School! Iruma-Kun","Comedia",18,"A"});
              
               Set<String> newRows = newData.keySet();
               int rownum = hoja.getLastRowNum()+1;
              
               for(String key:newRows){
                   Row row=hoja.createRow(rownum++);
                   Object[] objArr=newData.get(key);
                   int cellnum=0;
                   
                   for(Object obj:objArr){
                       Cell celda=row.createCell(cellnum++);
                       if(obj instanceof String){
                           celda.setCellValue((String)obj);    
                       }else if(obj instanceof Boolean){
                           celda.setCellValue((Boolean)obj);                           
                       }else if(obj instanceof Date){
                           celda.setCellValue((Date)obj);
                       }else if(obj instanceof Double){
                           celda.setCellValue((Double) obj);
                       }else if(obj instanceof Integer){
                           celda.setCellValue((Integer)obj);
                       }
                   }
               }
               FileOutputStream os = new FileOutputStream(excel);
               libro.write(os);
               System.out.println("Se ha editado el archivo");
               
               os.close();
               libro.close();
               fis.close();
                       
               
        }catch(FileNotFoundException fe){
          fe.printStackTrace();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }
    
}
