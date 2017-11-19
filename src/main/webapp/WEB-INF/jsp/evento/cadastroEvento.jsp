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
<script src="<c:url value='/resource/js/jquery.mask.min.js' />"></script>

</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#dataEvento').mask('99/99/9999');
		});
	</script>

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
			<form>

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
		<c:if test="${sessao.logado}">

			<!-- GERENTE -->
			<c:if test="${sessao.tipoUsuario == 'G'}">

			</c:if>
			<!-- EXECUTOR -->
			<c:if test="${sessao.tipoUsuario == 'E'}">
				<div class="row eventoIndex">
					<c:forEach var="error" items="${errors}">
						<div class="erro">${error.message}</div>
					</c:forEach>
					<form action="<c:url value="/cadastrar/evento"/>" method="post">
						<div class="col-md-12">
							<h2>Cadastro de Evento</h2>
						</div>

						<div class="form-group col-md-12">
							<label for="tituloEvento">Título do Evento</label> <input
								type="text" class="form-control" id="tituloEvento"
								name="evento.eve_titulo" placeholder="Digite o título do Evento">
						</div>
						<div class="form-group col-md-12">
							<label for="descricaoEvento">Descrição do Evento</label>
							<textarea class="form-control" id="descricaoEvento"
								name="evento.eve_descricao"
								placeholder="Digite a descrição do Evento"></textarea>
						</div>
						<div class="form-group col-md-12">
							<label for="dataEvento">Data do Evento</label> <input type="text"
								class="form-control" id="dataEvento" name="evento.eve_data" placeholder="dd/mm/aaaa">
						</div>
						<div class="form-group col-md-6">
							<label for="maxIntEvento">Máximo de Ingressos do tipo
								Inteiro do Evento</label> <input type="number" class="form-control"
								id="maxIntEvento" name="evento.eve_max_inteira" min=0>
						</div>
						<div class="form-group col-md-6">
							<label for="maxMeiaEvento">Máximo de Ingressos do tipo
								Meia do Evento</label> <input type="number" class="form-control"
								id="maxMeiaEvento" name="evento.eve_max_meia" min=0>
						</div>
						<div class="form-group col-md-6">
							<label for="maxIntEvento">Valor de Ingressos do tipo
								Inteiro do Evento</label> <input type="number" class="form-control"
								id="valorIntEvento" name="evento.eve_valor_inteira" min=0>
						</div>
						<div class="form-group col-md-6">
							<label for="maxMeiaEvento">Valor de Ingressos do tipo
								Meia do Evento</label> <input type="number" class="form-control"
								id="valorMeiaEvento" name="evento.eve_valor_meia" min=0>
						</div>
						<!-- 
				<div class="form-group col-md-12">
					<label for="taxaEvento">Taxa do Evento</label> 
					<input type="number" class="form-control" id="taxaEvento" name="evento.eve_taxa">
				</div>
				 -->
						<div class="form-group col-md-12">
							<label for="cidadeEvento">Cidade do Evento</label> <select
								class="form-control" id="cidadeEvento" name="evento.eve_local">
								<option value="1">Campinas</option>
							</select>
						</div>
						<!-- 
				<div class="form-group col-md-6">
					<label for="estadoEvento">Estado do Evento</label> 
					<select class="form-control" id="estadoEvento" name="estadoEvento">
						<option value="1">São Paulo</option>
					</select>
				</div>
				-->
						<div class="form-group col-md-12">
							<label for="categoriaEvento">Categoria do Evento</label> <select
								class="form-control" id="categoriaEvento"
								name="evento.eve_categoria">
								<option value="1">Stand-Up</option>
								<option value="2">Show Musical</option>
							</select>
						</div>
						<button type="submit" class="btn btn-primary">Enviar</button>
					</form>
				</div>
	</div>
	</div>
	</c:if>
	<!-- COMPRADOR -->
	<c:if test="${sessao.tipoUsuario == 'C'}">

	</c:if>

	</c:if>

	<c:if test="${empty sessao or not sessao.logado}">

	</c:if>


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