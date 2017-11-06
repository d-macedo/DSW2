<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Turtle Tickets - Cadastrar Evento</title>

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
						<li><a href="">Cadastrar Usuário</a></li>
						<li><a href="">Aguardando Aprovação</a></li>
						<li><a href="">Balanço Geral</a></li>
						<li><a href="<c:url value="/logout"/>">Logout</a></li>
					</c:if>
					<!-- EXECUTOR -->
					<c:if test="${sessao.tipoUsuario == 'E'}">
						<li><a href="#">Cadastrar Evento</a></li>
						<li><a href="">Meus Eventos</a></li>
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

	<div class="container">

		<div class="row pesquisarIndex">
			<form action="<c:url value="/cadastrar/usuario"/>" method="post">
				<c:forEach var="error" items="${errors}">
					<div class="erro">${error.message}</div>
				</c:forEach>
				
				<div class="col-md-3">
					<input type="text" placeholder="Pesquisar eventos"
						name="nomeEvento">
				</div>
				<div class="col-md-3">
					<input type="text" placeholder="Pesquisar por cidade"
						name="nomeCidade">
				</div>
				<div class="col-md-3">
					<input type="text" placeholder="Pesquisar por data"
						name="dataEvento">
				</div>
				<div class="col-md-3">
					<button>Pesquisar</button>
				</div>
			</form>
		</div>

		<div class="row eventoIndex">
			<form>
				<div class="form-group col-md-12">
					<label for="tituloEvento">Título do Evento</label> <input
						type="text" class="form-control" id="tituloEvento"
						name="tituloEvento" placeholder="Digite o título do Evento">
				</div>
				<div class="form-group col-md-12">
					<label for="descricaoEvento">Descrição do Evento</label>
					<textarea class="form-control" id="descricaoEvento"
						name="descricaoEvento" placeholder="Digite a descrição do Evento"></textarea>
				</div>
				<div class="form-group col-md-12">
					<label for="dataEvento">Data do Evento</label> <input type="date"
						class="form-control" id="dataEvento" name="dataEvento">
				</div>
				<div class="form-group col-md-6">
					<label for="maxIntEvento">Máximo de Ingressos do tipo
						Inteiro do Evento</label> <input type="number" class="form-control"
						id="maxIntEvento" name="maxIntEvento" min=0>
				</div>
				<div class="form-group col-md-6">
					<label for="maxMeiaEvento">Máximo de Ingressos do tipo Meia
						do Evento</label> <input type="number" class="form-control"
						id="maxMeiaEvento" name="maxMeiaEvento" min=0>
				</div>
				<div class="form-group col-md-12">
					<label for="taxaEvento">Taxa do Evento</label> <input type="number"
						class="form-control" id="taxaEvento" name="taxaEvento">
				</div>
				<div class="form-group col-md-6">
					<label for="cidadeEvento">Cidade do Evento</label> <select
						class="form-control" id="cidadeEvento" name="cidadeEvento">
						<option value="Campinas">Campinas</option>
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="estadoEvento">Estado do Evento</label> <select
						class="form-control" id="estadoEvento" name="estadoEvento">
						<option value="São Paulo">São Paulo</option>
					</select>
				</div>
				<div class="form-group col-md-12">
					<label for="categoriaEvento">Categoria do Evento</label> <select
						class="form-control" id="categoriaEvento" name="categoriaEvento">
						<option value="Stand-Up">Stand-Up</option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary">Enviar</button>
			</form>
		</div>
	</div>
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