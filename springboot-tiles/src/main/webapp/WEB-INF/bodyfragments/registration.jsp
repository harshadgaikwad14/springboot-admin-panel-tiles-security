
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="register-logo">
	<a href="../../index2.html"><b>Cair</b>Event</a>
</div>

<div class="card">
	<div class="card-body register-card-body">
		<p class="login-box-msg">Register a new membership</p>

		<form:form method="POST" action="${pageContext.request.contextPath}/registration" modelAttribute="userForm" class="form-signin">
			
			
			
			
			
			
			<spring:bind path="username">
				<div class="input-group mb-3 ${status.error ? 'has-error' : ''}">



					<form:input type="text" path="username" class="form-control"
						placeholder="Username" autofocus="true"></form:input>
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-user"></span>
						</div>
					</div>


					<form:errors path="username"></form:errors>
				</div>
			</spring:bind>



			<spring:bind path="password">
				<div class="input-group mb-3 ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="password" class="form-control"
						placeholder="Password"></form:input>
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
					<span><form:errors path="password"></form:errors></span>


				</div>
			</spring:bind>
			<spring:bind path="passwordConfirm">
				<div class="input-group mb-3 ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="passwordConfirm"
						class="form-control" placeholder="Confirm your password"></form:input>
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
					<span><form:errors path="passwordConfirm"></form:errors></span>

				</div>
			</spring:bind>
			<div class="row">
				<div class="col-8">
					<div class="icheck-primary">
						<!--<input type="checkbox" id="agreeTerms" name="terms" value="agree">
						<label for="agreeTerms"> I agree to the <a href="#">terms</a>
						</label>-->
					</div>
				</div>
				<!-- /.col -->
				<div class="col-4">
					<button type="submit" class="btn btn-primary btn-block">Register</button>
				</div>
				<!-- /.col -->
			</div>
		</form:form>


		<a href="${contextPath}/login" class="text-center">I already have
			a membership</a>
	</div>
	<!-- /.form-box -->
</div>
<!-- /.card -->