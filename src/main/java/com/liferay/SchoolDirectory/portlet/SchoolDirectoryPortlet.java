package com.liferay.SchoolDirectory.portlet;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;
import com.liferay.SchoolDirectory.objects.EducationalServiceDistrict;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author sablan
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=SchoolDirectory",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SchoolDirectoryPortletKeys.SCHOOLDIRECTORY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SchoolDirectoryPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		//Check if there's a loadRecords attribute being passed into the request
		//Should be false on every other page that isn't the initial render
		boolean loadRecords = ParamUtil.get(renderRequest, "init", true);
		
		if(loadRecords) {
			//TODO: delete me
			System.out.println("--LOADING RECORDS--");
			
			ArrayList<EducationalServiceDistrict> ESDs = new ArrayList<EducationalServiceDistrict>();
			renderRequest.setAttribute("ESDs", ESDs);
		}
		//Need to pass context path to the jsp in order to reference any locally saved files
		//i.e. images
		renderRequest.setAttribute("contextPath", renderRequest.getContextPath());
		
		super.render(renderRequest, renderResponse);
	}
	
	
}

