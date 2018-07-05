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
	String mId = request.getParameter("mId");
%>
<%
	GoodsDao dao = DAOFactory.getGoodsServiceInstance();
	Goods singleMovies =dao.queryBymId(mId);
	
	String titile = singleMovies.getTitle();
	String id = singleMovies.getmId();
	int year = singleMovies.getYear();
	float rating= singleMovies.getRating();
	String director = singleMovies.getDirector();
	String genres = singleMovies.getGenresname();
	String stars = singleMovies.getStarsname();
%>
<!DOCTYPE html>
<html>
<head>
<title>Movie store</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/div.css" rel="stylesheet" type="text/css"	media="all" />
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
	<div class="movieContent">
		<div class="moviecontainer">
			<div class="movieContent-top">
				
			</div>
			
			<div class="movieContent-mid">
			
			</div>
			<div class="separateLine"></div>
			<div class="movieContent-bot">
				<div class="Table">
    <div class="Title">
        <p>This is the info of a single movie</p>
    </div>
    <div class="Row">
        <div class="Cell-l">
            <p>Title</p>
        </div>
        <div class="Cell-r">
            <p><%=titile%></p>
        </div>
   
    </div>
    <div class="Row">
        <div class="Cell-l">
            <p>Year</p>
        </div>
        <div class="Cell-r">
            <p><%=year %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Director</p>
        </div>
        <div class="Cell-r">
            <p><%=director %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Movie id</p>
        </div>
        <div class="Cell-r">
            <p><%=mId%></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Stars</p>
        </div>
        <div class="Cell-r">
            <p><%=stars %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Genre</p>
        </div>
        <div class="Cell-r">
            <p><%=genres %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Rating</p>
        </div>
        <div class="Cell-r">
            <p><%=rating %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Price</p>
        </div>
        <div class="Cell-r">
            <p>19.99</p>
        </div>
    </div>
</div>

		<div class="clearfix"></div>
		<div class="separateLine"></div>		
		
			</div>
			<div class="separateLine"></div>
			<div class="movieContent-page">
			</div>
		</div>
	</div>	
</body>
</html>