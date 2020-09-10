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
				<h1>DashBoard</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
				
					<li class="breadcrumb-item active">Dashboard</li>
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
						<h3 class="card-title">Grid View Of Dashboard</h3>
						
					</div>
					<!-- /.card-header -->
					<div class="card-body">


						<c:if test="${!empty eventShareGridData}">
							<table id="gridViewEventShareGridDataTable"
								class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>EventShareId</th>
										<th>EventShareDate</th>
										<th>EventName</th>
										<th>EventDate</th>
										<th>ClientName</th>
										<th>EventShareCount</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${eventShareGridData }" var="eventShare">
										<tr>
											<td>${eventShare.eventShareId}</td>
											<td>${eventShare.eventShareDate}</td>
											<td>${eventShare.eventName}</td>
											<td>${eventShare.eventDate}</td>
											<td>${eventShare.drName}</td>
											<td>${eventShare.eventShareCount}</td>
											
											
										</tr>

									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>EventShareId</th>
										<th>EventShareDate</th>
										<th>EventName</th>
										<th>EventDate</th>
										<th>ClientName</th>
										<th>EventShareCount</th>

									</tr>
								</tfoot>
							</table>
						</c:if>
						<c:if test="${empty eventShareGridData}">

							<div class="error-page">
								

								<div class="error-content">
									<h3>
										<i class="fas fa-exclamation-triangle text-warning"></i> No Records Found.
									</h3>

									<p>
										We could not find the records you were looking for.</a> 
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
