
<%--Create URL to go to a districts dashboard --%>
<portlet:renderURL var="DistrictsDashboardRenderUrl">
	<portlet:param name="mvcRenderCommandName" value="/render/districtsSchools/dashboard" />
	<portlet:param name="tab" value="District" />
</portlet:renderURL>

<%--Create URL to go to a schools dashboard --%>
<portlet:renderURL var="SchoolsDashboardRenderUrl">
	<portlet:param name="mvcRenderCommandName" value="/render/districtsSchools/dashboard" />
	<portlet:param name="tab" value="School" />
</portlet:renderURL>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
	<a class="navbar-brand" href="${homeUrl }">
		<img src="${contextPath }/images/eds_icon.png" height="30" class="d-inline-block align-top" alt="">
		Education Directory
	</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item ${tab eq 'esd' ? 'active' : '' }">
				<a class="nav-link" href="${homeUrl }">ESDs</a>
			</li>
			<li class="nav-item ${tab eq 'District' ? 'active' : '' }">
				<a class="nav-link" href="${DistrictsDashboardRenderUrl }">Districts</a>
			</li>
			<li class="nav-item ${tab eq 'School' ? 'active' : '' }">
				<a class="nav-link" href="${SchoolsDashboardRenderUrl }">Schools</a>
			</li>
		</ul>
	</div>
</nav>