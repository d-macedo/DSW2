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

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"><img src="<c:url value='/resource/img/turtle-logo-transp-teste.png' />" height="56px"></a>
			<ul class="nav navbar-nav">
				<li><a href="<c:url value='/' />">Turtle Tickets</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${usuarioSession.logado}">
			    		<li><a href="">Olá, ${usuarioSession.nome }!</a></li> 
			    		<li><a href="<c:url value="/logout"/>">Logout</a></li>
			    </c:if>
			    
			    <c:if test="${not usuarioSession.logado}">
			      	<li><a href="">Você não está logado. ${usuarioSession.nome}</a></li> 
					<li><a href="<c:url value='/cadastrar/usuario' />"><span class="glyphicon glyphicon-user"></span> Cadastre-se</a></li>
					<li><a href="<c:url value='/login' />"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			    </c:if>
			    
			</ul>
		</div>
	</nav>
	
	<div class="container">
	
		<div class="row pesquisarIndex">
			<form method="get" action="">
				<div class="col-md-3">
					<input type="text" placeholder="Pesquisar eventos" name="nomeEvento">
				</div>
				<div class="col-md-3">
					<input type="text" placeholder="Pesquisar por cidade" name="nomeCidade">
				</div>
				<div class="col-md-3">
					<input type="text" placeholder="Pesquisar por data" name="dataEvento">
				</div>
				<div class="col-md-3">
					<button>Pesquisar</button>
				</div>
			</form>
		</div>
		
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