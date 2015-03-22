<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Spring 4 MVC -HelloWorld</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextResourcePath" value="${pageContext.request.contextPath}/pages/resources" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Add CSS -->
<link rel="stylesheet" href="${contextResourcePath}/css/background.css">

<!-- Add JS -->
<script src="${contextResourcePath}/js/jquery-2.1.js" type="text/javascript"></script>

</head>
<body>
	<center>
		<h1>Login Form</h1>
		<%
			String name = (String) session.getAttribute("failure");
			if (name != null) {
				out.print(name);
			}
		%>
		<div id="login_form">
			<form name="f1" method="post" action="checkLogin" id="f1">
				<table>
					<tr>
						<td class="f1_label">User Name :</td>
						<td><input type="text" name="username" value="" /></td>
					</tr>
					<tr>
						<td class="f1_label">Password :</td>
						<td><input type="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="login" value="Log In"
							style="font-size: 18px;" /></td>
						<td>
							<h2>
								<a href="${contextPath}/user/signUp">Register
									Here</a>
							</h2>
						</td>
					</tr>

				</table>
			</form>
		</div>
	</center>
</body>
</html>