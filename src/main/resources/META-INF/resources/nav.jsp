

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
			<li class="nav-item ${tab eq 'district' ? 'active' : '' }">
				<a class="nav-link" href="#">Districts</a>
			</li>
			<li class="nav-item ${tab eq 'school' ? 'active' : '' }">
				<a class="nav-link" href="#">Schools</a>
			</li>
		</ul>
	</div>
</nav>