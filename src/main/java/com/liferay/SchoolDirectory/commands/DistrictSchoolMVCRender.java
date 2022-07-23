package com.liferay.SchoolDirectory.commands;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.SchoolDirectory.objects.District;
import com.liferay.SchoolDirectory.objects.School;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
	    property = {
	        "javax.portlet.name="+ SchoolDirectoryPortletKeys.SCHOOLDIRECTORY,
	        "mvc.command.name=/render/districtsSchools/dashboard"
	    },
	    service = MVCRenderCommand.class
	)
public class DistrictSchoolMVCRender implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		System.out.println("--- Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(renderRequest);
		
		String tab = ParamUtil.getString(renderRequest, "tab", "");
		SchoolDirectoryDao dao = new SchoolDirectoryDao();
		
		if(tab.equalsIgnoreCase("school")) {
			ArrayList<School> records = dao.getAllSchools();
			renderRequest.setAttribute("records", records);
		} else if(tab.equalsIgnoreCase("district")) {
			ArrayList<District> records = dao.getAllDistricts();
			renderRequest.setAttribute("records", records);
		} else {
			String error = "<p>" + "Error location: " + this.getClass().getName() + "</p>" +
					"<p>" + "Tab variable is '" + tab + "'. We are unable to direct you to a tab with that name";
			
			renderRequest.setAttribute("error", error);
			return "/error.jsp";
		}
		
		renderRequest.setAttribute("tab", tab);
		return "/dashboards/districtsSchools.jsp";
	}

}
