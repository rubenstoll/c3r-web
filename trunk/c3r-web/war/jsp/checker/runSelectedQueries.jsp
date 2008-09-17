<%@ include file="/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Selected Queries Results</title>
</head>
<body>

<c:if test="${empty sessionScope['checkingEngine']}">
Checking Engine does not exits ... <br />
New Checking Engine created ... <br />
<stl:init var="checkingEngine" scope="session" 
		ontoDir="/data/schemas"
		annotDir="/data/annotations" 
		ruleDir="/data/rules"
		ontoCC="http://www.owl-ontologies.com/Ontology1205837312.owl#"
		rdfs="http://www.w3.org/2000/01/rdf-schema#"/>
</c:if>



</body>
</html>