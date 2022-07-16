package com.liferay.SchoolDirectory.commands;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
	    property = {
	        "javax.portlet.name="+ SchoolDirectoryPortletKeys.SCHOOLDIRECTORY,
	        "mvc.command.name=/action/parse"
	    },
	    service = MVCActionCommand.class
	)
public class ParseCsvMVCAction implements MVCActionCommand {
	/**
	 * This assumes you are uploading a csv file with the following columns and in this order
	 * studentId/username/email
	 * visit reason //Removed
	 * submitter username
	 * meeting date
	 * location //Removed
	 * required by professor
	 * Course Number
	 * 
	 * If it has more or less than COLUMN_AMOUNT columns it will be skipped
	 */
	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
        
		
		InputStream inputStream;
		try {
			inputStream = uploadPortletRequest.getFileAsStream("csvFile");
			
			 Scanner s = new Scanner(inputStream).useDelimiter("\\A");
			//limitCount
			int limitCount = 0;
			 
			//Count the rows that have error
			int errorCount = 0;
			
			//ArrayList<Visit> visits = new ArrayList<Visit>();
		 
			int rowCount = 0;
			while(s.hasNext()) {
				 
				 //Checks to make sure we don't go over the limit count
				 if(limitCount > SchoolDirectoryPortletKeys.HARD_LIMIT_CSV_ROWS) {
					 break;
				 }
				 
				 //split at each new line
				 s.useDelimiter("\\n");
				 
				 //is first row just headers and we can skip?
				 //boolean hasHeaders = Boolean.parseBoolean(ParamUtil.getString(actionRequest, "firstRowHeaders", "false"));
				 
				 //SARCZoomSignInDao dao = new SARCZoomSignInDao();
				 
				 //We are at each row
				 String row = s.next();
				 //Split first row by comma
				 String[] rowArray = row.split(",");
				 if(rowCount == 0){
					 //We are assuming that the first row to get the amount of columns
					 int columnLength = rowArray.length;
				 }
				 //Visit visit = new Visit();
				 
				 //Converts the item in the first column to student info arraylist<string>
				 //index 0 = id
				 //index 1 = firstname lastname
				 String studentId = rowArray[0].trim();
				 
				 studentId = replaceCruelUnicodeCharacters(studentId).trim();
				 
//				 if(studentId.length() > 0) {
//					 try {
//						 ArrayList<String> studentInfo = dao.getStudentInformation(studentId);
//						 visit.setStudentId(studentInfo.get(0));
//					 } catch(Exception e) {
//						 System.out.println("INFO: " + this.getClass().getName() + " : cannot find student info with the id of '" + rowArray[0].trim() + "'");
//					 }
//					 
//				 }
//				 
//				 visit.setVisitReason(replaceCruelUnicodeCharacters(rowArray[1]).trim());
//				 visit.setSubmitterId(replaceCruelUnicodeCharacters(rowArray[2]).trim());
//				 visit.setMeetingDate(replaceCruelUnicodeCharacters(rowArray[3]).trim());
//				 visit.setLocation(replaceCruelUnicodeCharacters(rowArray[4]).trim());
//				 //check if 3rd string in array is int
//				 try {
//					 visit.setRequired(Boolean.parseBoolean(replaceCruelUnicodeCharacters(rowArray[5]).trim().toLowerCase()));
//				 } catch(NumberFormatException e) {
//					 System.out.println("INFO: " + this.getClass().getName() + ": The csv that is being uploaded does not have an boolean in the 6th column. Defaulting to false.");
//					 System.out.println("INFO: " + this.getClass().getName() + ": 6th column value: " + rowArray[5]);
//					 visit.setRequired(false);
//				 }
//				 visit.setCourseNumber(replaceCruelUnicodeCharacters(rowArray[6]).trim());
//
//				 visits.add(visit);
			 }
			 limitCount++;
			 
			 //pass all the found rows to the front end
//			 actionRequest.setAttribute("visits", visits);
			 actionRequest.setAttribute("errorCount", errorCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
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
