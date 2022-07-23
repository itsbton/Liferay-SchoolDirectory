package com.liferay.SchoolDirectory.commands.school;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.SchoolDirectory.objects.School;
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
	        "mvc.command.name=/action/school/form"
	    },
	    service = MVCActionCommand.class
	)
public class SchoolFormMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		System.out.println("--- Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(actionRequest);

		String primaryKey = ParamUtil.getString(actionRequest, "primaryKey", "");
		//Get information from the jsp and put it into an object
		School school = new School();
		school.setPrimaryKey(primaryKey);
		school.setCode(ParamUtil.getString(actionRequest,"code", ""));
		school.setName(ParamUtil.getString(actionRequest,"name", ""));
		school.setAddressLine1(ParamUtil.getString(actionRequest,"addressline1", ""));
		school.setAddressLine2(ParamUtil.getString(actionRequest,"addressline2", ""));
		school.setState(ParamUtil.getString(actionRequest,"state", ""));
		school.setZipCode(ParamUtil.getString(actionRequest,"zip", ""));
		school.setPhone(ParamUtil.getString(actionRequest,"phone", ""));
		school.setEmail(ParamUtil.getString(actionRequest,"email", ""));
		
		school.setAypCode(ParamUtil.getString(actionRequest,"aypCode", ""));
		school.setCity(ParamUtil.getString(actionRequest,"city", ""));
		school.setEsdCode(ParamUtil.getString(actionRequest,"esdCode", ""));
		school.setEsdName(ParamUtil.getString(actionRequest,"esdName", ""));
		school.setGradeCategory(ParamUtil.getString(actionRequest,"gradeCategory", ""));
		school.setHighestGrade(ParamUtil.getString(actionRequest,"highestGrade", ""));
		school.setLowestGrade(ParamUtil.getString(actionRequest,"lowestGrade", ""));
		school.setLeaCode(ParamUtil.getString(actionRequest,"leaCode", ""));
		school.setLeaName(ParamUtil.getString(actionRequest,"leaName", ""));
		school.setOrgCategoryList(ParamUtil.getString(actionRequest,"orgCategoryList", ""));
		school.setPrincipalName(ParamUtil.getString(actionRequest,"principalName", ""));
		
		
		//pass it to dao
		SchoolDirectoryDao dao = new SchoolDirectoryDao();
		
		if(primaryKey.isEmpty()) {
			dao.insertSchool(school);
		} else {
			dao.updateSchool(school);
		}
	}

}
