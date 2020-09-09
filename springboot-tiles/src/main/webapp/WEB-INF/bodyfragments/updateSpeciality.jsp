<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!-- Content Header (Page header) -->
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>Speciality</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="${contextPath}/">Dashboard</a></li>
					<li class="breadcrumb-item active">Update Speciality</li>
				</ol>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
</section>

<!-- Main content -->
<section class="content">
	<div class="container-fluid">
		<div class="row">
			<!-- left column -->
			<div class="col-md-12">
				<!-- jquery validation -->
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">Update Specicality</h3>
					</div>
					<!-- /.card-header -->
					<!-- form start -->

					<form:form method="POST"
						action="${pageContext.request.contextPath}/updateSpeciality"
						modelAttribute="updateSpecialityForm">

						<div class="card-body">

							<c:if test="${!empty specialityName}">

								<div class="alert alert-success alert-dismissible fade show"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>

									Speciality <strong>${specialityName}</strong> Updated
									Successfully.
								</div>


							</c:if>

							<spring:bind path="id">
								<div class="form-group col-md-6">
									<label for="specialityId">Id</label>
									<form:hidden path="id" />

									<form:input type="text" path="id" class="form-control"
										disabled="true"></form:input>
									<form:errors path="id"></form:errors>
								</div>
							</spring:bind>

							<spring:bind path="name">
								<div class="form-group col-md-6">
									<label for="specialityName">Name</label>


									<form:input type="text" path="name" class="form-control"
										placeholder="Enter Speciality Name"></form:input>
									<form:errors path="name"></form:errors>
								</div>
							</spring:bind>
						</div>
						<!-- /.card-body -->
						<div class="card-footer">
							<button type="submit" class="btn btn-primary">Update</button>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form:form>
				</div>
				<!-- /.card -->
			</div>
			<!--/.col (left) -->
			<!-- right column -->

			<!--/.col (right) -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container-fluid -->
</section>
<!-- /.content -->
