<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="domain" value="${pageContext.request.contextPath}"/>
<div>
	Main content would go here. Lets try.

</div>
<div id="dealContainer" style="height: 370px; width: 100%;"></div>
<script>

    window.onload = function () {
        var deal = new CanvasJS.Chart("dealContainer", {
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
                }, {
            		type: "line",
					name: "Deal",
					color: "#369EAD",
					showInLegend: true,
					markerSize: 0,
					//yValueFormatString: "$#,###k",
					dataPoints: [
						<c:forEach items="${deals}" var="deal">
							{ x: new Date(${deal.buyCurrency.unixTime}*1000), y: ${deal.buyMainCurrencyBefore}, indexLabel: "buy", markerType: "triangle"},
            				{ x: new Date(${deal.sellCurrency.unixTime}*1000), y: ${deal.sellMainCurrencyAfter}, indexLabel: "sell", markerType: "cross"},
						</c:forEach>
					]
            	}
            ]
        });

<%--<c:forEach items="${deals}" var="deal">
   /*deal.options.data[0].dataPoints.push({x: ${deal.buyCurrency.unixTime},y: ${deal.buyMainCurrency}});
   */deal.options.data[0].dataPoints.push({x: ${deal.sellCurrency.unixTime},y: ${deal.sellMainCurrency}});
</c:forEach>--%>
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
<script src="${domain}/resources/js/canvasjs.min.js" type="text/javascript"></script>
<script>
/*
$( document ).ready(function() {
console.log( "ready!" );
});*/
</script>