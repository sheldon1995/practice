<%@page import="java.util.ArrayList"%>
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
/**
	How to clear all goods shopping cart  after the info of bank card submitted?
		1.Set a session at CheckOutServlet after the the info of bank card submitted
		2.Get the session at ShoppingCart.jsp, if it is not empty, deleteAll will be invoked. Then, set the session to null.
		3.After invoking, redirecting to ShoppingCart.jsp while it is a blank page
		4.Checking the session at the beginning of ShoppingCart.jsp, if it is not empty, do nothing.
*/
%>

<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart</title>
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
		return confirm("Are you sure that you want to pay");
	}
	function confirmDelete() {
		return confirm("Are you sure that you want to delete");
	}
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container">
		<div class="check-out">
			<h1>All orders</h1>
			<%
			String strUid = (String) session.getAttribute("uid");
			int uid = 0;
			if (strUid != null) 
			{
			uid = Integer.parseInt(strUid);
			}
			if(request.getSession().getAttribute("clearCart")!=null && !request.getSession().getAttribute("clearCart").equals("yes")){		
			%>
			<table>
				<tr>
					<th>Goods</th>
					<th>Numbers</th>
					<th>Price</th>
					<th>Final Price</th>
				</tr>
				<%			
					ShoppingCartDao dao = DAOFactory.getShoppingCartServiceInstance();
					GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
					List<ShoppingCart> cartList = dao.getAllGoods(uid);
					
					float allTotalPrice = 0;
					if (cartList != null & cartList.size() > 0) {
						ShoppingCart cart;
						Goods goods;
						//String photoPath;
						int number;
						float price;
						float totalPrice;
						int gid;
						ArrayList<String> list = new ArrayList<String>();
						for (int i = 0; i < cartList.size(); i++) {
							cart = cartList.get(i);
							goods = goodsDao.queryBymId(cart.getGid());
							number = cart.getNumber();
							price = 19;
							gid = goods.getGid();
							totalPrice = number * price ;
							allTotalPrice = allTotalPrice + totalPrice;
				%>
				<tr>
					<td class="ring-in">
					
						<div class="sed">
							<h4>
								<a href="jsp/moviesDescribed.jsp?mId=<%=goods.getmId()%>" class="at-in"> 
								<%=goods.getTitle()%>
								</a>
								<%
								list.add(goods.getmId());
								request.getSession().setAttribute("mIdList", list);
								%>
						</h4>
							<br>
							<p>
								Time of adding to cart：<%=cart.getSdate()%></p>
						</div>
						<div class="clearfix"></div></td>
					<td><%=number%></td>
					<td><%=price%>$</td>
					<td><%=totalPrice%>$</td>
					
					<td>
					quantity:&nbsp;
					<%
					if(number==1){
						
					%>
					<a href="jsp/deleteGoods.jsp?mId=<%=goods.getmId()%>&number=<%=number%>" class="item_add" style="text-decoration:none;">
					- &nbsp;
					</a>
					<%
					}
					else{%>
					<a href="jsp/addToCart.jsp?mId=<%=goods.getmId()%>&buyNumber=<%=number-1%>" class="item_add" style="text-decoration:none;">
					- &nbsp;
					</a>
					<% } %>
					
					<a href="jsp/addToCart.jsp?mId=<%=goods.getmId()%>&buyNumber=<%=number+1%>" class="item_add" style="text-decoration:none;">
					+ &nbsp;
					</a>
					<a href="jsp/deleteGoods.jsp?mId=<%=goods.getmId()%>&number=<%=number%>" onclick="return confirmDelete()">
						DELETE All<%=request.getSession().getAttribute("clearCart") %>
					</a>
					</td>
				</tr>
				<%
					}
						
					}
				
				%>
			</table>
			
			<a>Total：<%=allTotalPrice%>$
			</a> &nbsp;&nbsp;&nbsp;
			<%
				if (cartList != null & cartList.size() > 0) {
			%>
			<a href="jsp/buyGoods.jsp" class="to-buy" onclick="return confirmBuy()">
			&nbsp;&nbsp;&nbsp;Check Out&nbsp;&nbsp;&nbsp;
			</a>
			<%
				} else {
			%>
			<a class="to-buy">
			&nbsp;&nbsp;&nbsp;Check Out&nbsp;&nbsp;&nbsp;
			</a>
			<%
				}
			}
			if(request.getSession().getAttribute("clearCart")!=null && request.getSession().getAttribute("clearCart").equals("yes")){	
				ShoppingCartDao clearDao = DAOFactory.getShoppingCartServiceInstance();
				if(clearDao.clearAllGoods(uid)){
					%>
					<script type="text/javascript">
					alert("Your shopping cart has been cleared");
					</script>
					<% 
					request.getSession().setAttribute("clearCart", "notDecided");
				}
			}
			%>
		</div>
	</div>
</body>
</html>