<jsp:useBean id="beanCurso" class="beans.BeanCurso"
	type="beans.BeanCurso" scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

	<div class="login-page">
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				<input type="text" id="login" name="login" placeholder="usu�rio">
				<input type="password" id="senha" name="senha" placeholder="senha"> 
				<button type="submit" value="logar">Entrar no Sistema</button>
			</form>
		</div>
	</div>

</body>
</html>