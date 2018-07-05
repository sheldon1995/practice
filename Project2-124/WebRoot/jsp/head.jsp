<%@page import="com.czy.util.OnlineCounter"%>
<%@page import="com.czy.bean.Goods"%>
<%@page import="java.util.List"%>
<%@page import="com.czy.factory.DAOFactory"%>
<%@page import="com.czy.dao.GoodsDao"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%
	String uname = (String) session.getAttribute("uname");
	String uid = String.valueOf(session.getAttribute("uid"));
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/another_style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/responsiveslides.min.js"></script>
<script type="text/javascript" src="js/memenu.js"></script>
<script>
	$(document).ready(function() {
		$(".memenu").memenu();
	});
</script>
<script type="text/javascript">
	function key() {
		return confirm("Are you sure to quit?");
	}
</script>
<script>
	function showtime() {
		var myDate = new Date();
		document.getElementById("time").innerHTML = myDate.getHours() + " hours "
				+ myDate.getMinutes() + " minutes " + myDate.getSeconds() + " seconds ";
		setTimeout("showtime()", 1000);
	}
</script>
</head>
<body>
	<!--header-->
	<div class="header">
		<div class="header-top">
			<div class="container">
				<div class="col-sm-4 world">
					<ul>
						<li></li>
					</ul>
				</div>
				<div class="col-sm-4 logo">
					<a href="index.jsp"><img src="images/logo.png" alt=""></a>
				</div>
				<div class="col-sm-4 header-left">
					<p class="log">
						<%
							if (uname != null) {
												out.print("<a>" + uname + ",Welcome" + "</a>");
												out.print("<a>" + uid + ",Welcome" + "</a>");
												out.print("<a href=\"jsp/showMessage.jsp\" target=\"_blank\">"
														+ "User information" + "</a>");
												out.print("<a href=\"servlet/LogoutServlet\" onClick=\"return key()\">"
														+ "Login out" + "</a>");
											} else {
												out.print("<a href=\"jsp/login.jsp\">Please login</a>");
												out.print("<a>or</a>");
												out.print("<a href=\"jsp/register.jsp\">Register</a>");
											}
						%>
						<a id="time"><script type="text/javascript">
							showtime();
						</script> </a> <a>Online customer:<%=OnlineCounter.getOnline()%></a>
					</p>
					<div class="cart box_1">
						<a href="jsp/shoppingCart.jsp"> <img src="images/cart.png"
							alt="" />
							Shopping cart
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="head-top">
				<div class="col-sm-8 h_menu4">
					<ul class="memenu skyblue">
						<li class=" grid"><a href="jsp/index.jsp">Main page</a></li>
						
						<li><a href="jsp/shoppingCart.jsp">Shopping Cart</a></li>
						<li><a href="jsp/alreadyBuy.jsp">Shopping history</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>