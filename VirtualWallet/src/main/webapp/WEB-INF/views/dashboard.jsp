<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isELIgnored="false"%>

<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Cards Wallet - Home</title>
</head>
<body>
	<table>
		<tr>
			<td><form:form name="viewcardsForm" method="GET"
					action="view_cards" modelAttribute="user">
					<a href="javascript:document.viewcardsForm.submit()">View Cards</a>
				</form:form></td>
			<td><form:form name="topupcardForm" action="topup_card"
					modelAttribute="user">
					<a href="javascript:document.topupcardForm.submit()">Topup Card</a>

				</form:form></td>
			<td><a href="login">Logout</a></td>
		</tr>
	</table>
	<form:form modelAttribute="user">
		<ul>
			<li>My wallet <br /> Available Balance: <form:label
					id="wallet-balance" path="bal">${user.bal }</form:label></li>
			<li>Cards <br /> No. of cards remaining: <form:label
					id="cards-remaining" path="noCards">${3 - user.noCards }</form:label></li>
		</ul>
	</form:form>
</body>
</html>