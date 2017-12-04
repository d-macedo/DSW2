<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Turtle Tickets - Balanço Geral</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource/css/bootstrap-theme.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource/css/turtletickets.css'/>">

<link rel='stylesheet prefetch'
	href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
<link rel='stylesheet prefetch'
	href='https://fonts.googleapis.com/css?family=Montserrat:400,700'>
<link rel='stylesheet prefetch'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<script src="<c:url value='/resource/js/jquery-3.2.1.min.js' />"></script>
<script src="<c:url value='/resource/js/bootstrap.min.js' />"></script>

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"><img
				src="<c:url value='/resource/img/turtle-logo-transp-teste.png' />"
				height="56px"></a>
			<ul class="nav navbar-nav">
				<li><a href="<c:url value='/' />">Turtle Tickets</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${sessao.logado}">

						<!-- GERENTE -->
					<c:if test="${sessao.tipoUsuario == 'G'}">
						<li><a href="<c:url value="/cadastrar/usuario" />">Cadastrar Usuário</a></li> 
						<li><a href="<c:url value="/evento/pendentes" />">Aguardando Aprovação (${numeroPendentes})</a></li> 
						<li><a href="">Balanço Geral</a></li>
			    			<li><a href="<c:url value="/logout"/>">Logout</a></li>
					</c:if>
					
					<!-- EXECUTOR -->
					<c:if test="${sessao.tipoUsuario == 'E'}">
						<li><a href="<c:url value="/cadastrar/evento" />">Cadastrar Evento</a></li> 
						<li><a href="<c:url value="#" />">Meus Eventos</a></li> 
					<li><a href="<c:url value="/logout"/>">Logout</a></li>
					</c:if>

					<!-- COMPRADOR -->
					<c:if test="${sessao.tipoUsuario == 'C'}">
						<li><a href="">Olá, ${sessao.nome}!</a></li>
						<li><a href="<c:url value="/logout"/>">Logout</a></li>
					</c:if>

				</c:if>

				<c:if test="${empty sessao or not sessao.logado}">
					<li><a href="<c:url value='/cadastrar/usuario' />"><span
							class="glyphicon glyphicon-user"></span> Cadastre-se</a></li>
					<li><a href="<c:url value='/login' />"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</c:if>

			</ul>
		</div>
	</nav>

		
			<c:if test="${empty sessao or not sessao.logado}">
			<div class="row eventoIndex">
				<div class="col-md-12 titulo">
					<p>Você não tem permissão para acessar esta página.</p>
				</div>
			</div>
		</c:if>
		
		<c:if test="${sessao.logado}">
			<c:if test="${sessao.tipoUsuario != 'G'}">
				<div class="row eventoIndex">
					<div class="col-md-12 titulo">
						<p>Você não tem permissão para acessar esta página.</p>
					</div>
				</div>
			</c:if>
		</c:if>
		
		<div class="container">
		
		<c:if test="${sessao.logado}">
			<c:if test="${sessao.tipoUsuario == 'G'}">
				<div class="row eventoIndex">
					<div class="col-md-12 tituloEvento">
						<font>Balanço Geral</font>
					</div>
				</div>
				<c:forEach items="${balancos}" var="evento">
					<a href="../evento/${evento.eve_cod}">
						<div class="row eventoIndex">
			
								<div class="col-md-12 tituloEvento">
									<font>${evento.eve_titulo}</font>
								</div>
								
								<div class="col-md-12 titulo">
									<font>Ingressos Inteira</font>
								</div>
								<div class="col-md-6 descricao">
									<p>${evento.eve_max_inteira}</p>
								</div>
								
								<div class="col-md-12 titulo">
									<font>Ingressos Meia</font>
								</div>
								<div class="col-md-12 descricao">
									<p>${evento.eve_max_meia}</p>
								</div>
								
								<div class="col-md-12 titulo">
									<font>Ingressos Inteira Vendidos</font>
								</div>
								<div class="col-md-6 descricao">
									<p>${evento.total_inteiras}</p>
								</div>
								
								<div class="col-md-12 titulo">
									<font>Ingressos Meia Vendidos</font>
								</div>
								<div class="col-md-6 descricao">
									<p>${evento.total_meia}</p>
								</div>
								
								<div class="col-md-12 titulo">
									<font>Porcentagem Vendida</font>
								</div>
								<div class="col-md-6 descricao">
									<p>${((evento.total_inteiras + evento.total_meia) / (evento.eve_max_inteira + evento.eve_max_meia))}</p>
								</div>
						</div>
					</a>
				</c:forEach>
			</c:if>
		</c:if>
		
	</div>
	
	<footer class="rodape">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2 col-md-offset-2">
					<ul>
						<li>Acesso ao sistema</li>
					</ul>
				</div>

				<div class="col-md-2">
					<ul>
						<li>Eventos</li>
					</ul>
				</div>

				<div class="col-md-6"></div>
			</div>
		</div>
	</footer>
</body>
</html>