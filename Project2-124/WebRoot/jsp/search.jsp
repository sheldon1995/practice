<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.czy.servlet.Search"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Movies</title>
</head>
<body>
	<p>Welcome to Search!</p>
	<form method="post" action="../servlet/Search">
		<table>
			<tr>
				<td>Title</td>
				<td><input type="text" name="title" /></td>
			</tr>

			<tr>
				<td>Year</td>
				<td><input type="text" name="year" maxlength=4 /></td>
			</tr>

			<tr>
				<td>Director</td>
				<td><input type="text" name="director" /></td>
			</tr>

			<tr>
				<td>Star's Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Search"></td>
			</tr>

		</table>
	</form>
</body>
</html>