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
	<c:remove var="defaultEngineWrapper" scope="application" />
	NOTE: Removing the default engine wrapper
</c:if>
<br><br>
<c:out value="${requestScope.message}" /><br /><br />


<jsp:useBean id="subQueryRunResults" 
	class="org.unitedstollutions.c3r.model.QueryResultsManager" scope="session" />

<table border="1"> 
	<thead>
		<tr>	
			<td>
			</td>
			<td>
				Query Reference Number
			</td>
			<td>
				Result
			</td>
		</tr>
	</thead>
	<c:forEach var="subResults" items="${subQueryRunResults.results}">
		<tr>
			<td>
				<input type="checkbox" name="selQryRsults" value="${subResults.key}">
			</td>
			<td>
				<c:out value="${subResults.key}" />
			</td>
			<c:forEach var="qryResultsValues" items="${subResults.value}">
			<td>
				<c:out value="${qryResultsValues}" />
			</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

<br /><br />


<br /><br /><br /><br />
</body>
</html>