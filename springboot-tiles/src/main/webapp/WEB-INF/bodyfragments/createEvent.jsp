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
				<h1>Create Event</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="${contextPath}/">Dashboard</a></li>
					<li class="breadcrumb-item active">Create Event</li>
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
						<h3 class="card-title">Create New Event</h3>
					</div>
					<!-- /.card-header -->
					<!-- form start -->

					<form:form method="POST"
						action="${pageContext.request.contextPath}/createEvent"
						enctype="multipart/form-data" modelAttribute="createEventForm">

						<div class="card-body">

							<c:if test="${!empty generatedEventName}">

								<div class="alert alert-success alert-dismissible fade show"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>

									Event <strong>${generatedEventName}</strong> Created
									Successfully.
								</div>


							</c:if>


							<spring:bind path="eventName">
								<div class="form-group col-md-6">
									<label for="eventNameLabel">Name</label>


									<form:input type="text" path="eventName" class="form-control"
										placeholder="Enter Event Name"></form:input>
									<form:errors path="eventName"></form:errors>
								</div>
							</spring:bind>

							<spring:bind path="eventDate">
								<div class="form-group col-md-6">
									<label for="eventDateLabel">Date</label>

									<div class="input-group date" id="eventDate"
										data-target-input="nearest">


										<form:input type="text" path="eventDate"
											class="form-control datetimepicker-input"
											data-target="#eventDate" placeholder="Enter Event Date"></form:input>
										<div class="input-group-append" data-target="#eventDate"
											data-toggle="datetimepicker">
											<div class="input-group-text">
												<i class="fa fa-calendar"></i>
											</div>
										</div>
									</div>


									<form:errors path="eventDate"></form:errors>
								</div>
							</spring:bind>


							<spring:bind path="images">
								 <div class="form-group col-md-6">
									<label for="eventImagesLabel">images</label>


									<form:input type="file" path="images" multiple="multiple"
										class="form-control" placeholder="Upload Mutiple Images"></form:input>
									<form:errors path="images"></form:errors>
								</div>
 
								<%-- <div class="form-group col-md-6">
									<label for="eventImagesLabel">Images</label>

									<div class="custom-file">
										<form:input type="file" path="images" multiple="multiple"
											class="custom-file-input" placeholder="Upload Mutiple Images"
											id="images"></form:input>
										<label class="custom-file-label" for="customFile">Choose
											file</label>
										<form:errors path="images"></form:errors>
									</div>
								</div> --%>

							</spring:bind>
						</div>
						<!-- /.card-body -->
						<div class="card-footer">
							<button type="submit" class="btn btn-primary">Create</button>
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
