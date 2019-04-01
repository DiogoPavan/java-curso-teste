<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessoMenu.jsp">Início</a>
	<center>
		<h1>Cadastro Produto</h1>
	</center>

	<center>
		<h3 style="color: red;">${msg}</h3>
	</center>

	<form action="salvarProduto" method="post" id="form"
		onsubmit="return validarCampos()">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" id="id" readonly="readonly" name="id"
							value="${produto.idProduto}" class="field-long"></td>
					</tr>
					<tr>
						<td>Descrição:</td>
						<td><input type="text" id="descricao" name="descricao"
							value="${produto.descricao}" class="field-long"></td>
					</tr>
					<tr>
						<td>Quantidade:</td>
						<td><input type="number" id="quantidade" name="quantidade"
							value="${produto.quantidade}" class="field-long"></td>
					</tr>
					<tr>
						<td>Valor:</td>
						<td><input type="text" id="valor" name="valor"
							value="${produto.valorEmTexto}" class="field-long"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('form').action='salvarProduto?acao=reset'">
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<div class="container">
		<table class="responsive-table">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Descrição</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Valor</th>
					<th scope="col" colspan="2">Ações</th>
				</tr>
			</thead>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><c:out value="${produto.idProduto}"></c:out></td>
					<td><c:out value="${produto.descricao}"></c:out></td>
					<td><c:out value="${produto.quantidade}"></c:out></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${produto.valor}" /> </td>

					<!-- ver para usar o doDelete -->
					<td><a
						href="salvarProduto?acao=delete&id=${produto.idProduto}"> <img
							src="resources/img/excluir.png" width="20px" height="20px"
							title="Excluir" />
					</a></td>
					<td><a
						href="salvarProduto?acao=editar&id=${produto.idProduto}"> <img
							src="resources/img/editar.png" width="20px" height="20px"
							title="Editar" />
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			var isValid = true;
			var action = document.getElementById('form').action;

			if (!action.endsWith('salvarProduto?acao=reset')) {
				if (document.getElementById("descricao").value == '') {
					alert('Informe o descricao');
					isValid = false;
				} else if (document.getElementById("quantidade").value == '') {
					alert('Informe o quantidade');
					isValid = false;
				} else if (document.getElementById("valor").value == '') {
					alert('Informe o valor');
					isValid = false;
				}
			}

			return isValid;

		}
	</script>
</body>

<script type="text/javascript">
  $(function() {
    $('#valor').maskMoney({thousands:'.', decimal:','});
  })
</script>
  
</html>