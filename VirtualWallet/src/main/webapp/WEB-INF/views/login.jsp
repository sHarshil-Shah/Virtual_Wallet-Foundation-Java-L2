<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page isELIgnored="false"%>

<%@ page session="true"%>
<html>
<head>
<title>Virtual Cards Wallet - Login</title>
<script type="text/javascript"
	src="<c:url value="/resources/myjquery.js"/>"></script>

</head>
<body>
	<h2>Login</h2>

	<form:form method="GET" action="ensureUser" modelAttribute="user">
		<table>
			<tr>
				<td><form:label path="uName">User Name</form:label></td>
				<td><form:input maxlength="30" id="username" path="uName" /></td>
			</tr>
			<tr>
				<td><form:label path="pass">Password</form:label></td>
				<td><form:input maxlength="30" type="password" id="password"
						path="pass" /></td>
			</tr>
			<tr>
				<td colspan="2"><button id="login" type="submit">Login</button></td>
			</tr>
		</table>
	</form:form>

	<br />
	<h2 id="error-message" style="color: red;">${status }</h2>

</body>
</html>