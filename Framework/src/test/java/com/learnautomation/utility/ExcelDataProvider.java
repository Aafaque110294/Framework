package com.learnautomation.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	XSSFWorkbook wb;
	
	/*Create a constructer in it so that it can load Excel as soon as we create object of this class
	we don’t have to provide excel path again & again.*/
	public ExcelDataProvider()
	{
		File src= new File("./TestData/Data.xlsx");
		try {
			FileInputStream fis= new FileInputStream(src);
			wb= new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("unable to read excel file"+ e.getMessage());
		}
	}
	
	// Actual method to read data
	//if sheet has string data
	public String getStringData(String sheetName, int row, int column)   // sheetname is the tab Login in excelsheet
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	// if it sheet has numeric data
	public double getNumericData(String sheetName, int row, int column)
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}
	
	//if we want to provide sheet index then below method
	public String getStringData(int sheetIndex, int row, int column)   // sheetname is the tab Login in excelsheet
	{
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
	}
	
	//getString methods are method overloading examples, same name diff parameters

}
