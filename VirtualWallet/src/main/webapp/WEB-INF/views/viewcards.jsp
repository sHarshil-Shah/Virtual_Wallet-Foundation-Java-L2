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
<title>Virtual Cards Wallet - View Cards</title>
</head>
<body>
	<table>
		<tr>
			<td><form:form name="dashboardForm" method="GET"
					action="dashboard" modelAttribute="user">
					<form:hidden path="userid" value="${user.userid }" />
					<form:hidden path="bal" value="${user.bal }" />
					<a href="javascript:document.dashboardForm.submit()">Dashboard</a>
					<!-- <button type="submit">View Cards</button> -->
				</form:form></td>
			<td><form:form name="topupcardForm" action="topup_card"
					modelAttribute="user">
					<form:hidden path="userid" value="${user.userid }" />
					<a href="javascript:document.topupcardForm.submit()">Topup Card</a>
					<!-- <button type="submit">Topup Card</button> -->
				</form:form></td>
			<td><a href="login">Logout</a></td>
		</tr>
	</table>
	<form:form method="GET" action="create_card" modelAttribute="user">

		<table>
			<c:forEach items="${cards}" var="card">
				<tr>
					<td><form:label path="" id="lbl-cardname">${card.cname}</form:label></td>
					<td><form:label path="" id="lbl-cardnumber">${card.cnumber}</form:label></td>
					<td><form:label path="" id="lbl-expiry">
							<fmt:formatDate value="${card.expiryDate}" pattern="MM/yyyy" />
						</form:label></td>
					<td><form:label path="" id="lbl-cardbalance">
							<b>&#x20B9; ${card.cardBal}</b>
						</form:label></td>
				</tr>
			</c:forEach>
		</table>
		<%-- <form:hidden path="userid"
			value="${user.userid==0?uid:user.userid }" />
		<form:hidden path="bal" value="${user.bal == 0?bal:user.bal }" /> --%>
		<button type="submit" id="btn-createcard"<c:if test="${info != null}"><c:out value="disabled='disabled'"/></c:if>">Create
			a new virtual card</button>
	</form:form>
	<div id="information">${info }</div>
</body>
</html>