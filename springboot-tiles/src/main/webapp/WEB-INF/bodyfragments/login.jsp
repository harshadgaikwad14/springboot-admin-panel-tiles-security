

<div class="login-logo">
	<a href="../../index2.html"><b>Cair</b>Event</a>
</div>
<!-- /.login-logo -->
<div class="card">
	<div
		class="card-body login-card-body ${error != null ? 'has-error' : ''}">
		<p class="login-box-msg">Sign in to start your session</p>

		<!-- <form action="../../index3.html" method="post"> -->
		<form method="POST" action="${pageContext.request.contextPath}/admin/panel/login"
			class="form-signin">
			<div class="input-group mb-3">
				<input type="text" name="username" class="form-control"
					placeholder="User Name" autofocus="true">
				<div class="input-group-append">
					<div class="input-group-text">
						<span class="fas fa-user"></span>
					</div>
				</div>
			</div>
			<div class="input-group mb-3">
				<input name="password" type="password" class="form-control"
					placeholder="Password">
				<div class="input-group-append">
					<div class="input-group-text">
						<span class="fas fa-lock"></span>
					</div>
				</div>
				<span>${error}</span> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
			</div>
			<div class="row">
				<div class="col-8">
					<div class="icheck-primary">
						<input type="checkbox" id="remember"> <label
							for="remember"> Remember Me </label>
					</div>
				</div>
				<!-- /.col -->
				<div class="col-4">
					<button type="submit" class="btn btn-primary btn-block">Sign
						In</button>
				</div>
				<!-- /.col -->
			</div>
		</form>



		<!-- <p class="mb-1">
			<a href="forgot-password.html">I forgot my password</a>
		</p> -->
		<p class="mb-0">
			<a href="${pageContext.request.contextPath}/admin/panel/registration"
				class="text-center">Register a new membership</a>
		</p>
	</div>
	<!-- /.login-card-body -->
</div>
