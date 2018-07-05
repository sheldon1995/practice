<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.czy.dao.GoodsDao"%>
<%@ page import="com.czy.bean.Goods"%>
<%@ page import="com.czy.factory.DAOFactory"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	String uid = String.valueOf(session.getAttribute("uid"));
%>
<!DOCTYPE html>
<html>
<head>
<title>Movie store</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/div.css" rel="stylesheet" type="text/css"	media="all" />
<link href="css/another_style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/popuo-box.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/responsiveslides.min.js"></script>
<script>
	$(function() {
		$("#slider").responsiveSlides({
			auto : true,
			speed : 50,
			namespace : "callbacks",
			pager : true,
		});
	});
	function showtime(){
		var myDate = new Date();
		document.getElementById("time").innerHTML = myDate.getHours() + " hours " + myDate.getMinutes() + " minutes "+ myDate.getSeconds() + " seconds " ;
		setTimeout("showtime()",1000);
	}
</script>
</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>
	<div class="banner">
		<div class="col-sm-3 banner-mat">
			<img class="img-responsive" src="images/1.jpg" alt="">
		</div>
		<div class="col-sm-6 matter-banner">
			<div class="slider">
				<div class="callbacks_container">
					<ul class="rslides" id="slider">
						<li><img src="images/2.jpg" alt="" ></li>
						<li><img src="images/3.jpg" alt="" ></li>
						<li><img src="images/4.jpg" alt=""></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-sm-3 banner-mat">
			<img class="img-responsive" src="images/5.jpg" alt="" >
		</div>
		<div class="clearfix"></div>
	</div>
	<!--//banner-->
	<!--content-->
	<div class="content">
		<div class="container">
			<div class="content-top">
				<%
					GoodsDao dao = DAOFactory.getGoodsServiceInstance();
					List<Goods> goodsList = dao.getAllGoods();
					if (goodsList != null && goodsList.size() > 0) {
						for (int i = 0; i < goodsList.size(); i++) {
							if (i % 4 == 0) {
				%>
				<div class="content-top1">
					<%
						}
					%>
					<div class="col-md-3 col-md2">
						<div class="col-md1 simpleCart_shelfItem">
							<h2>
								<a
									href="jsp/moviesDescribed.jsp?mId=<%=goodsList.get(i).getmId()%>"
									target="_blank"><%=goodsList.get(i).getTitle()%>
									
								</a>
							</h2>
							<h3>
							Director:<%=goodsList.get(i).getDirector()%>
							</h3>
							<div class="price">
								<h4 class="item_price">
								29.99
								</h4>
								<a
									href="jsp/addToCart.jsp?mId=<%=goodsList.get(i).getmId()%>&buyNumber=1"
									class="item_add">Add to cart
									
								</a>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<%
						if (i % 4 == 3) {
					%>
					<div class="clearfix"></div>
				</div>
				<%
					}
						}
					}
				%>
			</div>
			<div class="clearfix"></div>				
			<div  class="content-middle ">
			<a href="jsp/advancedSearch.jsp">Advanced Search</a>
			</div>
			<div  class="content-middle ">
			Guided Search  
			</div>
			<div class ="content-bottom">
				<div class= "bot-left">
				Browse Movie by Genre 
				<%
					GoodsDao genresName = DAOFactory.getGoodsServiceInstance();
					List<Goods> genresList = genresName.getAllGenres();
					int ResultNumberPerPage = 10;
					if (genresList != null && genresList.size() > 0) {
						for (int i = 0; i < genresList.size(); i++){
							
				%>
							<div class="clearfix"></div>	
					   <a href="jsp/showMoviesTitleOrGenres.jsp?title=notDecided&pgnation=10&order=5&movieGenres=<%=genresList.get(i).getGenresname()%>" target="_blank">												
						<%=genresList.get(i).getGenresname() %>
					   </a>
							
				<% 			
				
						}
					}
				%>
				</div>
				<div class= "bot-right">
				Browse Movied by Title
				<%
				for(char a='A';a<='Z';a++){
				%>
					<a href="jsp/showMoviesTitleOrGenres.jsp?title=<%=a%>&pgnation=10&order=5&movieGenres=notDecided" target="_blank">
					<% out.print(a+" ");
					%>
					</a>
					
				<% 	
				}
				%>
				<br/>
				<%
				for(int i=0;i<10;i++)  
	            {  
				%>
				<a href="jsp/showMoviesTitleOrGenres.jsp?title=<%=i%>&pgnation=10&order=5&movieGenres=notDecided" target="_blank">
					<% out.print(i+" ");   %>
	            </a> 
	               <%
	            }
				%>
			</div>
		</div>
	</div>
</div>
</body>
</html>