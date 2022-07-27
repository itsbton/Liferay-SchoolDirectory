<%@ include file="/init.jsp" %>
<portlet:actionURL var="ChangeDistrictActionUrl" name="/action/district/form"></portlet:actionURL>

<%@ include file="/nav.jsp" %>
<h2>District Form</h2>
<aui:container>
	<aui:form name="districtInsertForm" action="${ChangeDistrictActionUrl }">
		<aui:input wrapperCssClass="d-none" name="primaryKey" value="${district.primaryKey }" />
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset label="Identifiable Information">
				<aui:row>
					<aui:col width="50">
						<aui:input label="District Code" name="code" type="text" value="${district.code }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="District Name" name="name" type="text" value="${district.name }"/>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="ESD Code" name="esdCode" type="text" value="${district.esdCode }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="ESD Name" name="esdName" type="text" value="${district.esdName }"/>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="100">
						<aui:input label="Administrator Name" name="administratorName" type="text" value="${district.administratorName }"/>
					</aui:col>
				</aui:row>
			</aui:fieldset>
		</aui:fieldset-group>
		<c:set var="record" value="${district }" />
		<%@ include file="/forms/components/address-section.jsp" %>
		<%@ include file="/forms/components/contact-section.jsp" %>		
		<%@ include file="/forms/components/button-section.jsp" %>
	</aui:form>
</aui:container>