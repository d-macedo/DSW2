<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resource/css/login.css'/>">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Montserrat:400,700'>
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	
	<title>Login</title>
</head>
<body>
	<div class="container">
		<div class="info">
    		<img src="<c:url value="/resource/img/titulo.png"/>">
  		</div>
	</div>
	<div class="form">
  		<div class="thumbnail"><img src="<c:url value="/resource/img/turtle-logo-transp-teste"/>"/> </div>
  		<form class="login-form">
    		<input name ="userLogin" type="text" placeholder="usuário"/>
    		<input name ="userPassword" type="password" placeholder="senha"/>
    		<button type="submit" name="buttonLogin">login</button>
    		<p class="message">Não Registrado? <a href="#">Cadastre-se aqui</a></p>
  		</form>
	</div>
</body>
</html>