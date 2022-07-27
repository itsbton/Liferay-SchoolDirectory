package com.liferay.SchoolDirectory.commands;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
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
	        "mvc.command.name=/render/upload"
	    },
	    service = MVCRenderCommand.class
	)
public class CsvUploadMVCRender implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		System.out.println("---Arrived At '" + this.getClass().getName() + "'---");
		ParamUtil.print(renderRequest);
		
		String tableName = ParamUtil.getString(renderRequest, "tableName", "");
		
		renderRequest.setAttribute("tableName", tableName);
		return "/dashboards/upload.jsp";
	}

}
