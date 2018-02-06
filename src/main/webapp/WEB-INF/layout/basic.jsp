<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<title><tiles:getAsString name="title" /></title>
</head>
<body>
<!-- Header -->
<tiles:insertAttribute name="header" />
<!-- Body -->
<tiles:insertAttribute name="body" />
<!-- Footer -->
<tiles:insertAttribute name="footer" />
Test</body>
</html>