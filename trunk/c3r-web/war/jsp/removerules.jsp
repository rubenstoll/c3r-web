<%--
 * Copyright (c) 2003 UnitedStollutions, Inc.  All rights reserved.  Switzerland.
--%>

<%@ include file="/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Removing Rules</title>
</head>
<body>
<c:if test="${!empty param['name']}">
	<c:set var="myQuery">ASK { ?x humans:name "${param.name}" }</c:set>
	<p>Parameter found "${param['name']}".</p>
	<stl:if test="${myQuery}">
		<p>Person found.</p>
		<stl:modify-annot kind="del"
			xpath="//*[child::humans:name='${param['name']}']" forceUpdate="true"
			file="human_2007_04_17.rdf"
			humans="http://www.inria.fr/2007/04/17/humans.rdfs#"
			rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" />
		<p>"${param['name']}" was deleted.</p>
	</stl:if>
</c:if>
</body>
</html>