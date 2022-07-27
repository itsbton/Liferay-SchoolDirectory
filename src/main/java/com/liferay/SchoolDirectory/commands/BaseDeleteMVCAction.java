package com.liferay.SchoolDirectory.commands;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
	    property = {
	        "javax.portlet.name="+ SchoolDirectoryPortletKeys.SCHOOLDIRECTORY,
	        "mvc.command.name=/action/delete"
	    },
	    service = MVCActionCommand.class
	)
public class BaseDeleteMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		System.out.println("--- Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(actionRequest);
		
		String tableName = ParamUtil.getString(actionRequest, "tableName", "");
		String primaryKey = ParamUtil.getString(actionRequest,  "primaryKey", "");
		
		if(tableName.isEmpty() || primaryKey.isEmpty()) {
			System.out.println("ERROR: " + this.getClass().getName() + 
					" - Either the table name or primary key is not provided and therefore cannot delete. "+ 
					"PK: " + primaryKey + " | tableName: " +tableName);
			SessionErrors.add(actionRequest, "errorDeleteError");
		} else {
			System.out.println("INFO: Deleting the following record - " +
					"PK: " + primaryKey + " | " + "tableName: " + tableName);
			SchoolDirectoryDao dao = new SchoolDirectoryDao();
			
			if(tableName.equalsIgnoreCase("District")) {
				dao.deleteDistrict(primaryKey);
			} else if(tableName.equalsIgnoreCase("School")) {
				dao.deleteSchool(primaryKey);
			} else if(tableName.equalsIgnoreCase("esd")) {
				dao.deleteEducationalServiceDistrict(primaryKey);
			} else {
				System.out.println("ERROR: " + this.getClass().getName() + " - Invalid table name (" + tableName + ")");
				SessionErrors.add(actionRequest, "errorInvalidTableName");
			}
			SessionMessages.add(actionRequest, "successDelete");
		}
	}

}
