package com.RegressionAutomation.Retail.GenericMethods;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.RegressionAutomation.Retail.Helpers.ReadConfigProperty;


//////////////////////////////////////////////////////////////////////////////////////////////////
//Class Name     : Generic
//Parameter used : 
//Class Purpose  : It contains all Generic Methods
//Author         : satya ranjan
/////////////////////////////////////////////////////////////////////////////////////////////////
public class GenericMethods extends Constants {
	private static boolean resultDirectory;
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//Method Name     : createDirectory
	//Parameter used  : DirectoryPath
	//Method Purpose  : It will create directory
	//Author          : satya ranjan
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean createDirectory(String DirectoryPath)
	{
		File directory = new File(DirectoryPath);
		if (!directory.exists())
		{
			System.out.println("Creating Directory: "+ directory.getName());
			resultDirectory=false;
			
			try
			{
				directory.mkdir();
				resultDirectory=true;
			}catch(Exception E)
			{
				System.out.println("Directory is not created and exception is: "+E.getMessage());
			}
		}
		else
		{
			System.out.println("Directory is exist");
			try
			{
				directory.delete();
				directory.mkdir();
				resultDirectory=true;
			}catch(Exception E)
			{
				System.out.println("Directory is not created and exception is: "+E.getMessage());
			}
			resultDirectory=true;
		}
		
		return resultDirectory;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Method Name:	getDataFromExcel
	//Parameter used:	CucumberfileName
	//Author Name	:satya ranjan
	//Method Description:	
	//Date Of Creation:		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Map<String, String> getDataFromExcel(String CucumberfileName)
	{
		Map<String, String> dATA_MAP1 = new HashMap<String, String>();
		String InputSheetAddress= userDir + ReadConfigProperty.getConfigValues("InputSheetAddress");
		String InputSheetName=ReadConfigProperty.getConfigValues("InputSheetName");
		String DataSheetname=ReadConfigProperty.getConfigValues("DataSheetname");
		try
		{
			File file=new File(InputSheetAddress);
			FileInputStream inputStream=new FileInputStream(file);
			String fileExtensionName=DataSheetname.substring(DataSheetname.indexOf("."));
			if (fileExtensionName.equals(".xlsx"))
			{
				workbook=new XSSFWorkbook(inputStream);
				int rowNumber=0;
				int RowToBeExecitedFB1_rownumber = 0;
				int RowToBeExecited = 0;
				int RowToBeExecitedFB1 = 0;
				int TCRow = 0;
				Sheet sheet= workbook.getSheet(InputSheetName);
				int intRowcnt = sheet.getFirstRowNum()-sheet.getLastRowNum();
				int counter = 0;
				int counter2 = 0;
				int row = sheet.getLastRowNum();
				int column = 0;
				int Executecolumn=0;
				int i = sheet.getRow(0).getLastCellNum();
				for(int a = 0;a<i;a++)
				{
					String cellval = sheet.getRow(0).getCell(a).getStringCellValue();
					if (cellval.equalsIgnoreCase("TO_BE_EXECUTED"))
					{
						Executecolumn = a;
						break;
					}
				}
				for(int b=0;b<i;b++)
				{
					String cellval1 = sheet.getRow(0).getCell(b).getStringCellValue();
					if (cellval1.equalsIgnoreCase("SCRIPT_ID"))
					{
						column = b;
						break;
					}
				}
				for(int k=0;k<row;k++)
				{
					Cell cell=sheet.getRow(k).getCell(column);
					if (sheet.getRow(k).getCell(column).getStringCellValue().equalsIgnoreCase(CucumberfileName) && (sheet.getRow(k).getCell(Executecolumn).getStringCellValue().equalsIgnoreCase("YES")))
					{
						rowNumber=k;
						break;
					}
					
				}
				for (int k = 0;k<sheet.getLastRowNum();k++)
				{
					for (int l=0;l<sheet.getRow(k).getLastCellNum();l++)
					{
						Cell cell = sheet.getRow(k).getCell(l);
						if (cell.getStringCellValue().equals("SCRIPT_ID"))
						{
							RowToBeExecited=cell.getRowIndex();
							break;
						}
					}
				}
				for (int j=0;j<sheet.getRow(0).getLastCellNum();j++)
				{
					Cell cell1=sheet.getRow(RowToBeExecited).getCell(j);
					Cell cell2=sheet.getRow(rowNumber).getCell(j);
					dATA_MAP1.put(cell1.getStringCellValue(), cell2.getStringCellValue());
				}
				String tcName = dATA_MAP1.get("TestCaseName");
				String ReportName=dATA_MAP1.get("SCRIPT_ID")+ "_" + dATA_MAP1.get("TestCaseName");
			}
			else if(fileExtensionName.equals(".xls"))
			{
				workbook=new XSSFWorkbook(inputStream);
				int rowNumber=0;
				int RowToBeExecitedFB1_rownumber = 0;
				int RowToBeExecited = 0;
				int RowToBeExecitedFB1 = 0;
				int TCRow = 0;
				Sheet sheet= workbook.getSheet(InputSheetName);
				int intRowcnt = sheet.getFirstRowNum()-sheet.getLastRowNum();
				int counter = 0;
				int counter2 = 0;
				int row = sheet.getLastRowNum();
				int column = 0;
				int Executecolumn=0;
				int i = sheet.getRow(0).getLastCellNum();
				for(int a = 0;a<i;a++)
				{
					String cellval = sheet.getRow(0).getCell(a).getStringCellValue();
					if (cellval.equalsIgnoreCase("TO_BE_EXECUTED"))
					{
						Executecolumn = a;
						break;
					}
				}
				for(int b=0;b<i;b++)
				{
					String cellval1 = sheet.getRow(0).getCell(b).getStringCellValue();
					if (cellval1.equalsIgnoreCase("SCRIPT_ID"))
					{
						column = b;
						break;
					}
				}
				for(int k=0;k<row;k++)
				{
					Cell cell=sheet.getRow(k).getCell(column);
					if (sheet.getRow(k).getCell(column).getStringCellValue().equalsIgnoreCase(CucumberfileName) && (sheet.getRow(k).getCell(Executecolumn).getStringCellValue().equalsIgnoreCase("YES")))
					{
						rowNumber=k;
						break;
					}
					
				}
				for (int k = 0;k<sheet.getLastRowNum();k++)
				{
					for (int l=0;l<sheet.getRow(k).getLastCellNum();l++)
					{
						Cell cell = sheet.getRow(k).getCell(l);
						if (cell.getStringCellValue().equals("SCRIPT_ID"))
						{
							RowToBeExecited=cell.getRowIndex();
							break;
						}
					}
				}
				for (int j=0;j<sheet.getRow(0).getLastCellNum();j++)
				{
					Cell cell1=sheet.getRow(RowToBeExecited).getCell(j);
					Cell cell2=sheet.getRow(rowNumber).getCell(j);
					dATA_MAP1.put(cell1.getStringCellValue(), cell2.getStringCellValue());
				}
				String tcName = dATA_MAP1.get("TestCaseName");
				String ReportName=dATA_MAP1.get("SCRIPT_ID")+ "_" + dATA_MAP1.get("TestCaseName");
			}
			
			
		}catch(Exception E)
		{
			System.out.println("Exception is"+E.getMessage());
		}
		return dATA_MAP1;
		
	}
}
