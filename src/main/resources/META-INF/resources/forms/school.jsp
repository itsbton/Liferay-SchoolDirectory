<%@ include file="/init.jsp" %>
<portlet:actionURL var="ChangeSchoolActionUrl" name="/action/school/form"></portlet:actionURL>

<%@ include file="/nav.jsp" %>

<h2>School Form</h2>
<aui:container>
	<aui:form name="schoolInsertForm" action="${ChangeSchoolActionUrl }">
		<aui:input wrapperCssClass="d-none" name="primaryKey" value="${school.primaryKey }" />
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset label="Identifiable Information">
				<aui:row>
					<aui:col width="50">
						<aui:input label="School Code" name="code" type="text" value="${school.code }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="School Name" name="name" type="text" value="${school.name }"/>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="ESD Code" name="esdCode" type="text" value="${school.esdCode }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="ESD Name" name="esdName" type="text" value="${school.esdName }"/>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Lea Code" name="leaCode" type="text" value="${school.esdCode }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Lea Name" name="leaName" type="text" value="${school.esdName }"/>
					</aui:col>
				</aui:row>
			</aui:fieldset>
		</aui:fieldset-group>
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset label="School Information">
				<aui:row>
					<aui:col width="50">
						<aui:input label="Lowest Grade" name="lowestGrade" type="text" value="${school.lowestGrade }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Highest Grade" name="highestGrade" type="text" value="${school.highestGrade }"/>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Principal Name" name="principalName" type="text" value="${school.principalName }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Org Category List" name="orgCategoryList" type="text" value="${school.orgCategoryList }"/>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="AYP Code" name="aypCode" type="text" value="${school.aypCode }"/>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Grade Category" name="gradeCategory" type="text" value="${school.gradeCategory }"/>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="100">
						<aui:input label="City" name="city" type="text" value="${school.city }"/>
					</aui:col>
				</aui:row>
			</aui:fieldset>
		</aui:fieldset-group>
		<c:set var="record" value="${school }" />
		<%@ include file="/forms/components/address-section.jsp" %>
		<%@ include file="/forms/components/contact-section.jsp" %>				
		<aui:button-row>
			<aui:button name="submitButton" type="submit" value="Submit" />	
		</aui:button-row>
	</aui:form>
</aui:container>