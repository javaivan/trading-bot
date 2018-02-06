<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="domain" value="${pageContext.request.contextPath}"/>
<div>
	<form:form method="POST" action="/currency-history" modelAttribute="insertCurrencyFromCsv">
		<table>
			<tr>
				<td><form:label path="fileName">Name</form:label></td>
				<td>
					<form:select path="fileName">
						<c:forEach items="${currencyHistories}" var="currencyHistory">
							<form:option value="${currencyHistory.name}" label="${currencyHistory.name}(${currencyHistory.size})"/>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td><form:label path="start">Start</form:label></td>
				<td><form:input path="start" cssClass="datepicker" maxlength="8"/></td>
			</tr>
			<tr>
				<td><form:label path="finish">Finish</form:label></td>
				<td><form:input path="finish" cssClass="datepicker"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"/></td>
			</tr>
		</table>
	</form:form>
</div>

<div id="currencyHistoryContainer" style="height: 370px; width: 100%;"></div>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="${domain}/resources/js/canvasjs.min.js" type="text/javascript"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function() {
        $( ".datepicker" ).datepicker({
            changeYear: true,
            dateFormat: 'dd.mm.y',
//            timeFormat:  'hh:mm'
        });
    });
    window.onload = function () {
		var deal = new CanvasJS.Chart("currencyHistoryContainer", {
				theme: "light2", // "light1", "light2", "dark1", "dark2"
				animationEnabled: true,
				zoomEnabled: true,
				title: {
					text: "Deals"
				},
				axisX: {
					valueFormatString: "MMM YYYY"
				},
				axisY2: {
					title: "Median List Price",
					prefix: "$",
					suffix: "K"
				},
				toolTip: {
					shared: true
				},
				legend: {
					cursor: "pointer",
					verticalAlign: "top",
					horizontalAlign: "center",
					dockInsidePlotArea: true,
					itemclick: toogleDataSeries
				},
				data: [
					{
						type: "line",
						name: "Price",
						color: "#C24642",
						showInLegend: true,
						markerSize: 0,
						// yValueFormatString: "$#,###k",
						dataPoints: [
							<c:forEach items="${currencies}" var="currency">
							{ x: new Date(${currency.unixTime}*1000), y: ${currency.price}},
							</c:forEach>
						]
					}
				]
		});
        deal.render();
        function toogleDataSeries(e){
            if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                e.dataSeries.visible = false;
            } else{
                e.dataSeries.visible = true;
            }
            deal.render();
        }

    }



</script>

