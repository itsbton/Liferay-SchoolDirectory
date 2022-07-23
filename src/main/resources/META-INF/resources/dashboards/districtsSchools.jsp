<%@ include file="/init.jsp" %>

<%--Creates a URL to go to the correct commands. Check commands package and OSGI headers --%>
<portlet:renderURL var="CsvUploadUrl">
	<portlet:param name="mvcRenderCommandName" value="/render/upload" />
	<portlet:param name="loadRecords" value="false" />
</portlet:renderURL>

<c:if test="${tab eq 'District' }">
	<%--Create URL to go to a blank district form --%>
	<portlet:renderURL var="BlankFormRenderUrl">
		<portlet:param name="mvcRenderCommandName" value="/render/district/form" />
		<portlet:param name="loadRecords" value="false" />
	</portlet:renderURL>
</c:if>
<c:if test="${tab eq 'School' }">
	<%--Create URL to go to a blank school form --%>
	<portlet:renderURL var="BlankFormRenderUrl">
		<portlet:param name="mvcRenderCommandName" value="/render/school/form" />
		<portlet:param name="loadRecords" value="false" />
	</portlet:renderURL>
</c:if>

<aui:container>
	
	<%@ include file="/nav.jsp" %>
	<div class="row">
		<c:if test="${records.size() == 0 }">
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
		<c:if test="${records.size() > 0 }">
			<div class="col-md-12">
				<h2>${tab.toUpperCase() } INFORMATION</h2><a class="btn btn-default" href="${BlankFormRenderUrl}">Add a New ${tab}</a>
				<hr />
				<table id="recordTable" data-toggle="table"
						data-search="true"
						data-show-columns="true"
						data-pagination="true"
					>
						<thead>
							<th data-sortable="true" data-field="code">${tab } Code</th>
							<th data-sortable="true" data-field="name">${tab } Name</th>
							<th data-sortable="true" data-field="addressLine1">Address Line 1</th>
							<th data-sortable="true" data-field="addressLine2">Address Line 2</th>
							<th data-sortable="true" data-field="email">Email</th>
							<th data-sortable="true" data-field="phone">Phone</th>
							<th data-field="edit">Edit</th>
						</thead>
						<tbody>
							<c:forEach items="${records }" var="record">
								<tr>
									<c:if test="${tab eq 'District' }">
										<%--Create URL to go to district form based off primaryKey --%>
										<portlet:renderURL var="FormRenderUrl">
											<portlet:param name="mvcRenderCommandName" value="/render/district/form" />
											<portlet:param name="loadRecords" value="false" />
											<portlet:param name="districtId" value="${record.primaryKey }" />
										</portlet:renderURL>
									</c:if>
									<c:if test="${tab eq 'School' }">
										<%--Create URL to go to school form based off primaryKey --%>
										<portlet:renderURL var="FormRenderUrl">
											<portlet:param name="mvcRenderCommandName" value="/render/school/form" />
											<portlet:param name="loadRecords" value="false" />
											<portlet:param name="schoolId" value="${record.primaryKey }" />
										</portlet:renderURL>
									</c:if>
									<td>${record.code }</td>
									<td>${record.name }</td>
									<td>${record.addressLine1 }</td>
									<td>${record.addressLine2 }</td>
									<td>${record.email }</td>
									<td>${record.phone }</td>
									<td><a href="${ FormRenderUrl}">Edit</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
		</c:if>
	</div>
</aui:container>