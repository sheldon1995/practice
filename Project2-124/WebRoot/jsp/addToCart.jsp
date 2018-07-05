<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.czy.factory.DAOFactory"%>
<%@page import="com.czy.dao.ShoppingCartDao"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Adding to cart</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<%	
	String strUid = (String) session.getAttribute("uid");

	int uid = 0;

	if (strUid != null) {

		uid = Integer.parseInt(strUid);

	}
		String gid = request.getParameter("mId");
		int number = Integer.valueOf(request.getParameter("buyNumber"));
		ShoppingCartDao dao = DAOFactory.getShoppingCartServiceInstance();
		boolean flag = dao.addGoods(uid, gid, number);
		if (flag) {
			%>
			<script type="text/javascript" language="javascript">
			alert("Add Success!");
			window.document.location.href="/Project2-124/jsp/shoppingCart.jsp";
			</script>
			<%
			
			//response.sendRedirect("shoppingCart.jsp");
		} else {
	%>
			Failed, retry
	<%
		}
	%>
</body>
</html>
