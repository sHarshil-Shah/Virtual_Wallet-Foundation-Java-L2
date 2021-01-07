<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page isELIgnored="false"%>

<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Cards Wallet - Topup</title>

</head>
<body>
	<table>
		<tr>
			<td><form:form name="dashboardForm" action="dashboard"
					method="GET">
					<a href="javascript:document.dashboardForm.submit()">Dashboard</a>

				</form:form></td>
			<td><form:form name="viewcardsForm" method="GET"
					action="view_cards">
					<a href="javascript:document.viewcardsForm.submit()">View Cards</a>
				</form:form></td>

			<td><a href="login">Logout</a></td>
		</tr>
	</table>
	<form:form method="POST" action="editCard" modelAttribute="card">
		<table>
			<tr>
				<td><form:label path="cardid">Card Name: </form:label></td>

				<td><form:select path="cardid" id="select-card">
						<c:forEach items="${cardList}" var="c" varStatus="loop">
							<option value="${c.cardid}">${c.cname}&nbsp;&nbsp;(
								&#x20B9;
								<form:label id="lbl-card-balance" path=""> ${c.cardBal }</form:label>)
							</option>

						</c:forEach>
					</form:select></td>

			</tr>
			<tr>
				<td><form:label path="">Select Account: </form:label></td>
				<td><select id="select-account">
						<option value="My Wallet" selected>My Wallet</option>
				</select></td>
				<td><form:label id="lbl-wallet-balance" path=""> Balance: &#x20B9; ${bal }</form:label></td>
			</tr>
			<tr>
				<td><form:label path="">Amount: </form:label></td>
				<td><form:input id="topup-val" path="cardBal" /></td>
			</tr>

			<tr>
				<td colspan=2>
					<button type="submit" id="btn-topup" type="submit">TopUp
						Card</button>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>