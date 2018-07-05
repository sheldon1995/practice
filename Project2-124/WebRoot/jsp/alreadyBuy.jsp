<%@page import="com.czy.bean.AlreadyBuy"%>
<%@page import="com.czy.dao.AlreadyBuyDao"%>
<%@page import="com.czy.bean.Goods"%>
<%@page import="com.czy.dao.GoodsDao"%>
<%@page import="com.czy.service.GoodsService"%>
<%@page import="com.czy.bean.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.czy.factory.DAOFactory"%>
<%@page import="com.czy.dao.ShoppingCartDao"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>Already buy</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/another_style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/memenu.js"></script>
<script type="text/javascript" src="js/simpleCart.min.js"></script>
<script type="text/javascript">
	function confirmBuy() {
		return confirm("Are you sure to pay？");
	}
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container">
		<div class="check-out">
			<h1>History</h1>
			<table>
				<tr>
					<th>Goods</th>
					<th>Numbers</th>
					<th>Price</th>
					<th>Final price</th>
					<th>Time of adding to cart</th>
				</tr>
				<%
					String strUid = (String) session.getAttribute("uid");
					int uid = 0;
					if (strUid != null) {
						uid = Integer.parseInt(strUid);
					}
					AlreadyBuyDao dao = DAOFactory.getAlreadyBuyServiceInstance();
					List<AlreadyBuy> abList = dao.getAllBuyGoods(uid);
					if (abList != null & abList.size() > 0) {
						GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
						Goods goods;
						AlreadyBuy ab;
						String gid;
						int number;
						String buyTime;
						String photoPath;
						float price;
						float totalPrice;
						for (int i = 0; i < abList.size(); i++) {
							ab = abList.get(i);
							gid = ab.getGid();
							number = ab.getNumber();
							buyTime = ab.getBuyTime();
							goods = goodsDao.queryById(gid);
							
							price = goods.getPrice();
							totalPrice = number * price + goods.getCarriage();
				%>
				<tr>
					<td class="ring-in"><a
						href="jsp/goodsDescribed.jsp?gid=<%=goods.getGid()%>"
						class="at-in"> 
					</a>
						<div class="sed">
							<h5>
								<%=goods.getGname()%></h5>
							<br>
						</div>
						<div class="clearfix"></div></td>
					<td><%=number%></td>
					<td><%=price%>$</td>
					<%
						
					%>
					<td><%=totalPrice%>$</td>
					<td><%=buyTime%></td>
				</tr>
				<%
					}
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>