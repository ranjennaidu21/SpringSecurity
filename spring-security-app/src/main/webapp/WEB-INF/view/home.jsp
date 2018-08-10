<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
	<title>Spring Security Home Page </title>
</head>

<body>
	<h2>Spring Security App</h2>
	<hr>
	
	Welcome to the Spring Security App!

	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
</body>

</html>