package com.liferay.SchoolDirectory.commands;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.SchoolDirectory.objects.District;
import com.liferay.SchoolDirectory.objects.EducationalServiceDistrict;
import com.liferay.SchoolDirectory.objects.School;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
	    property = {
	        "javax.portlet.name="+ SchoolDirectoryPortletKeys.SCHOOLDIRECTORY,
	        "mvc.command.name=/action/upload"
	    },
	    service = MVCActionCommand.class
	)
public class UploadCsvMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		System.out.println("--- Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(actionRequest);
		
		//Make sure a proper table name was passed to the action
		String tableName = ParamUtil.getString(actionRequest,  "tableName", "");
		if(tableName.isEmpty()) {
			System.out.println("ERROR: " + this.getClass().getName() + " - No table name provided, therefore cannot upload records");
			SessionErrors.add(actionRequest, "errorNoTableName");
		} else if (!(tableName.equalsIgnoreCase("District") || 
				tableName.equalsIgnoreCase("School") ||
				tableName.equalsIgnoreCase("Educational Service Districts"))) {
			System.out.println("ERROR: " + this.getClass().getName() + " - Invalid table name provided ('"+tableName+"'), therefore cannot upload records");
			SessionErrors.add(actionRequest, "errorInvalidTableName");
		} else {
			//No issues so far, move ahead with parsing the CSV
			//Get the contents of the file
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
			InputStream inputStream;
			SchoolDirectoryDao dao = new SchoolDirectoryDao();
			
			//Depending on the table we're uploading - we need to make sure the correct columns are provided per row
			int columnCount = tableName.equalsIgnoreCase("District") ? SchoolDirectoryPortletKeys.DISTRICT_TABLE_CSV_COLUMN_COUNT : 
					tableName.equalsIgnoreCase("School") ? SchoolDirectoryPortletKeys.SCHOOL_TABLE_CSV_COLUMN_COUNT :
						tableName.equalsIgnoreCase("Educational Service Districts") ? SchoolDirectoryPortletKeys.ESD_TABLE_CSV_COLUMN_COUNT : -1;
			try {
				inputStream = uploadPortletRequest.getFileAsStream("csvFile");
				Scanner s = new Scanner(inputStream).useDelimiter("\\A");
			
				//Count the rows that have error
				int errorCount = 0;
				int rowCount = 0;
				while(s.hasNext()) {
					 //split at each new line
					 s.useDelimiter("\\n");
					 
					 //is first row just headers and we can skip?
					 boolean hasHeaders = Boolean.parseBoolean(ParamUtil.getString(actionRequest, "firstRowHeaders", "false"));
					 
					 //We are at each row
					 String row = s.next();
					 //Split by comma
					 String[] rowArray = row.split(",");
					 if(rowArray.length != columnCount){
						 //Each row needs to be the correct column count otherwise, it's considered invalid
						 System.out.println("INFO: " + this.getClass().getName() + 
								 " - Incorrect column length (Row column length="+Integer.toString(rowArray.length)+" Requested Length="+Integer.toString(SchoolDirectoryPortletKeys.SCHOOL_TABLE_CSV_COLUMN_COUNT)+
								 ". Could not put this csv row into the " + tableName + 
								 " table: " + row);
						 errorCount++;
					 } else {
						//upload a row at a time to the correct table
						if(hasHeaders && rowCount == 0) {
							System.out.println("INFO: " + this.getClass().getName() + " - Skipping first row because headers. Row: (" + row + ")");
						} else {
							if(tableName.equalsIgnoreCase("District")) {
								District district = new District(rowArray);
								dao.insertDistrict(district);
							} else if(tableName.equalsIgnoreCase("School")) {
								School school = new School(rowArray);
								dao.insertSchool(school);
							} else if(tableName.equalsIgnoreCase("Educational Service Districts")) {
								EducationalServiceDistrict esd = new EducationalServiceDistrict(rowArray);
								dao.insertEducationalServiceDistrict(esd);
							}
						}
					 }
					 rowCount++;
				}
				actionRequest.setAttribute("errorCount", errorCount);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	 //Checks if Unicode Character 'ZERO WIDTH NO-BREAK SPACE' (U+FEFF)
	 //https://stackoverflow.com/questions/6784799/what-is-this-char-65279
	 //This usually happens at the beginning of excel sheets
	//Added additional non-white white spaces - https://stackoverflow.com/questions/28295504/how-to-trim-no-break-space-in-java
	private String replaceCruelUnicodeCharacters(String value) {
		value = value.replace('\u00A0',' ');
		value = value.replace('\u2007',' ');
		value = value.replace('\u202F',' ');
		return value;
	}

}
