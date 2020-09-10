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
				<h1>Captcha</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="${contextPath}/">Dashboard</a></li>
					<li class="breadcrumb-item active">Update Captcha</li>
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
						<h3 class="card-title">Update Captcha</h3>
					</div>
					<!-- /.card-header -->
					<!-- form start -->

					<form:form method="POST"
						action="${pageContext.request.contextPath}/updateSpecialityCaptcha"
						enctype="multipart/form-data"
						modelAttribute="updateSpecialityCaptchaForm">

						<div class="card-body">

							<c:if test="${!empty updatedCaptchaName}">

								<div class="alert alert-success alert-dismissible fade show"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>

									Captcha <strong>${updatedCaptchaName}</strong> Updated
									Successfully.
								</div>


							</c:if>

							<div class="row">
								<div class="col-md-6">

									<spring:bind path="id">
										<div class="form-group">
											<label for="specialityCaptchId">Id</label>
											<form:hidden path="id" />

											<form:input type="text" path="id" class="form-control"
												disabled="true"></form:input>
											<form:errors path="id"></form:errors>
										</div>
									</spring:bind>

									<spring:bind path="captchaText">
										<div class="form-group">
											<label for="captchaText">Captcha Text</label>


											<form:input type="text" path="captchaText" id="captchaText"
												class="form-control" placeholder="Enter Captcha Text"></form:input>


											<form:errors path="captchaText"></form:errors>
										</div>
									</spring:bind>

									<spring:bind path="speciality">
										<div class="form-group">
											<label for="specialityLabel">Select Speciality</label>

											<form:select path="speciality" class="form-control">
												<form:option value="-1" label="--- Please Select ---" />
												<form:options items="${specialityMap}" />
											</form:select>



											<form:errors path="speciality"></form:errors>
										</div>
									</spring:bind>


									<spring:bind path="image">
										<div class="form-group">
											<label for="imageLabel">Image</label>


											<form:input type="file" path="image" class="form-control"
												placeholder="Upload Image"></form:input>
											<form:errors path="image"></form:errors>
										</div>



									</spring:bind>
								</div>
								<div class="col-md-6">


									<label for="imageLabel">Uploaded Captcha</label>


									<div class="row text-center text-lg-left">


										<%-- <div class="col-lg-8 col-md-4 col-6">
											<img class="img-fluid img-thumbnail"
												src="${pageContext.request.contextPath}${updateSpecialityCaptchaForm.captchaImagePath}"
												alt="">
										</div> --%>
										
										<div class="col-lg-8 col-md-4 col-6">
											<img class="img-fluid img-thumbnail"
												src="${updateSpecialityCaptchaForm.captchaImagePath}"
												alt="">
										</div>

									</div>




								</div>
							</div>






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
