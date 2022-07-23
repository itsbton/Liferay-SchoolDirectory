package com.liferay.SchoolDirectory.commands.school;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.SchoolDirectory.objects.School;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
	    property = {
	        "javax.portlet.name="+ SchoolDirectoryPortletKeys.SCHOOLDIRECTORY,
	        "mvc.command.name=/render/school/form"
	    },
	    service = MVCRenderCommand.class
	)
public class SchoolFormMVCRender implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		System.out.println("---Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(renderRequest);
		
		String schoolId = ParamUtil.getString(renderRequest, "schoolId", "");
		
		SchoolDirectoryDao dao = new SchoolDirectoryDao();
		School school = dao.getSchoolById(schoolId);
		renderRequest.setAttribute("school", school);
		
		//TODO:delete
		System.out.println("School:");
		System.out.println(school.toString());
		return "/forms/school.jsp";
	}

}
