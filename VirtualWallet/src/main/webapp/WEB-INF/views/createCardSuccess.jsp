<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Cards Wallet - Create Card Success</title>
</head>
<body>
	<table>
		<tr>
			<td><form:form name="dashboardForm" action="../dashboard"
					method="GET">
					<a href="javascript:document.dashboardForm.submit()">Dashboard</a>

				</form:form></td>
			<td><form:form name="viewcardsForm" method="GET"
					action="../view_cards">
					<a href="javascript:document.viewcardsForm.submit()">View Cards</a>
				</form:form></td>
			<td><form:form name="topupcardForm" action="../topup_card">
					<a href="javascript:document.topupcardForm.submit()">Topup Card</a>
				</form:form></td>
			<td><a href="../login">Logout</a></td>
		</tr>
	</table>

	<div id="success-message">Virtual card Created</div>
	<form:form method="GET" action="../view_cards" modelAttribute="card">
		<table>
			<tr>
				<td><form:label path="cname">Card Name: </form:label></td>
				<td><form:label id="lbl-cardname" path="cname">${card.cname }</form:label></td>
			</tr>
			<tr>
				<td><form:label path="cnumber">Card Number: </form:label></td>
				<td><form:label id="lbl-cardnumber" path="cnumber">${card.cnumber }</form:label></td>
			</tr>
			<%-- <tr>
				<td><form:label path="expiryDate">Expiry on: </form:label></td>
				<td><form:label id="lbl-expiry" path="expiryDate"
						value="${card.expiryDate }" /> <fmt:formatDate
						value="${card.expiryDate}" pattern="MM/yyyy" /></td>
			</tr> --%>
			<tr>
				<td><form:label path="cardBal">Card Balance: </form:label></td>
				<td><form:label id="lbl-cardbalance" path="cardBal"
						value="${card.cardBal }">${card.cardBal }</form:label></td>
			</tr>
			<tr>
				<td colspan=2>
					<button type="submit" id="btn-viewcard" type="submit">View
						Cards</button>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>