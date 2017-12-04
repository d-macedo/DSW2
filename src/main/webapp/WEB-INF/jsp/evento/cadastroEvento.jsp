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
						<li><a href="<c:url value="/cadastrar/usuario" />">Cadastrar Usuário</a></li> 
						<li><a href="<c:url value="/evento/pendentes" />">Aguardando Aprovação (${numeroPendentes})</a></li> 
						<li><a href="">Balanço Geral</a></li>
			    			<li><a href="<c:url value="/logout"/>">Logout</a></li>
					</c:if>
					
					<!-- EXECUTOR -->
					<c:if test="${sessao.tipoUsuario == 'E'}">
						<li><a href="<c:url value="#" />">Cadastrar Evento</a></li> 
						<li><a href="<c:url value="/evento/meuseventos" />">Meus Eventos</a></li> 
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

			<c:if test="${empty sessao or not sessao.logado}">
			<div class="row eventoIndex">
				<div class="col-md-12 titulo">
					<p>Você não tem permissão para acessar esta página.</p>
				</div>
			</div>
		</c:if>
		
		<c:if test="${sessao.logado}">
			<c:if test="${sessao.tipoUsuario != 'E'}">
				<div class="row eventoIndex">
					<div class="col-md-12 titulo">
						<p>Você não tem permissão para acessar esta página.</p>
					</div>
				</div>
			</c:if>
		</c:if>
		
		<c:if test="${sessao.logado}">
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
								id="maxIntEvento" name="evento.eve_max_inteira" value="0">
						</div>
						<div class="form-group col-md-6">
							<label for="maxMeiaEvento">Máximo de Ingressos do tipo
								Meia do Evento</label> <input type="number" class="form-control"
								id="maxMeiaEvento" name="evento.eve_max_meia" value="0">
						</div>
						<div class="form-group col-md-6">
							<label for="valorIntEvento">Valor de Ingressos do tipo
								Inteiro do Evento</label> <input type="number" class="form-control"
								id="valorIntEvento" name="evento.eve_valor_inteira" value="0">
						</div>
						<div class="form-group col-md-6">
							<label for="valorMeiaEvento">Valor de Ingressos do tipo
								Meia do Evento</label> <input type="number" class="form-control"
								id="valorMeiaEvento" name="evento.eve_valor_meia" value="0">
						</div>
						<div class="form-group col-md-12">
							<label for="localEvento">Local do Evento</label>
							<select
								class="form-control" id="localEvento" name="local_id">
								<option value="0"> - Selecione - </option>
								<c:forEach items="${locais}" var="loc">
									<option
									  value="${loc.loc_cod}">${loc.loc_nome} (${loc.loc_endereco} - ${loc.loc_cidade.cid_nome},${loc.loc_cidade.cid_estado.est_sigla})
									</option>
								</c:forEach>
							</select>
							
							<a href="" id="linkLocal" data-toggle="modal" data-target="#myModal">Cadastrar Novo Local</a>
							
							<!-- Modal -->
							  <div class="modal fade" id="myModal" role="dialog">
							    <div class="modal-dialog">
							    
							      <!-- Modal content-->
							      <div class="modal-content">
							        <div class="modal-header">
							          <button type="button" class="close" data-dismiss="modal">&times;</button>
							          <h4 class="modal-title">Novo Local</h4>
							        </div>
							        <div>
							          <div class="form-group col-md-12">
										 <label for="nomeLocal">Nome</label>
										 <input
											type="text" class="form-control" id="nomeLocal"
											name="local.loc_nome" placeholder="Digite o nome do Local">
									  </div>
									  <div class="form-group col-md-12">
										 <label for="enderecoLocal">Endereço</label>
										 <input
											type="text" class="form-control" id="enderecoLocal"
											name="local.loc_endereco" placeholder="Digite o endereço do Local">
									  </div>
									  <div class="form-group col-md-12">
										 <label for="cidadeLocal">Cidade</label>
										 <select
											class="form-control" id="cidadeLocal" name="cidade_id">
											<option value="0"> - Selecione - </option>
											<c:forEach items="${cidades}" var="cid">
												<option value="${cid.cid_cod}">${cid.cid_nome} - ${cid.cid_estado.est_sigla}</option>
											</c:forEach>
										 </select>
									  </div>
							        </div>
							        <div class="modal-footer">
							          <button id="endNovoLocal" type="button" class="btn btn-default" data-dismiss="modal">Cadastrar</button>
							        </div>
							      </div>
							      
							   </div>
							 </div>
							<!-- Fim Modal -->
							
						</div>	
						<div class="form-group col-md-12">
							<label for="categoriaEvento">Categoria do Evento</label>
							<select
								class="form-control" id="categoriaEvento"
								name="categoria_id">
								<option value="0"> - Selecione - </option>
								<c:forEach items="${categorias}" var="cat">
									<option value="${cat.cat_cod}">${cat.cat_descricao}</option>
								</c:forEach>
							</select>
						</div>
						<button type="submit" class="btn btn-primary">Enviar</button>
					</form>
				</div>
	</div>
	</div>
	</c:if>
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
	
	<script>
		$("#endNovoLocal").click(function(){
			$('#localEvento').append($('<option>', {
			    value: 0,
			    text: $("#nomeLocal").val(),
			    selected: true
			}));
		});
	</script>
	
</body>
</html>