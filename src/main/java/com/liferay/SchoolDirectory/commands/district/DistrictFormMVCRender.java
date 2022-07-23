package com.liferay.SchoolDirectory.commands.district;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.dao.SchoolDirectoryDao;
import com.liferay.SchoolDirectory.objects.District;
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
	        "mvc.command.name=/render/district/form"
	    },
	    service = MVCRenderCommand.class
	)
public class DistrictFormMVCRender implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		System.out.println("---Made it to " + this.getClass().getName() + " ---");
		ParamUtil.print(renderRequest);
		
		String districtId = ParamUtil.getString(renderRequest, "districtId", "");
		
		SchoolDirectoryDao dao = new SchoolDirectoryDao();
		District district = dao.getDistrictById(districtId);
		renderRequest.setAttribute("district", district);
		
		//TODO:delete
		System.out.println("District:");
		System.out.println(district.toString());
		
		return "/forms/district.jsp";
	}

}
