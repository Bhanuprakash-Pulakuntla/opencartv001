package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutility 
	
	{
		public FileInputStream fi;
		public FileOutputStream fo;
		public XSSFWorkbook workbook;
		public XSSFSheet sheet;
		public XSSFRow rows;
		public XSSFCell cells;
		String path;
		public CellStyle style;
		public Excelutility(String path) 
		{
			this.path=path;
		}
		
		public int getRowcount(String sheetname) throws IOException 
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetname);
			int totalrows=sheet.getLastRowNum();
			workbook.close();
			fi.close();
			return totalrows;
			
			
		}
		public int getcellcount(String sheetname,int rownum) throws IOException 
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetname);
			rows=sheet.getRow(rownum);
			int totalcells=rows.getLastCellNum();
			workbook.close();
			fi.close();
			return totalcells;
		}
		public String getcelldata(String sheetname,int rownum,int cellnum) throws IOException 
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetname);
			rows=sheet.getRow(rownum);
			cells=rows.getCell(cellnum);
			String data;
			//data=cells.toString();
			try 
			{
				DataFormatter formatter=new DataFormatter();
				data=formatter.formatCellValue(cells);
			}
			catch(Exception e) 
			{
				data="";
			}
			workbook.close();
			fi.close();
			return data;
		}
		public void setdata(String sheetname,int rownum,int cellnum,String data) throws IOException 
		{
			File xlfile=new File(path);
			if(!xlfile.exists()) //if file not exists create a new file
			{
				workbook=new XSSFWorkbook();
				fo=new FileOutputStream(path);
				workbook.write(fo);
			}
			
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			if(workbook.getSheetIndex(sheetname)==-1)//if sheet not eixsts create a new sheet
				workbook.createSheet(sheetname);
			sheet=workbook.getSheet(sheetname);
			
			if(sheet.getRow(rownum)==null)//if row not exist create a row
				sheet.createRow(rownum);
			rows=sheet.getRow(rownum);
			
			cells=rows.createCell(cellnum);
			cells.setCellValue(data);
			fo=new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		}
		public void fillgreencolor(String sheetname,int rownum,int cellnum) throws IOException 
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetname);
			rows=sheet.getRow(rownum);
			cells=rows.getCell(cellnum);
			
			style=workbook.createCellStyle();
			
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			cells.setCellStyle(style);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
			
		}
		
		public void fillRedcolor(String sheetname,int rownum,int cellnum) throws IOException 
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetname);
			rows=sheet.getRow(rownum);
			cells=rows.getCell(cellnum);
			
			style=workbook.createCellStyle();
			
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			cells.setCellStyle(style);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
			
		}
}
