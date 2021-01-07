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
<title>Virtual Cards Wallet - Topup Success</title>
</head>
<body>
	<div id="success-message">TopUp Successful</div>

	<form:form method="GET" action="../view_cards" modelAttribute="card">
		<table>
			<tr>
				<td><form:label path="cname">Card Name: </form:label></td>
				<td><form:label id="lbl-cardname" path="cname" />${card.cname }</td>
			</tr>
			<tr>
				<td><form:label path="cnumber">Card Number: </form:label></td>
				<td><form:label id="lbl-cardnumber" path="cnumber" />${card.cnumber }</td>
			</tr>
			<%-- 	<tr>
				<td><form:label path="expiryDate">Expiry on: </form:label></td>
				<td><form:label id="lbl-expiry" path="expiryDate"
						value="${card.expiryDate }" /> <fmt:formatDate
						value="${card.expiryDate}" pattern="MM/yyyy" /></td>
			</tr> --%>
			<tr>
				<td><form:label path="cardBal">Card Balance: </form:label></td>
				<td><form:label id="lbl-cardbalance" path="cardBal"
						value="${card.cardBal }" />${card.cardBal }</td>
			</tr>
			<tr>
				<td></td>
				<td><form:hidden path="user.userid" value="${uid }" /></td>
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