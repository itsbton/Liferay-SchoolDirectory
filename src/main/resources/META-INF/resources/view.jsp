<%@ include file="/init.jsp" %>

<%--Creates a URL to go to the correct commands. Check commands package and OSGI headers --%>
<portlet:renderURL var="CsvUploadUrl">
	<portlet:param name="mvcRenderCommandName" value="/render/upload" />
	<portlet:param name="loadRecords" value="false" />
</portlet:renderURL>

<aui:container>
	
	<%@ include file="/nav.jsp" %>

	<%--Display directory information below --%>
	<%-- On init we start with ESDs, but selection can be changed based on the dropdown --%>
	<c:if test="${ESDs.size() == 0 }">
		<div class="row">
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
		</div>
	</c:if>

</aui:container>