<%@ include file="/init.jsp" %>
<portlet:actionURL var="ChangeEsdActionUrl" name="/action/esd/form"></portlet:actionURL>

<%@ include file="/nav.jsp" %>
<h2>ESD Form</h2>
<aui:container>
	<aui:form name="esdInsertForm" action="${ChangeEsdActionUrl }">
		<aui:input wrapperCssClass="d-none" name="primaryKey" value="${esd.primaryKey }" />
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset label="Identifiable Information">
				<aui:row>
					<aui:col width="50">
						<aui:input label="ESD Code" name="code" type="text" value="${esd.code }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="ESD Name" name="name" type="text" value="${esd.name }"/>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col widht="100">
						<aui:input label="Administrator Name" name="administratorName" type="text" value="${esd.administratorName }"/>
					</aui:col>
				</aui:row>
			</aui:fieldset>
		</aui:fieldset-group>
		<c:set var="record" value="${esd }" />
		<%@ include file="/forms/components/address-section.jsp" %>
		<%@ include file="/forms/components/contact-section.jsp" %>				
		<aui:button-row>
			<aui:button name="submitButton" type="submit" value="Submit" />	
		</aui:button-row>
	</aui:form>
</aui:container>