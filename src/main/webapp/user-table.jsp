<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="plugins/images/favicon.png">
<title>Pixel Admin</title>
<!-- Bootstrap Core CSS -->
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Menu CSS -->
<link
	href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<!-- animation CSS -->
<link href="css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
<link rel="stylesheet" href="./css/custom.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<!-- Preloader -->
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>
	<div id="wrapper">
		<!-- Navigation -->
		<jsp:include page="HeaderPage.jsp" />
		<!-- Left navbar-header -->
		<jsp:include page="LeftNavbarHeader.jsp" />
		<!-- Left navbar-header end -->

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row bg-title">
					<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						<h4 class="page-title">Danh sách thành viên</h4>
					</div>
					<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
						<a href="add_user" class="btn btn-sm btn-success">Thêm mới</a>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /row -->
				<div class="row">
					<div class="col-sm-12">
						<div class="white-box">
							<div class="table-responsive">
								<table class="table" id="example">
									<thead>
										<tr>
											<th>ID</th>
											<th>First Name</th>
											<th>Last Name</th>
											<th>Username</th>
											<th>Role</th>
											<th>Action</th>
										</tr>
									</thead>


									<tbody>
										<c:forEach var="item" items="${usersLista}">
											<tr>
												<td>${item.id}</td>
												<td>${item.fName}</td>
												<td>${item.lName}</td>
												<td>${item.uName}</td>
												<td>${item.roleEntity.nameString}</td>
												<td><a href="edit_user?id=${item.id}"
													class="btn btn-sm btn-primary">Sửa</a> <a href="#"
													id-user="${item.id}" class="btn-xoa btn btn-sm btn-danger">Xóa</a>
													<a href="detail_user?idU=${item.id}"
													class="btn btn-sm btn-info">Xem</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>



				<div class="container-fluid p-0">
					<div class="row">
						<div class="col-xl-6 col-md-6 col-sm-12">
							<nav aria-label="Page navigation">
								<ul class="pagination">
									<c:if test="${numberpage == 1}">
										<li class="page-item"><a class="page-link" href="#"
											aria-label="Previous"><span aria-hidden="true"><i
													class="fa fa-chevron-left" aria-hidden="true"></i></span> <span
												class="sr-only">Previous</span></a></li>
									</c:if>

									<c:if test="${numberpage != 1}">
										<li class="page-item"><a class="page-link"
											href="user?pageeid=${numberpage-1}"
											aria-label="Previous"><span aria-hidden="true"><i
													class="fa fa-chevron-left" aria-hidden="true"></i></span> <span
												class="sr-only">Previous</span></a></li>
									</c:if>




									<c:forEach begin="1" end="${maxpageid}" var="num">
										<li class="page-item active"><a class="page-link"
											href="user?pageeid=${num}">${num}</a></li>
									</c:forEach>
									<c:if test="${numberpage == maxpageid}">
										<li class="page-item"><a class="page-link" href="#"
											aria-label="Next"><span aria-hidden="true"><i
													class="fa fa-chevron-right" aria-hidden="true"></i></span> <span
												class="sr-only">Next</span></a></li>

									</c:if>

									<c:if test="${numberpage != maxpageid}">
										<li class="page-item"><a class="page-link"
											href="user?pageeid=${numberpage+1}"
											aria-label="Next"><span aria-hidden="true"><i
													class="fa fa-chevron-right" aria-hidden="true"></i></span> <span
												class="sr-only">Next</span></a></li>

									</c:if>
								</ul>
							</nav>
						</div>

					</div>
				</div>



				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
			<footer class="footer text-center"> 2018 &copy; myclass.com
			</footer>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<!--slimscroll JavaScript -->
	<script src="js/jquery.slimscroll.js"></script>
	<script src="js/jquery.dataTables.js"></script>
	<!--Wave Effects -->
	<script src="js/waves.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="js/custom.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>

	<script src="js/user-table.js"></script>
</body>

</html>