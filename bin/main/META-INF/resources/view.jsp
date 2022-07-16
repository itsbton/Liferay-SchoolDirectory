<%@ include file="/init.jsp" %>

<%--Creates a URL to go to the correct commands. Check commands package and OSGI headers --%>
<portlet:renderURL var="CsvUploadUrl">
	<portlet:param name="mvcRenderCommandName" value="/render/upload" />
</portlet:renderURL>

<aui:container>
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="${homeUrl }">
			<img src="${contextPath }/images/eds_icon.png" height="30" class="d-inline-block align-top" alt="">
			Education Directory
		</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="#">Edit</a>
				</li>
			</ul>
		</div>
	</nav>

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