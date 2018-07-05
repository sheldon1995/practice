<%@page import="com.czy.dao.EmployeeDao"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.czy.dao.EmployeeDao"%>
<%@ page import="com.czy.bean.Employee"%>
<%@ page import="com.czy.factory.DAOFactory"%>
<%@ page import="java.util.List"%>
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
<title>Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src='https://www.google.com/recaptcha/api.js'></script>
<link href="css/div.css" rel="stylesheet" type="text/css"	media="all" />
<script src="js/kit.js"></script>
<!--[if IE]>
		<script src="js/ieFix.js"></script>
		<![endif]-->
<script src="js/dom.js"></script>
<script src="js/form.js"></script>

<!--validator-->
<script src="js/validator.js"></script>
<script src="js/autowired.validator.js"></script>
<%  
        HttpSession sess = request.getSession();  
        String message = (String)sess.getAttribute("message");  
    	String comparision ="You successfully add a new star!!!";
    	if(message!=""){  
        %>  
  	         <script type="text/javascript">  
                    alert("<%=message %>");  
             </script> 
        <%  
    }
  
    else{  
        %>  
             <script type="text/javascript">  
                    alert("<%="Welcome TA!" %>");  
             </script> 
        <%  
        sess.setAttribute("message", "");  
    }  
 %>


<style>
table td {
	font-size: 19px;
}

label {
	cursor: pointer;
	margin-right: 1em;
}
</style>
</head>
<center>
<body>
	<h1>Welcome! TA</h1>
	<p>Add a new star! Name is required, bithday is optional</p>
	<div >
		<form id="registForm" action="servlet/DashboardProcess" method="post">
			<ol>
				<li><label for="starname">
				Name： <span class="kitjs-validator" for="@uname" rules="[{notNull:true, message:'Can not be empty'}]"></span>
				</label> <span class="field-validation-valid" data-valmsg-for="uname"
					data-valmsg-replace="true"></span> <input id="starname" name="starname"
					type="text" value=""></li>
		
				<li><label for="passwd">BirthYear： 
				</label> <span class="field-validation-valid" data-valmsg-for="passwd"
					data-valmsg-replace="true"></span> <input id="birthYear" name="birthYear"
					type="text" value=0000></li>
			</ol>
			
			<input type="submit" value="Submit" class="btn-submit">
		</form>
	</div>
<table border="1">
      <tr>
          <td>Table Name</td>
           <td>Field Name</td>
           <td>Type</td>
       
       </tr>
        	<%
					EmployeeDao getMetaData = DAOFactory.getMetaServiceInstance();
					List<Employee> metaDataList = getMetaData.getMetaServiceInstance();
					if (metaDataList != null && metaDataList.size() > 0) {
						for (int i = 0; i < metaDataList.size(); i++){
							
				%>
          <tr>
        
              <td><%=metaDataList.get(i).getTablename() %></td>
               <td><%=metaDataList.get(i).getCalumname() %></td>
               <td><%=metaDataList.get(i).getType() %></td>
           </tr>
     		<% 							
						}
					}
				%>
		
		
   </table>

</body>
</center>
</html>