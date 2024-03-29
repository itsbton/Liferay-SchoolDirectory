<%@ include file="/init.jsp" %>

<liferay-ui:error key="errorInvalidTableName" message="Sorry, an incorrect table name was requested. Please try the link again or reach out to your administrator" />
<liferay-ui:error key="errorNoTableName" message="Sorry, no table name was requested. Please try the link again or reach out to your administrator" />
<liferay-ui:error key="errorDeleteError" message="Sorry, we're unable to delete this record. Please consult administrator if the issue persists" />
<liferay-ui:error key="errorCSVRows" message="Successfully added ${successCount } records. However, unable to add ${errorCount } rows due to an issue with the columns. See log for details." />
<liferay-ui:success key="successCSVRows" message="Successfully added ${successCount } records!" />
<liferay-ui:success key="successDelete" message="Successfully deleted record!" />

<%--Creates a URL to go to the correct commands. Check commands package and OSGI headers --%>
<portlet:renderURL var="CsvUploadUrl">
	<portlet:param name="mvcRenderCommandName" value="/render/upload" />
	<portlet:param name="loadRecords" value="false" />
	<portlet:param name="tableName" value="Educational Service Districts" />
</portlet:renderURL>

<%--Create URL to go to a blank esd form --%>
<portlet:renderURL var="EsdBlankFormRenderUrl">
	<portlet:param name="mvcRenderCommandName" value="/render/esd/form" />
	<portlet:param name="loadRecords" value="false" />
</portlet:renderURL>

<aui:container>
	
	<%@ include file="/nav.jsp" %>
	<div class="row">
		<c:if test="${ESDs.size() == 0 }">
			<div class="col-md-12">
				<table class="table">
					<thead></thead>
					<tbody>
						<tr>
							<td>No Records Found.</td>
						</tr>
					</tbody>
				</table>
				<a class="btn btn-default" href="${CsvUploadUrl }">Click Here to Upload a CSV of Data</a>
			</div>
		</c:if>
		<c:if test="${ESDs.size() > 0 }">
			<div class="col-md-12">
				<h2>${tab.toUpperCase() } INFORMATION</h2><a class="btn btn-default" href="${EsdBlankFormRenderUrl}">Add a New ESD</a>
				<hr />
				<table id="ESDTable" data-toggle="table"
						data-search="true"
						data-show-columns="true"
						data-pagination="true"
					>
						<thead>
							<th data-sortable="true" data-field="code">ESD Code</th>
							<th data-sortable="true" data-field="name">ESD Name</th>
							<th data-sortable="true" data-field="addressLine1">Address Line 1</th>
							<th data-sortable="true" data-field="addressLine2">Address Line 2</th>
							<th data-sortable="true" data-field="email">Email</th>
							<th data-sortable="true" data-field="phone">Phone</th>
							<th data-field="edit">Edit</th>
						</thead>
						<tbody>
							<c:forEach items="${ESDs }" var="esd">
								<tr>
									<%--Create URL to go to esd form based off primaryKey --%>
									<portlet:renderURL var="EsdFormRenderUrl">
										<portlet:param name="mvcRenderCommandName" value="/render/esd/form" />
										<portlet:param name="loadRecords" value="false" />
										<portlet:param name="tab" value="${tab }" />
										<portlet:param name="esdId" value="${esd.primaryKey }" />
									</portlet:renderURL>
									<td>${esd.code }</td>
									<td>${esd.name }</td>
									<td>${esd.addressLine1 }</td>
									<td>${esd.addressLine2 }</td>
									<td>${esd.email }</td>
									<td>${esd.phone }</td>
									<td><a href="${ EsdFormRenderUrl}">Edit</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
		</c:if>
	</div>
</aui:container>