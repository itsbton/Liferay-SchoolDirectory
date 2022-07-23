package com.liferay.SchoolDirectory.commands.district;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.SchoolDirectory.objects.District;
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
	        "mvc.command.name=/action/district/form"
	    },
	    service = MVCActionCommand.class
	)
public class DistrictFormMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		System.out.println("--- Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(actionRequest);

		String primaryKey = ParamUtil.getString(actionRequest, "primaryKey", "");
		//Get information from the jsp and put it into an object
		District district = new District();
		district.setPrimaryKey(primaryKey);
		district.setCode(ParamUtil.getString(actionRequest,"code", ""));
		district.setName(ParamUtil.getString(actionRequest,"name", ""));
		district.setAddressLine1(ParamUtil.getString(actionRequest,"addressline1", ""));
		district.setAddressLine2(ParamUtil.getString(actionRequest,"addressline2", ""));
		district.setState(ParamUtil.getString(actionRequest,"state", ""));
		district.setZipCode(ParamUtil.getString(actionRequest,"zip", ""));
		district.setPhone(ParamUtil.getString(actionRequest,"phone", ""));
		district.setEmail(ParamUtil.getString(actionRequest,"email", ""));
		district.setAdministratorName(ParamUtil.getString(actionRequest,"administratorName", ""));
		district.setEsdCode(ParamUtil.getString(actionRequest, "esdCode", ""));
		district.setEsdName(ParamUtil.getString(actionRequest, "esdName", ""));
		
		//pass it to dao
		SchoolDirectoryDao dao = new SchoolDirectoryDao();
		
		if(primaryKey.isEmpty()) {
			dao.insertDistrict(district);
		} else {
			dao.updateDistrict(district);
		}

	}


}
