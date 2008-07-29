<%--
 * Copyright (c) 2003 UnitedStollutions, Inc.  All rights reserved.  Switzerland.
--%>

<%@ include file="/jsp/include.jsp"%>

<html>
<head>
<title>Conformity Checking in Construction : Reasoning</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="c3r.css" />
</head>

<body>
<a href="<c:url value="home"/>">Home</a>

<p />Note: Fix this page because once results have been rendered to
user the servlet is not executed again
<p />
<h1>Conformity Checking in Construction : Reasoning</h1>

<h2>Engine initialization</h2>

<c:if test="${empty applicationScope['defaultEngineWrapper']}">
	<stl:init ontoDir="/data/schemas" annotDir="/data/annotations"
		ruleDir="/data/rules"
		ontoCC="http://www.owl-ontologies.com/Ontology1205837312.owl#" />
</c:if>
<h2>Test Query</h2>
<div>
<ul>
	<stl:for-each-result
		query="SELECT ?x ?name WHERE { ?x ontoCC:overallWidth ?name }">
		<li>${x} has length ${name}</li>
	</stl:for-each-result>
</ul>
</div>

</body>
</html>
