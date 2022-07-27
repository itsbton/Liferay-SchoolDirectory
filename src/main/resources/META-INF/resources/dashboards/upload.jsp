<%@ include file="/init.jsp" %>
<liferay-ui:error key="error" message="Something went wrong, please try again" />
<liferay-ui:error key="size" message="CSV is too big" />
<liferay-ui:error key="csv" message="The file must be in csv format and semicolon delimited" />

<portlet:actionURL var="uploadURL" name="/action/upload">
	<portlet:param name="tableName" value="${tableName }" />
</portlet:actionURL>

<aui:container>
	<h2>Upload CSV to ${tableName } Table</h2>
	<aui:form action="${uploadURL}" name="uploadCsvForm" enctype="multipart/form-data">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset label="Please select CSV file to upload">
				<input type="file" accept=".csv" name="csvFile"></input>
			</aui:fieldset>
			<aui:fieldset label="">
				<aui:input type="checkbox" label="First row headers?" name="firstRowHeaders" />
			</aui:fieldset>
			<aui:row>
				<aui:col width="100">
					<button type="submit" class="btn btn-default">Submit the CSV file</button>
				</aui:col>
			</aui:row>
		</aui:fieldset-group>
	</aui:form>
</aui:container>