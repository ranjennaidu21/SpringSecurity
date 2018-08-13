<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Spring Security Home Page </title>
</head>

<body>
	<h2>Spring Security App</h2>
	<hr>
	
	Welcome to the Spring Security App!

	<hr>
	
	<!-- display user name and role -->
	
	<p>
		<!-- give user id  -->
		User: <security:authentication property="principal.username" />
		<br><br>
		<!-- //give user roles-->
		Role(s): <security:authentication property="principal.authorities" />
		
		<!-- by default Spring Security uses(ROLE_*) prefixes , this is configurable -->
	</p>
	
	<hr>
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
</body>

</html>