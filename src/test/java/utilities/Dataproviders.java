package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders 
{
	@DataProvider(name="logindata")
	public String [][]getdata() throws IOException
	{
		String path=".\\testData\\opencartlogintestdata.xlsx";//taking xlfile path from testdata
		
		Excelutility xlutil=new Excelutility(path);//create an object for excel utility
		
		
		int totalrows=xlutil.getRowcount("sheet1");
		int totalcells=xlutil.getcellcount("sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcells];//created two dimensional array which can store the data from excelutility file
		
		for(int i=1;i<=totalrows;i++) // row data starts from 1st row
		{
			for(int j=0;j<totalcells;j++) //cell data starts from 0th cell
			{
				logindata[i-1][j]=xlutil.getcelldata("sheet1",i,j);//i=1,j=0 in excel but in array[0][0]
			}
		}
		
	return logindata;	//returning two dimensional array
	}
	
	//dataprovider2
	//dataprovider3
	//dataprovider4
	//dataprovider5

}
