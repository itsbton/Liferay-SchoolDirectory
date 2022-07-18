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
			</aui:fieldset>
		</aui:fieldset-group>
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset label="Address">
				<aui:input label="Address Line 1" name="addressline1" type="text" value="${esd.addressLine1 }"/>
				<aui:input label="Address Line 2" name="addressline2" type="text" value="${esd.addressLine2 }"/>
				<aui:input label="State" name="state" type="text" value="${esd.state }"/>
				<aui:input label="Zip" name="zip" type="text"  value="${esd.zipCode }"/>
			</aui:fieldset>
		</aui:fieldset-group>
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset label="Contact">
				<aui:input label="Email" name="email" type="text" value="${esd.email }"/>
				<aui:input label="Phone" name="phone" type="text" value="${esd.phone }"/>
				<aui:input label="Administrator Name" name="administratorName" type="text" value="${esd.administratorName }"/>
			</aui:fieldset>
		</aui:fieldset-group>		
		<aui:button-row>
			<aui:button name="submitButton" type="submit" value="Submit" />	
		</aui:button-row>
	</aui:form>
</aui:container>