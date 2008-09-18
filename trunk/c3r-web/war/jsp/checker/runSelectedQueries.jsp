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
<br /><br />
message 1: <c:out value="${requestScope.message1}" /><br />
message 2: <c:out value="${requestScope.message2}" /><br />
result 1: <c:out value="${sessionScope.checkedResults1}" /><br />
result 2: <c:out value="${sessionScope.checkedResults2}" /><br />
mymessage: <c:out value="${requestScope.mymessage}" /><br />
list queries using paramValues.name: <c:out value="${param.selectedQueries}" /><br />

<c:choose>
	<c:when test="${not empty paramValues.selectedQueries}">
		Queries you selected:
		<ul>
			<c:forEach items="${paramValues.selectedQueries}" var="adj">
				<li><c:out value="${adj}"/></li>
			</c:forEach>
		</ul>
	</c:when>
	<c:otherwise>
		You didn't choose any feedback checkboxes.
	</c:otherwise>
</c:choose>

</body>
</html>