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
	<!--content-->
	
	<div class="movieContent">
		<div class="moviecontainer">
			<div class="movieContent-top">
				
			</div>
			
			<div class="movieContent-mid">
				
			</div>
			<div class="separateLine"></div>
			<div class="movieContent-bot">
			
				<%
					GoodsDao getMovies = DAOFactory.getGoodsServiceInstance();
					List<Goods> movieList = (List<Goods>) request.getSession().getAttribute("searchResult");
					if (movieList != null && movieList.size() > 0) {
						for (int i = 1; i < movieList.size(); i++){
							
				%>
				<div class="Table">
				
    <div class="Title">
        <p>This is advanced search</p>
    </div>
    <div class="Row">
        <div class="Cell-l">
            <p>Title</p>
        </div>
        <div class="Cell-r">
            <p><a href="jsp/moviesDescribed.jsp?mId=<%=movieList.get(i).getmId() %>" target="_blank"><%=movieList.get(i).getTitle() %></a></p>
        </div>
   
    </div>
    <div class="Row">
        <div class="Cell-l">
            <p>Year</p>
        </div>
        <div class="Cell-r">
            <p><%=movieList.get(i).getYear() %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Director</p>
        </div>
        <div class="Cell-r">
            <p><%=movieList.get(i).getDirector() %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Movie id</p>
        </div>
        <div class="Cell-r">
            <p><%=movieList.get(i).getmId() %></p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Stars</p>
        </div>
        <div class="Cell-r">
            <p>
            <% for(int m=0;m<movieList.get(i).getStarsnumber();m++){
                %>
                 <a href="jsp/starsDescribed.jsp?&starsId=<%=movieList.get(i).getStId().get(m)%>" target="_blank">
                 <%=movieList.get(i).getStars().get(m)%> 
                 </a>
                <% 
                if(m%4==0){
                	%>
                	<br><%
                }
                }
           	%> 
            </p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Genre</p>
        </div>
        <div class="Cell-r">
            <p>
            
              	<% for(int m=0;m<movieList.get(i).getGenresnumber();m++){
                %>
                  <a href="jsp/showMoviesTitleOrGenres.jsp?title=notDecided&pgnation=10&order=5&movieGenres=<%=movieList.get(i).getGenres().get(m)%>" target="_blank">
                 <%=movieList.get(i).getGenres().get(m)%> 
                 </a>
                <% 
                }
           	%>      
            </p>
        </div>
    </div>
      <div class="Row">
        <div class="Cell-l">
            <p>Rating</p>
        </div>
        <div class="Cell-r">
            <p><%=movieList.get(i).getRating() %></p>
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
      <div class="Row">
        <div class="Cell-l">
            <p></p>
        </div>
        <div class="Cell-r">
           <p><a href="jsp/addToCart.jsp?mId=<%=movieList.get(i).getmId()%>&buyNumber=1">
         Add to cart(Click)
                </a>
             </p>
        </div>
    </div>
</div>
		<div class="clearfix"></div>
		<div class="separateLine"></div>
			<% 							
						}
					}
					else{
						out.print("No result");
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