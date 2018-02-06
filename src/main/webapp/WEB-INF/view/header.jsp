<%@ page pageEncoding="UTF-8"%>
<c:set var="domain" value="${pageContext.request.contextPath}"/>
<div>The Header</div>
<ul>
    <a href="${domain}/">home</a>
    <a href="${domain}/insert-currency-from-csv">Insert Currency From Csv</a>
    <a href="${domain}/currency-history">Currency History</a>
</ul>