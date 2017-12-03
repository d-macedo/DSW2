<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Turtle Tickets</title>

<link rel="stylesheet" type="text/css" href="<c:url value='/resource/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resource/css/bootstrap-theme.min.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resource/css/turtletickets.css'/>">

<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Montserrat:400,700'>
<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<script src="<c:url value='/resource/js/jquery-3.2.1.min.js' />"></script>
<script src="<c:url value='/resource/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/resource/js/jquery.mask.min.js' />"></script>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#dataPesquisa').mask('99/99/9999');
	});
</script>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"><img src="<c:url value='/resource/img/turtle-logo-transp-teste.png' />" height="56px"></a>
			<ul class="nav navbar-nav">
				<li><a href="<c:url value='/' />">Turtle Tickets</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${sessao.logado}">
				
					<!-- GERENTE -->
					<c:if test="${sessao.tipoUsuario == 'G'}">
						<li><a href="<c:url value="/cadastrar/usuario" />">Cadastrar Usuário</a></li> 
						<li><a href="evento/pendentes">Aguardando Aprovação (${numeroPendentes})</a></li> 
						<li><a href="">Balanço Geral</a></li>
			    			<li><a href="<c:url value="/logout"/>">Logout</a></li>
					</c:if>
					
					<!-- EXECUTOR -->
					<c:if test="${sessao.tipoUsuario == 'E'}">
						<li><a href="<c:url value="/cadastrar/evento" />">Cadastrar Evento</a></li> 
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
					<li><a href="<c:url value='/cadastrar/usuario' />"><span class="glyphicon glyphicon-user"></span> Cadastre-se</a></li>
					<li><a href="<c:url value='/login' />"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			    </c:if>
			    
			</ul>
		</div>
	</nav>
	
	<div class="container">
	
		<div class="row pesquisarIndex">
			<form method="post" action="<c:url value="/" />">
				<div class="col-md-3">
					<input type="text" placeholder="Pesquisar eventos" name="evento">
				</div>
				<div class="col-md-3"> 	
					<select name="cidade">
						<option value="0">Pesquisar por cidade</option>
						<c:forEach items="${cidades}" var="cidade">
							<option value="${cidade.cid_cod}">${cidade.cid_nome} - ${cidade.cid_estado.est_sigla}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-3">
					<input type="text" id="dataPesquisa" name="data" placeholder="Pesquise por Data">
				</div>
				<div class="col-md-3">
					<button type="submit">Pesquisar</button>
				</div>
			</form>
		</div>
		
		
		
		<c:if test="${empty eventos}">
		<div class="row eventoIndex">
			<div class="col-md-12 titulo">
				<h2>Vai dar bom!</h2>
			</div>
			
			<div class="col-md-5 descricao">
				<p>Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.</p>
				<div>
					<a href="" class="link">Visualizar evento...</a>
				</div>			
			</div>
			
			<div class="col-md-7 imagem">
				<img src="<c:url value='/resource/img/turtle-logo-transp-teste.png' />">
			</div>
		</div>
		</c:if>
		
		<c:if test="${not empty eventos}">
		<c:forEach items="${eventos}" var="evento">
		<div class="row eventoIndex">
			<div class="col-md-12 titulo">
				<h2>${evento.eve_titulo}</h2>
				<p>${evento.eve_data}</p>
			</div>
			
			<div class="col-md-5 descricao">
				<p>${evento.eve_descricao}</p>
				<div>
					<a href="<c:url value="/evento/${evento.eve_cod}"/>" class="link">Visualizar evento...</a>
				</div>			
			</div>
		</div>	
		</c:forEach>
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
						<li>
							Eventos
						</li>
					</ul>
				</div>
				
				<div class="col-md-6"></div>
			</div>
		</div>
	</footer>
</body>
</html>
			    			