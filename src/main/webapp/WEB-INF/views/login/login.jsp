<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
Login
<%-- <form action="<c:url value='/login/success.do'/>" method="POST">  --%>
<form action="<c:url value='/login'/>" method="POST"> 
	아이디 : <input type="text" id="user_id" name="username" value="admin"> 
	비밀번호 : <input type="password" id="password" name="password" value="admin">
	<input type="text" name="${_csrf.parameterName}" value="${_csrf.token}" />
 	<input type="submit" value="sigin in"/> 
 </form>
</body>
</html>