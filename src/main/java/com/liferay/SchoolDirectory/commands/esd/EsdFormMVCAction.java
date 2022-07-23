package com.liferay.SchoolDirectory.commands.esd;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.SchoolDirectory.objects.EducationalServiceDistrict;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
	    property = {
	        "javax.portlet.name="+ SchoolDirectoryPortletKeys.SCHOOLDIRECTORY,
	        "mvc.command.name=/action/esd/form"
	    },
	    service = MVCActionCommand.class
	)
public class EsdFormMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		System.out.println("--- Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(actionRequest);

		String primaryKey = ParamUtil.getString(actionRequest, "primaryKey", "");
		//Get information from the jsp and put it into an object
		EducationalServiceDistrict esd = new EducationalServiceDistrict();
		esd.setPrimaryKey(primaryKey);
		esd.setCode(ParamUtil.getString(actionRequest,"code", ""));
		esd.setName(ParamUtil.getString(actionRequest,"name", ""));
		esd.setAddressLine1(ParamUtil.getString(actionRequest,"addressline1", ""));
		esd.setAddressLine2(ParamUtil.getString(actionRequest,"addressline2", ""));
		esd.setState(ParamUtil.getString(actionRequest,"state", ""));
		esd.setZipCode(ParamUtil.getString(actionRequest,"zip", ""));
		esd.setPhone(ParamUtil.getString(actionRequest,"phone", ""));
		esd.setEmail(ParamUtil.getString(actionRequest,"email", ""));
		esd.setAdministratorName(ParamUtil.getString(actionRequest,"administratorName", ""));
		
		//pass it to dao
		SchoolDirectoryDao dao = new SchoolDirectoryDao();
		
		if(primaryKey.isEmpty()) {
			dao.insertEducationalServiceDistrict(esd);
		} else {
			dao.updateEducationalServiceDistrict(esd);
		}

	}

}
