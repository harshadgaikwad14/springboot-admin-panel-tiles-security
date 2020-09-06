

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="index3.html" class="brand-link"> <img
		src="${pageContext.request.contextPath}/dist/img/cairevent.jpg"
		alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
		style="opacity: .8"> <span class="brand-text font-weight-light">CairEvent</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->

		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->

				<li class="nav-item"><a
					href="${pageContext.request.contextPath}/" class="nav-link"> <i
						class="nav-icon fas fa-tachometer-alt"></i>
					<p>Dashboard</p>
				</a></li>

				<li class="nav-item has-treeview"><a href="#" class="nav-link">
						<i class="nav-icon far fa-calendar-alt"></i>
						<p>
							Event <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="../layout/top-nav.html"
							class="nav-link"> <i class="far fa-file"></i>
								<p>Create</p>
						</a></li>
						
						<li class="nav-item"><a href="../layout/boxed.html"
							class="nav-link"> <i class="far fa-list-alt"></i>
								<p>List</p>
						</a></li>

					</ul></li>

				<li class="nav-item has-treeview"><a href="#" class="nav-link">
						<i class="fas fa-graduation-cap"></i>
						<p>
							Speciality<i class="fas fa-angle-left right"></i>

						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="${pageContext.request.contextPath}/createSpeciality"
							class="nav-link"> <i class="far fa-file"></i>
								<p>Create</p>
						</a></li>
						
						<li class="nav-item"><a href="${pageContext.request.contextPath}/gridViewSpeciality"
							class="nav-link"> <i class="far fa-list-alt"></i>
								<p>List</p>
						</a></li>

					</ul></li>


			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>


