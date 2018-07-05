
<%@page import="com.czy.factory.DAOFactory"%>
<%@page import="com.czy.dao.ShoppingCartDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String uid = String.valueOf(session.getAttribute("uid"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Pay</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>
	<div align="center">
<form id="Checkout" action="servlet/CheckOutServlet" method="post">
	<table>
	<tr>
		<td>Bank Id</td>
		<td>Firstname </td>
		<td>Lastname </td>
		<td>Expiration Date </td>
	</tr>
	<tr>
		<td><input id="id" name="id" type="text" value=""></td>
		<td><input id="firstname" name="firstname" type="text" value=""></td>
		<td><input id="lastname" name="lastname" type="text" value=""></td>
		<td><input id="expiration" name="expiration" type="text" value="0000-00-00"></td>
	</tr> 	
 	</table>
 	<input type="submit" value="Submit" class="btn-submit">
</form>
</div>
</body>
</html>