<%@page import="com.springMVC.hello.model.User"%>
<%@page import="com.springMVC.hello.utility.JSPUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script>
var user;
$( document ).ready(function() {
	if('${user}'){
	setUserData(user);
	}
	});
	
	function setUserData(){
		$("#name").val('${user.name}');
		$("#email").val('${user.email}');
		$("#username").val('${user.username}');
		$("#password").val('${user.password}');
	}

	function sendData() {
		var user = {};
		user.name = $("#name").val();
		user.email = $("#email").val();
		user.username = $("#username").val();
		user.password = $("#password").val();

		//At Add
		var url = "add";
		if ('${user}') {
			//At Edit			
			url = "update";
			user.id='${user.id}';
		}
		
		$.ajax({
					url : url,
					data : JSON.stringify(user),
					async : false,
					type : "POST",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(result) {
						//Add
						if (!'${user}') {
						location.href = "<%=JSPUtils.getURL("/user/loginForm", request)%>";
						}
						//Edit
						else{
							location.href = "<%=JSPUtils.getURL("/user/homePage", request)%>";
						}
					},
					error : function(result) {
					},
				});
	}
</script>

<body>

	<form id='register'>
		<fieldset>
			<legend>Register</legend>
			<input type='hidden' name='submitted' id='submitted' value='1' /> <label
				for='name'>Your Full Name*: </label> <input type='text' name='name'
				id='name' maxlength="50" /> <label for='email'>Email
				Address*:</label> <input type='text' name='email' id='email' maxlength="50" />

			<label for='username'>UserName*:</label> <input type='text'
				name='username' id='username' maxlength="50" /> <label
				for='password'>Password*:</label> <input type='password'
				name='password' id='password' maxlength="50" /> <input
				type='button' name='Submit' value='Submit' onclick="sendData()" />
		</fieldset>
	</form>
</body>
</html>