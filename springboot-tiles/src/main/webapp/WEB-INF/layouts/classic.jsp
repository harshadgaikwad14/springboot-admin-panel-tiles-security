<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bbootstrap 4 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/summernote/summernote-bs4.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">


</head>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">


		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>


<!-- jQuery -->
<script
	src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script
	src="${pageContext.request.contextPath}/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
	$.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script
	src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script
	src="${pageContext.request.contextPath}/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script
	src="${pageContext.request.contextPath}/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script
	src="${pageContext.request.contextPath}/plugins/jqvmap/jquery.vmap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script
	src="${pageContext.request.contextPath}/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script
	src="${pageContext.request.contextPath}/plugins/moment/moment.min.js"></script>
<script
	src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script
	src="${pageContext.request.contextPath}/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script
	src="${pageContext.request.contextPath}/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script
	src="${pageContext.request.contextPath}/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>

<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script
	src="${pageContext.request.contextPath}/dist/js/pages/dashboard.js"></script>




<!-- DataTables -->
<script
	src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script
	src="${pageContext.request.contextPath}/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	
	<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>

<!-- page script -->
<script>
	$(function() {


		
		 //Date range picker
	    $('#eventDate').datetimepicker({
	        format: 'YYYY-MM-DD'
	    });
		
		$('#gridViewSpecialityDataTable').DataTable({
			"paging": true,
		      "lengthChange": false,
		      "searching": false,
		      "ordering": true,
		      "info": true,
		      "autoWidth": false,
		      "responsive": true,
		});

		$('#gridViewSpecialityCaptchaDataTable').DataTable({
			"paging": true,
		      "lengthChange": false,
		      "searching": false,
		      "ordering": true,
		      "info": true,
		      "autoWidth": false,
		      "responsive": true,
		});
		
	});
</script>

</html>