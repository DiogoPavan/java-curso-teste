<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <jsp:useBean id="beanCurso" class="beans.BeanCurso" type="beans.BeanCurso" scope="page"></jsp:useBean>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP</title>
</head>
<body>
<jsp:setProperty property="*" name="beanCurso"/>
	<h1> SECONDA PAGE </h1>
	
	<!-- <jsp:getProperty property="nome" name="beanCurso"/>
	<br/>
	<jsp:getProperty property="sexo" name="beanCurso"/> -->
	
	Nome: ${param.nome}
	<br/>
	Sexo: ${param.sexo}
	<br/>
	${sessionScope.teste}
</body>
</html>