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
				<h1>View Captcha</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="${contextPath}/">Dashboard</a></li>
					<li class="breadcrumb-item active">View Captcha</li>
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
			<div class="col-12">
				<!-- jquery validation -->
				<div class="card">


					<div class="card-header">
						<h3 class="card-title">Grid View Of Captcha</h3>
						<spring:url value="/createSpeciality" var="createSpeciality" />
						<a class="text-center float-right"
							href="${contextPath}/createSpecialityCaptcha">Create Captcha</a>
					</div>
					<!-- /.card-header -->
					<div class="card-body">

						<c:if test="${message == 'INUSED'}">
							<div class="alert alert-success alert-dismissible fade show"
								role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>

								Captcha Already In Use.
							</div>

						</c:if>

						<c:if test="${message == 'SUCCESS'}">
							<div class="alert alert-success alert-dismissible fade show"
								role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>

								Captcha Successfully deleted.
							</div>

						</c:if>

						<c:if test="${message == 'FAILED'}">
							<div class="alert alert-warning alert-dismissible fade show"
								role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>

								Captcha failed to delete.
							</div>

						</c:if>


						<c:if test="${!empty specialityCaptchaGridData}">
							<table id="gridViewSpecialityCaptchaDataTable"
								class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Id</th>
										<th>Captch Text</th>
										<th>Speciality</th>
										<th>Update</th>
										<th>Delete</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${specialityCaptchaGridData }"
										var="specialityCaptcha">
										<tr>
											<td>${specialityCaptcha.id}</td>
											<td>${specialityCaptcha.captchaText}</td>
											<td>${specialityCaptcha.specialityName}</td>

											<th><spring:url
													value="getSpecialityCaptcha/${specialityCaptcha.id }"
													var="updateURL" /> <a class="btn btn-xs"
												href="${updateURL }"> <i class="fas fa-edit"></i>
											</a></th>
											<th><spring:url
													value="deleteSpecialityCaptcha/${specialityCaptcha.id }"
													var="deleteURL" /> <a class="btn btn-xs"
												href="${deleteURL }"> <i class="fa fa-trash"></i>
											</a></th>
										</tr>

									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Id</th>
										<th>Captch Text</th>
										<th>Speciality</th>
										<th>Update</th>
										<th>Delete</th>

									</tr>
								</tfoot>
							</table>
						</c:if>
						<c:if test="${empty specialityCaptchaGridData}">

							<div class="error-page">


								<div class="error-content">
									<h3>
										<i class="fas fa-exclamation-triangle text-warning"></i> No
										Records Found.
									</h3>

									<p>
										We could not find the records you were looking for. Meanwhile,
										you may <a href="${contextPath}/createSpecialityCaptcha">
											create new captcha</a>
									</p>

								</div>
								<!-- /.error-content -->
							</div>

						</c:if>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->


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
