<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isELIgnored="false"%>

<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Cards Wallet - Create card</title>
</head>
<body>
	<table>
		<tr>
			<td><form:form name="dashboardForm" action="../dashboard"
					method="GET">
					<a href="javascript:document.dashboardForm.submit()">Dashboard</a>

				</form:form></td>
			<td><form:form name="viewcardsForm" method="GET"
					action="view_cards">
					<a href="javascript:document.viewcardsForm.submit()">View Cards</a>
				</form:form></td>
			<td><form:form name="topupcardForm" action="../topup_card"
					modelAttribute="user">
					<form:hidden path="userid" value="${user.userid }" />
					<a href="javascript:document.topupcardForm.submit()">Topup Card</a>
				</form:form></td>
			<td><a href="../login">Logout</a></td>
		</tr>
	</table>
	<h2>Create New Virtual Card</h2>

	<form:form method="POST" action="card/add" modelAttribute="card">
		<table>
			<tr>
				<td><form:label path="cname">Card Name</form:label></td>
				<td><form:input maxlength="20"
						placehoder="an eg., Online Payments" id="input-cardname"
						path="cname" /></td>
			</tr>
			<tr>
				<td><form:label path="">Select Account</form:label></td>
				<td><form:select id="from-account" path="">
						<form:option value="My Wallet" label="My Wallet" />
					</form:select></td>
				<td><form:label path="user.bal">Balance: &#x20B9;</form:label></td>
				<td><form:label id="lbl-acc-balance" path="user.bal">${bal }</form:label></td>
			</tr>
			<tr>
				<td><form:label path="cardBal">Amount</form:label></td>
				<td><form:input id="input-amount" path="cardBal" /></td>
			</tr>
			<tr>
				<td><form:hidden path="cnumber" value="${cnumber }" /></td>
				<td><form:hidden path="expiryDate" value="${expiryDate }" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:hidden path="user.userid" value="${uid}" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input id="btn-createcard" type="submit"
					value="Create a new virtual card" /></td>

			</tr>
		</table>
	</form:form>

</body>
</html>