<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="domain" value="${pageContext.request.contextPath}"/>
<div>
	<form:form method="POST" action="/insert-currency-from-csv" modelAttribute="insertCurrencyFromCsv">
		<table>
			<tr>
				<td><form:label path="fileName">File Name</form:label></td>
				<td><form:input path="fileName"/></td>
			</tr>
<%--			<tr>
				<td><form:label path="start">Start</form:label></td>
				<td><form:input path="start" cssClass="datepicker"/></td>
			</tr>
			<tr>
				<td><form:label path="finish">Finish</form:label></td>
				<td><form:input path="finish" cssClass="datepicker"/></td>
			</tr>--%>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"/></td>
			</tr>
		</table>
	</form:form>
</div>

<%--
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( ".datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
    } );
</script>--%>
