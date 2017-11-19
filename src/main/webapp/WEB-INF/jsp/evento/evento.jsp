<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Turtle Tickets - Evento</title>

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

		<div class="row eventoIndex">
			
			<div class="col-md-12 tituloEvento">
				<font>${evento.eve_titulo}</font>
			</div>
			
			<div class="col-md-12 titulo">
				<font>Descrição do Evento</font>
			</div>
			<div class="col-md-12 descricao">
				<p>${evento.eve_descricao}</p>
			</div>
			
			<div class="col-md-12 titulo">
				<font>Data do Evento</font>
			</div> 
			<div class="col-md-12 descricao">
				<p>${evento.eve_data}</p>
			</div>
			
			<div class="col-md-12 titulo">
				<font>Categoria do Evento</font>
			</div>
			<div class="col-md-12 descricao">
				<p>${evento.eve_categoria.cat_descricao}</p>
			</div>
			
			<div class="col-md-12 titulo">
				<font>Local do Evento</font>
			</div>
			
			<div class="col-md-12 descricao">
				<p>${evento.eve_local.loc_nome}</p>
				<p>${evento.eve_local.loc_endereco}, ${evento.eve_local.loc_cidade.cid_abrev} - ${evento.eve_local.loc_cidade.cid_estado.est_sigla }</p>
			</div>
			
			<div class="col-md-6 titulo">
				<font>Valor Inteira</font>
			</div>
			<div class="col-md-6 titulo">
				<font>Valor Meia</font>
			</div>
			
			<div class="col-md-6 descricao">
				<p>R$ ${evento.eve_valor_inteira}</p>
			</div>
			<div class="col-md-6 descricao">
				<p>R$ ${evento.eve_valor_meia}</p>
			</div>
			
		</div>
		
		<div class="row eventoIndex">
		
			<div class="col-md-12 titulo">
				
				<c:if test="${empty sessao or not sessao.logado}">
					<font>Faça login para mais informações.</font>
				</c:if>
				
				<c:if test="${sessao.logado}">
					<c:if test="${(evento.eve_status == 1) and (sessao.tipoUsuario == 'G')}">
						<button type="submit" class="btn btn-success">Aprovar Evento</button>
						<button type="submit" class="btn btn-danger">Não Aprovar Evento</button>
					</c:if>
					
					<c:if test="${(evento.eve_status == 1) and (sessao.tipoUsuario != 'G')}">
						<font>Evento aguardando aprovação.</font>
					</c:if>
					
					<c:if test="${(evento.eve_status == 2) and (sessao.tipoUsuario == 'G')}">
						<button type="submit" class="btn btn-success">Aprovar Evento</button>
					</c:if>
					
					<c:if test="${(evento.eve_status == 2) and (sessao.tipoUsuario != 'G')}">
						<font>Evento não aprovado pela gerência.</font>
					</c:if>
					
					<c:if test="${(evento.eve_status == 3) and (sessao.tipoUsuario == 'G')}">
						<button type="submit" class="btn btn-danger">Não Aprovar Evento</button>
					</c:if>
					
					<c:if test="${(evento.eve_status == 3) and (sessao.tipoUsuario == 'E') and (sessao.id == evento.eve_executor)}">
						<button type="submit" class="btn btn-danger">Cancelar Evento</button>
					</c:if>
					
					<c:if test="${(evento.eve_status == 3) and (sessao.tipoUsuario == 'E') and (sessao.id != evento.eve_executor)}">
						<font>Você não é o executor deste evento.</font>
					</c:if>
					
					<c:if test="${(evento.eve_status == 3) and (sessao.tipoUsuario == 'C')}">
						<button type="submit" class="btn btn-primary">Comprar Ingresso</button>
					</c:if>
					
					<c:if test="${(evento.eve_status == 4)}">
						<font>Evento Cancelado</font>
					</c:if>
					
					<c:if test="${(evento.eve_status == 5)}">
						<font>Evento Finalizado</font>
					</c:if>
				</c:if>
				
			</div>
			
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