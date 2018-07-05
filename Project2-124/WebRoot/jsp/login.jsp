<%@ page language="java" pageEncoding="utf-8"%>
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
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="js/kit.js"></script>
<!--[if IE]>
		<script src="js/ieFix.js"></script>
		<![endif]-->
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-30210234-1' ]);
	_gaq.push([ '_trackPageview' ]);
	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
<script src="js/dom.js"></script>
<script src="js/form.js"></script>
<link rel="stylesheet" href="css/css.css" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/validator.css" />

<!--validator-->
<script src="js/validator.js"></script>
<script src="js/autowired.validator.js"></script>
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
<body>
	<h1>Welcome!</h1>
	<div id="regist-main">
		<form id="registForm" action="servlet/LoginServlet" method="post">
			<ol>
				<li><label for="uname">
				Email： <span
						class="kitjs-validator" for="@uname"
						rules="[{notNull:true, message:'Can not be empty'}]"></span>
				</label> <span class="field-validation-valid" data-valmsg-for="uname"
					data-valmsg-replace="true"></span> <input id="uname" name="uname"
					type="text" value=""></li>
			
				<li><label for="uname"> </label>
				<li><label for="passwd">Password： <span
						class="kitjs-validator" for="@passwd"
						rules="[{notNull:true, message:'Can not be empty'},{minLength:'2',message:'the is 2 digits'}]"></span>
				</label> <span class="field-validation-valid" data-valmsg-for="passwd"
					data-valmsg-replace="true"></span> <input id="passwd" name="passwd"
					type="password"></li>
				
				<li><label for="identity">Your Identity：</label> 
				<br />
				customer <input id="customer" value= "customers" name="identity" type="radio" style="width: 10px;height: 10px" checked >
				administrator<input id="administrator" value= "employees" name="identity" type="radio" style="width: 10px;height: 10px">		
				</li>
			
					<li>
					<%//<div class="g-recaptcha" data-sitekey="6Ld24FgUAAAAAOYaVZLJHKEHVyCcc1pjIieRG0Un"></div> %>
					</li>
			</ol>
			<div class="registError"></div>
			
			<input type="submit" value="login" class="btn-submit">
		</form>
	</div>
</body>
</html>