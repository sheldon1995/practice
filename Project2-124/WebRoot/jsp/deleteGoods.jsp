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
	String gid = request.getParameter("mId");
	int number = Integer.parseInt(request.getParameter("number"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Delete</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<%
		ShoppingCartDao scDao = DAOFactory.getShoppingCartServiceInstance();
		if (scDao.deleteGoods(Integer.parseInt(uid), gid, number)) {
			%>
			<script type="text/javascript" language="javascript">
			alert("Delete Success!");
			window.document.location.href="/Project2-124/jsp/shoppingCart.jsp";
			</script>
			<% 
		} else {
	%>
	Failed,retry
	<%
		}
	%>
</body>
</html>
