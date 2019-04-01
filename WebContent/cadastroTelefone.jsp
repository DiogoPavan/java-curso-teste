<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

</head>
<body>
	<a href="cadastroUsuario.jsp">Voltar</a>
	<center>
		<h1>Cadastro Telefones</h1>
	</center>

	<center>
		<h3 style="color: red;">${msg}</h3>
	</center>

	<center>
		<form action="salvarTelefone" method="post" id="form"
			onsubmit="return validarCampos()">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<td>Usuário:</td>
							<td><input type="text" id="id" readonly="readonly" name="id"
								value="${usuarioEscolhido.idBeanCurso}" class="field-long"></td>
							<td><input type="text" id="nome" readonly="readonly"
								name="nome" value="${usuarioEscolhido.nome}" class="field-long"></td>

						</tr>
						<tr>
							<td>Número:</td>
							<td><input type="text" id="numero" name="numero"
								value="${telefone.numero}" class="field-long"></td>
							<td>
								<select id="tipo" name="tipo" class="field-long">
									<option value="CASA">Casa</option>
									<option value="TRABALHO">Trabalho</option>
									<option value="CELULAR">Celular</option>
								</select>
							</td>

						</tr>

						<tr>
							<td></td>
							<td><input type="submit" value="Salvar"></td>
						</tr>
					</table>
				</li>
			</ul>
		</form>
	</center>
	<div class="container">
		<table class="responsive-table">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Número</th>
					<th scope="col">Tipo</th>
					<th scope="col">Deletar</th>
				</tr>
			</thead>
			<c:forEach items="${telefones}" var="telefone">
				<tr>
					<td><c:out value="${telefone.idTelefone}"></c:out></td>
					<td><c:out value="${telefone.numero}"></c:out></td>
					<td><c:out value="${telefone.tipo}"></c:out></td>

					<td><a
						href="salvarTelefone?acao=delete&idTelefone=${telefone.idTelefone}">
							<img src="resources/img/excluir.png" width="20px" height="20px"
							title="Excluir" />
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			var isValid = true;
			var action = document.getElementById('form').action;

			if (!action.endsWith('salvarUsuario?acao=reset')) {
				if (document.getElementById("login").value == '') {
					alert('Informe o login');
					isValid = false;
				}
			}

			return isValid;
		}
	</script>
</body>
</html>