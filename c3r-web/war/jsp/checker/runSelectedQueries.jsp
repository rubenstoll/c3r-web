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
<c:out value="${requestScope.message}" /><br /><br />


<jsp:useBean id="queryManager" 
	class="org.unitedstollutions.c3r.model.QueryResultsManager" scope="session" />

<table border="1"> 
	<thead>
		<tr>	
			<td>
			</td>
			<td>
				name
			</td>
			<td>
				description
			</td>
			<td>
				sparql content
			</td>
			</tr>
	</thead>
	<c:forEach var="query" items="${queryManager.queries}">
		<tr>
			<td>
				<input type="checkbox" name="selectedQueries" value="${query.value.name}">
			</td>
			<td>
				<c:out value="${query.value.name}" />
			</td>
			<td>
				<c:out value="${query.value.description}" />
			</td>
			<td>
				${query.value.sparql}
			</td>
			</tr>
	</c:forEach>
</table>

<br /><br />
Selected Queries Query run results:<br />
<c:forEach var="query" items="${queryManager.queries}">
	<c:set var="q" value="${query.value.sparql}"/>
	<c:set var="queryErrors" value="${stl:validateQuery(pageContext, q)}" />
	<c:choose>
	<c:when test="${empty queryErrors}">
		<stl:send-query query="${q}" engine="${checkingEngine}" /><br /><br />
	</c:when>
	<c:otherwise>${queryErrors}</c:otherwise>
	</c:choose>
</c:forEach>

<br /><br /><br /><br />
</body>
</html>