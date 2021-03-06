<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="../assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Candidatos | Gesti?n</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
	name='viewport' />
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- CSS Files -->
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="../assets/css/now-ui-dashboard.css?v=1.5.0" rel="stylesheet" />
<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="../assets/demo/demo.css" rel="stylesheet" />
</head>

<body class="">
	<div class="wrapper ">
		<div class="sidebar" data-color="orange">
			<!--
        Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red | yellow"
    -->
			<div class="logo">
				<a href="http://www.creative-tim.com" class="simple-text logo-mini">
					CT </a> <a href="http://www.creative-tim.com"
					class="simple-text logo-normal"> Creative Tim </a>
			</div>
			<div class="sidebar-wrapper" id="sidebar-wrapper">
				<ul class="nav">
					<li><a href="/ufps-elecciones/elecciones/"> <i
							class="fas fa-newspaper"></i>
							<p>Elecciones</p>
					</a></li>
					<li><a href="/ufps-elecciones/candidatos/"> <i
							class="fas fa-user-tie"></i>
							<p>Candidatos</p>
					</a></li>
					<li><a href="/ufps-elecciones/votantes/"> <i
							class="fas fa-users"></i>
							<p>Votantes</p>
					</a></li>
				</ul>
			</div>
		</div>
		<div class="main-panel" id="main-panel">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-transparent  bg-primary  navbar-absolute">
				<div class="container-fluid">
					<div class="navbar-wrapper">
						<div class="navbar-toggle">
							<button type="button" class="navbar-toggler">
								<span class="navbar-toggler-bar bar1"></span> <span
									class="navbar-toggler-bar bar2"></span> <span
									class="navbar-toggler-bar bar3"></span>
							</button>
						</div>
						<a class="navbar-brand" href="#pablo">Lista de candidatos</a>
					</div>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navigation" aria-controls="navigation-index"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-bar navbar-kebab"></span> <span
							class="navbar-toggler-bar navbar-kebab"></span> <span
							class="navbar-toggler-bar navbar-kebab"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end"
						id="navigation">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="#pablo">
									<i class="now-ui-icons users_single-02"></i>
									<p>
										<span class="d-lg-none d-md-block">Account</span>
									</p>
							</a></li>
						</ul>
					</div>
				</div>
			</nav>
			<!-- End Navbar -->
			<div class="panel-header panel-header-sm"></div>
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">Candidatos</h4>
							</div>
							<div class="card-body">
								<a href="/ufps-elecciones/candidatos/agregar">
									<p>Registrar nuevo candidato</p>
								</a>
								<div class="table-responsive">
									<c:if test="${empty listaCandidato}">
										<h1>No hay registros disponibles :(</h1>
									</c:if>
									<c:if test="${!empty listaCandidato}">
										<table class="table">
											<thead class=" text-primary">
												<th>Documento</th>
												<th>Nombres</th>
												<th>Apellidos</th>
												<th>Elecci?n</th>
												<th>N?mero</th>
												<th></th>
											</thead>
											<tbody>
												<c:forEach var="candidato" items="${listaCandidato}">
													<tr>
														<td><c:out value="${candidato.documento}" /></td>
														<td><c:out value="${candidato.nombre}" /></td>
														<td><c:out value="${candidato.apellido}" /></td>
														<td><c:out value="${candidato.eleccionBean.nombre}" /></td>
														<td><c:out value="${candidato.numero}" /></td>
														<td><a
															href="/ufps-elecciones/candidatos/editar?id=<c:out value='${candidato.id}'/>"><i
																class="fas fa-edit fa-lg mr-2"></i></a> <a
															href="/ufps-elecciones/candidatos/eliminar?id=<c:out value='${candidato.id}'/>"><i
																class="fas fa-trash fa-lg"></i></a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<footer class="footer">
				<div class=" container-fluid ">
					<nav>
						<ul>
							<li><a href="https://www.creative-tim.com"> Creative Tim
							</a></li>
							<li><a href="http://presentation.creative-tim.com">
									About Us </a></li>
							<li><a href="http://blog.creative-tim.com"> Blog </a></li>
						</ul>
					</nav>
					<div class="copyright" id="copyright">
						&copy;
						<script>
							document.getElementById('copyright').appendChild(
									document.createTextNode(new Date()
											.getFullYear()))
						</script>
						, Designed by <a href="https://www.invisionapp.com"
							target="_blank">Invision</a>. Coded by <a
							href="https://www.creative-tim.com" target="_blank">Creative
							Tim</a>.
					</div>
				</div>
			</footer>
		</div>
	</div>
	<!--   Core JS Files   -->
	<script src="../assets/js/core/jquery.min.js"></script>
	<script src="../assets/js/core/popper.min.js"></script>
	<script src="../assets/js/core/bootstrap.min.js"></script>
	<script src="../assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
	<!-- Chart JS -->
	<script src="../assets/js/plugins/chartjs.min.js"></script>
	<!--  Notifications Plugin    -->
	<script src="../assets/js/plugins/bootstrap-notify.js"></script>
	<!-- Control Center for Now Ui Dashboard: parallax effects, scripts for the example pages etc -->
	<script src="../assets/js/now-ui-dashboard.min.js?v=1.5.0"
		type="text/javascript"></script>
</body>

</html>