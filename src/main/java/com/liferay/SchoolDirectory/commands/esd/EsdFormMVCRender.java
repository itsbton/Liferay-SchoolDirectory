package com.liferay.SchoolDirectory.commands.esd;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.SchoolDirectory.objects.EducationalServiceDistrict;
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
	        "mvc.command.name=/render/esd/form"
	    },
	    service = MVCRenderCommand.class
	)
public class EsdFormMVCRender implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		System.out.println("---Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(renderRequest);
		
		String esdId = ParamUtil.getString(renderRequest, "esdId", "");
		
		SchoolDirectoryDao dao = new SchoolDirectoryDao();
		EducationalServiceDistrict esd = dao.getEducationalServiceDistrictById(esdId);
		renderRequest.setAttribute("esd", esd);
		
		System.out.println("ESD:");
		System.out.println(esd.toString());
		return "/forms/esd.jsp";
	}

}
