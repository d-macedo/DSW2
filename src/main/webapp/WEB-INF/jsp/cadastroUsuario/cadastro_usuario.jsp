<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="<c:url value='/resource/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resource/css/bootstrap-theme.min.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resource/css/turtletickets.css'/>">

<title>Cadastro Usuário</title>
</head>
<body class="body-cadastro-usuario">

	<div class="titulo-cadastro-usuario">
		<h1>Cadastro de Usuário</h1>
	</div>
	<div class="container">
		<form class="form-cadastro-usuario">
			<div class="form-group">
		    	<label for="email">Endereço de Email</label>
		    	<input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Email">
		    <small id="emailHelp" class="form-text text-muted">Nós nunca compartilharemos o seu email com ninguém.</small>
			</div>
		  	<div class="form-group">
		    	<label for="password">Senha</label>
		    	<input type="password" class="form-control" id="password" placeholder="Senha">
		  	</div>

		  	<div class="form-group">
		    	<label for="name">Nome</label>
		    	<input type="text" class="form-control" id="name" placeholder="Nome">
		  	</div>

		  	<div class="form-group">
		    	<label for="tipo">Tipo</label>
		    		<select class="form-control" id="tipo" placeholder="Tipo">
		    			<option value="E">Executor</option>
		    			<option value="G">Gerente</option>
		    		</select>
		  	</div>
		  	<button type="submit" class="btn">Cadastrar-se</button>
		</form>
	</div>
</body>
</html>