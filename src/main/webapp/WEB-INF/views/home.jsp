<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<html>
<head>
	<title>Home</title>
	<meta http-equiv="Content-Type" content="text/html; charsetUTF-8">
</head>
<body>
<h1>
	Hello world!  
</h1>
하하핳하 
<P>  The time on the server is ${serverTime}.@ </P>
<form action="<c:url value='/logout'/>" method="POST"> 
${username}
<input type="text" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
 <button type="submit">LOG OUT</button> 
 </form>
</body>
</html>
