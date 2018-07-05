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
	String title =String.valueOf(request.getParameter("title")); 
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
<style type="text/css">
.tg  {
	border-collapse:collapse;
	border-spacing:0;
	border-color:#aabcfe;
	align:center;}
.tg td{font-family:Arial, sans-serif;font-size:18px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;}
.tg th{font-family:Arial, sans-serif;font-size:18px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;}
.tg .tg-1bmp{color:black;border-color:#ffffff;vertical-align:top}
.tg .tg-k8y1{font-size:18px;color:black;border-color:#ffffff;vertical-align:top}
.tg .tg-534c{color:black;border-color:#ffffff;vertical-align:top}
</style>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
	<!--content-->
	
	<div class="movieContent">
		<div class="moviecontainer">
			<div class="table"  >
			<form action="./servlet/AdvancedServlet">
			<table class="tg" >
  <tr>
    <th class="tg-k8y1">Title</th>
    <th class="tg-1bmp"><input type="text" name="title" value=""></th>
  </tr>
  <tr>
    <td class="tg-534c">Year</td>
    <td class="tg-534c"><input type="text" name="year" value=""></td>
  </tr>
  <tr>
    <td class="tg-1bmp">Director</td>
    <td class="tg-1bmp"><input type="text" name="director" value=""></td>
  </tr>
  <tr>
    <td class="tg-534c">Star's Name</td>
    <td class="tg-534c"><input type="text" name="name" value=""></td>
  </tr>
  <tr>
    <td class="tg-534c"><input type="submit" value="Search" name="submit"></td>
    <td class="tg-534c"><input type="reset" value="Reset" name="reset"></td>
  </tr>
		</table>
	</form>
</div>
		</div>
	</div>
</body>
</html>