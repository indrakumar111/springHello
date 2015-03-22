<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Set Static Path For Css -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextResourcePath"
	value="${pageContext.request.contextPath}/pages/resources" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Add CSS -->
<link rel="stylesheet" href="${contextResourcePath}/css/background.css">
<link rel="stylesheet" href="${contextResourcePath}/css/table.css">

<!-- Add JS -->
<script src="${contextResourcePath}/js/jquery-2.1.js"
	type="text/javascript"></script>

</head>
<body>
	<center>
		<div>
			<h3>Welcome MVC ${name}</h3>
			<h6>
				<a href="${contextPath}/user/logout">Logout Here</a>
			</h6>
		
		<div>
<!-- 			Add Images From Resources -->
			<img src="${contextResourcePath}/images/default.jpeg" alt="name"
				style="width: 100px; height: 100px">
		</div>
		</div>
		
		<table style="width: 100%">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>UserName</th>
					<th>Password</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach items="${allUser}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>${user.username}</td>
						<td>${user.password}</td>
						<td><a href="${contextPath}/user/edit?userId=${user.id}">Edit</a>
							&nbsp;&nbsp; <a
							href="${contextPath}/user/delete?userId=${user.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</center>


</body>
</html>