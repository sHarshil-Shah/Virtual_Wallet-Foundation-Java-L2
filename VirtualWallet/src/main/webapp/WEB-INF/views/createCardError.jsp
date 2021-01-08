<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Cards Wallet - Create Card Failure</title>
</head>
<body>
	<table>
		<tr>
			<td><form:form name="dashboardForm" action="../dashboard">
					<a href="javascript:document.dashboardForm.submit()">Dashboard</a>

				</form:form></td>
			<td><form:form name="viewcardsForm" method="GET"
					action="../view_cards">
					<a href="javascript:document.viewcardsForm.submit()">View Cards</a>
				</form:form></td>
			<td><form:form name="topupcardForm" action="../topup_card"
					method="GET">

					<a href="javascript:document.topupcardForm.submit()">Topup Card</a>
				</form:form></td>
			<td><a href="../login">Logout</a></td>
		</tr>
	</table>

	<div id="cardFailureText">${msg }</div>
	<br />
	<form:form method="GET" action="../view_cards" modelAttribute="card">

		<button type="submit" id="btn-viewcard" type="submit">View
			Cards</button>
	</form:form>
</body>
</html>