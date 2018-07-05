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
	String starsId = request.getParameter("starsId");
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
				Search Result
			</div>
			
			<div class="movieContent-mid">
				Sort by Title Title Year Year
			</div>
			<div class="separateLine"></div>
			<div class="movieContent-bot">
	<%
	GoodsDao dao = DAOFactory.getGoodsServiceInstance();
	List<Goods> singleStars =dao.queryBystarsId(starsId);
	if(singleStars!=null && singleStars.size()>0){
		for(int i=0;i<singleStars.size();i++){
		%>
				<div class="Table">
    <div class="Title">
        <p>This is the info of a single star</p>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>id</p>
        </div>
        <div class="Cell-r">
            <p><%=singleStars.get(i).getStarsId()%></p>
        </div>
    </div>
    <div class="Row">
        <div class="Cell-l">
            <p>Name</p>
        </div>
        <div class="Cell-r">
            <p><%=singleStars.get(i).getStarsname()%></p>
        </div>
    </div>
    <div class="Row">
        <div class="Cell-l">
            <p>Birth Year</p>
        </div>
        <div class="Cell-r">
            <p><%=singleStars.get(i).getYear() %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Acted Movies</p>
        </div>
        <div class="Cell-r">
            <p>
            	<% for(int m=0;m<singleStars.get(i).getAcLength();m++){
                %>
                  <a href="jsp/moviesDescribed.jsp?mId=<%=singleStars.get(i).getMovieId().get(m)%>" target="_blank">
                 <%=singleStars.get(i).getActedMovies().get(m)%> 
                 </a>
                <% 
                }
           	%>
            
            </p>
        </div>
    </div>
</div>
		<div class="clearfix"></div>
		<div class="separateLine"></div>		
		<% 	
		}
	}
    %>
			</div>
			<div class="separateLine"></div>
			<div class="movieContent-page">
			</div>
		</div>
	</div>	
</body>
</html>