<%@ include file="/init.jsp" %>

<%--Creates a URL to go to the correct commands. Check commands package and OSGI headers --%>
<portlet:renderURL var="CsvUploadUrl">
	<portlet:param name="mvcRenderCommandName" value="/render/upload" />
	<portlet:param name="loadRecords" value="false" />
</portlet:renderURL>

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
				<h2>Directory Information:</h2>
				<c:forEach items="${records }" var="record">
					<div class="row">
						<div class="col-md-12">
							<a href="#">${esd.esdCode }</a><span> - ${esd.esdName }</span>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
</aui:container>