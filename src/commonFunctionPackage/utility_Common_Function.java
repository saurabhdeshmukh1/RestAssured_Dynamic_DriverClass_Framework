package commonFunctionPackage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class utility_Common_Function {
	public static void evidencefilecreator(String filename,String requestBody,String responseBody) throws IOException {
		File newfile = new File("D:\\Testing\\Automation\\Rest Assured\\"+filename+".txt");
		System.out.println("A new text file created to record request and response of API :"+newfile.getName());

		FileWriter datawrite = new FileWriter(newfile);
		datawrite.write("request body :" +requestBody+"\n\n");
		datawrite.write("response body :" +responseBody);
		datawrite.close();
		System.out.println("Request body and Response body are saved in : " +newfile.getName());
	}

	public static ArrayList<String> readdataexcel(String sheetname, String testcasename) throws IOException
	{
		ArrayList<String> ArrayData = new ArrayList<String>();
		//Step 1 : create the object of file input stream
		FileInputStream fis = new FileInputStream("D:\\Testing\\Automation\\Rest Assured\\Post_Test_Data.xlsx");
		//Step 2 : open the excel file 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		//Step 3 : access the sheet name 
		int countofsheet = workbook.getNumberOfSheets();
		for (int i = 0; i < countofsheet; i++)
		{
			String filesheetname = workbook.getSheetName(i);
			if(filesheetname.equalsIgnoreCase(sheetname))
			{
				//Step 4 : access the row from where data is supposed to be fetched
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				//Row r = rows.next();
				while(rows.hasNext())
				{
					Row r2 = rows.next();
					if(r2.getCell(0).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						Iterator<Cell> cellvalues = r2.cellIterator();
						while(cellvalues.hasNext()) 
						{
							String testdata = cellvalues.next().getStringCellValue();
							ArrayData.add(testdata);
						}
					}
				}
			}
		} 
		workbook.close();
		return ArrayData;
	}
}
