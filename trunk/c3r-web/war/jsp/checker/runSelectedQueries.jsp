<%@ include file="/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Selected Queries Results</title>
</head>
<body>

<!--remove the default or first engine wrapper for more heap space-->
<!--removing the engine doesn't do much ... shame-->
<c:if test="${not empty applicationScope['defaultEngineWrapper']}">
	Removing the default engine wrapper ...
	<c:remove var="defaultEngineWrapper" scope="application" />
</c:if>

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


<br /><br /><br /><br />
</body>
</html>