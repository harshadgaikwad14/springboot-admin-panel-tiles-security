<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Navbar -->
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
	<!-- Left navbar links -->
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
			href="#" role="button"><i class="fas fa-bars"></i></a></li>
		<li class="nav-item d-none d-sm-inline-block"><a
			href="${contextPath}/" class="nav-link">Home</a></li>
		

		<%-- <li class="nav-item d-none d-sm-inline-block"><c:if
				test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <a
						onclick="document.forms['logoutForm'].submit()" class="nav-link">Logout</a>
				</form>


			</c:if></li> --%>

	</ul>

	<%-- <c:if test="${pageContext.request.userPrincipal.name != null}">
		<div class="input-group input-group-sm">

			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<!-- <a
					onclick="document.forms['logoutForm'].submit()" class="nav-link">Logout</a> -->

				<!-- <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button> -->

				<a onclick="document.forms['logoutForm'].submit()"
					class="btn btn-default btn-flat float-right">Sign out</a>
			</form>

		</div>
	</c:if> --%>

	<c:if test="${pageContext.request.userPrincipal.name != null}">

		<!-- Right navbar links -->
		<ul class="navbar-nav ml-auto">

			<li class="nav-item dropdown user-menu"><a href="#"
				class="nav-link dropdown-toggle" data-toggle="dropdown"> <img
					src="${pageContext.request.contextPath}/dist/img/user2-160x160.jpg"
					class="user-image img-circle elevation-2" alt="User Image"> <span
					class="d-none d-md-inline">${pageContext.request.userPrincipal.name}</span>
			</a>
				<ul class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
					<!-- User image -->
					<li class="user-header bg-primary"><img
						src="${pageContext.request.contextPath}/dist/img/user2-160x160.jpg"
						class="img-circle elevation-2" alt="User Image">

						<p>${pageContext.request.userPrincipal.name}</p></li>

					<!-- Menu Footer-->
					<li class="user-footer">
						<form id="logoutForm" method="POST" action="${contextPath}/logout">
							<a href="#" class="btn btn-default btn-flat">Profile</a> <a
								onclick="document.forms['logoutForm'].submit()"
								class="btn btn-default btn-flat float-right">Sign out</a> <input
								type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</li>

				</ul></li>
		</ul>

	</c:if>
</nav>
<!-- /.navbar -->