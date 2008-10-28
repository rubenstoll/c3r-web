<%@ include file="/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Selected Queries Results</title>
</head>
<body>

<!--  
These are the available engine attributes within sewese.  There is no attribute to specify the
IFC project file. 

ontoDir 
The relative (to the webapp directory) path name of the ontologies directory e.g. ontoDir="WEB-INF/data/schemas" 

annotDir 
The relative (to the webapp directory) path name of the annotations directory e.g. annotDir="WEB-INF/data/annotations" 

ruleDir 
The relative (to the webapp directory) path name of the rules directory e.g. ruleDir="WEB-INF/data/rules" 

resourcesDir 
The relative (to the webapp directory) path name of the other resources directory to load. 

projectionMax 
Maximum number of projection computed to answer a query ; default is 10000. 

resultMax
Maximum number of results returned after possibly grouping projections ; default is 100. 

resultJoin
Groups projections that share the same first concept into one result ; default is false. 

var
The variable name where to store the engine instance ; default is "defaultEngineWrapper" 

scope
The scope where to store the engine instance. One of 'page', 'request', 'session' or 'application' ; default is 'application'. 
-->

<!--remove the default or first engine wrapper for more heap space-->
<!--removing the engine doesn't do much ... shame-->
<c:if test="${not empty applicationScope['defaultEngineWrapper']}">
	Removing the default engine wrapper ...
	<c:remove var="defaultEngineWrapper" scope="application" />
</c:if>

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