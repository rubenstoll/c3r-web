<%@ include file="/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Query Finder</title>
</head>
<body>

<!--create an engine-->
<c:if test="${empty applicationScope['defaultEngineWrapper']}">
Engine does not exits ... <br />
New engine created ... <br />
<stl:init
		ontoDir="/data/schemas"
		annotDir="/data/anno_templates" 
		ruleDir="/data/rules"
		annoComplexe="http://www.owl-ontologies.com/Ontology1215697160.owl#"
		ontoCC="http://www.owl-ontologies.com/Ontology1205837312.owl#"
		rdfs="http://www.w3.org/2000/01/rdf-schema#"/>
</c:if>

<stl:init var="checkingEngine" scope="session" 
		ontoDir="/data/schemas"
		annotDir="/data/annotations" 
		ruleDir="/data/rules"
		ontoCC="http://www.owl-ontologies.com/Ontology1205837312.owl#"
		rdfs="http://www.w3.org/2000/01/rdf-schema#"/>

<!--use the query configuration beans-->
<jsp:useBean
	id="thematicConfig"
	class="org.unitedstollutions.c3r.model.ThematicQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="destinationRequete"
	class="org.unitedstollutions.c3r.model.DestinationQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="extraitTypeLEG"
	class="org.unitedstollutions.c3r.model.ExtraiteTypeLEGQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="extraitTitre"
	class="org.unitedstollutions.c3r.model.ExtraiteTitreQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="domainApplication"
	class="org.unitedstollutions.c3r.model.DomainApplicationQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="subDomainApplication"
	class="org.unitedstollutions.c3r.model.SubDomainApplicationQueryGroupConfiguration" scope="session"/>

<!--variables to be used for the SPARQL queries-->
<c:set var="selectedThematic" value="${thematicConfig.value}" />
<c:if test="${thematicConfig.value} == "not_selected">
	<c:set var="selectedThematic" value="" />
</c:if>
<c:set var="selectedDestinationRequest" value="${destinationRequete.value}" />
<c:set var="selectedExtraitTypeLEG" value="${extraitTypeLEG.value}" />
<c:set var="selectedExtractedTitle" value="${extraitTitre.value}" />
<c:set var="selectedDomainApplication" value="${domainApplication.value}" />
<c:set var="selectedSubDomainApplication" value="${subDomainApplication.value}" />


<!--sparql query definitions-->

<!-- The last filter is hard coded because the master-detail list
 	feature does not work yet -->
<c:set var="complexQuery">
	PREFIX annoComplexe:<http://www.owl-ontologies.com/Ontology1215697160.owl#>
	SELECT DISTINCT ?queryName ?queryDescription ?them ?dest ?leg ?titre ?domaine ?sousdomaine ?sparqlContent display xml WHERE {

			?queryName annoComplexe:textRequete ?queryDescription
			?queryName annoComplexe:contenuRequete ?sparqlContent

			?queryName annoComplexe:thematiqueRequete ?them
			FILTER (xsd:string(?them) ~ '${selectedThematic}')

			?queryName annoComplexe:destinationRequete ?dest
			FILTER (xsd:string(?dest) ~ '${selectedDestinationRequest}')

			?queryName annoComplexe:extraitTypeLEG ?leg
			FILTER (xsd:string(?leg) ~ '${selectedExtraitTypeLEG}')

			?queryName annoComplexe:extraitTitre ?titre
			FILTER (xsd:string(?titre) ~ '${selectedExtractedTitle}')

			?queryName annoComplexe:domaineSimple ?domaine
			FILTER (xsd:string(?domaine) ~ '${selectedDomainApplication}')

			?queryName annoComplexe:sousDomaineSimple ?sousdomaine
			FILTER (xsd:string(?sousdomaine) ~ '${selectedSubDomainApplication}')
	}
</c:set>

<!-- all below is what the user sees -->

<b>The query group configuration values</b> <br />
Thematic: <c:out value="${selectedThematic}" /> <br />
Destination Request: <c:out value="${selectedDestinationRequest}" /><br />
Extrait Type LEG: <c:out value="${selectedExtraitTypeLEG}" /><br />
Extracted Title: <c:out value="${selectedExtractedTitle}" /><br />
Domain Application: <c:out value="${selectedDomainApplication}" /><br />
Sub Domain Application: <c:out value="${selectedSubDomainApplication}" /><br />

<br /><br />
The query being executed is:<br />
${complexQuery}
<br /><br />

<div>
<%-- 
	the value or query name returned with the results after running the query looks
    something like this:
     	value="http://www.owl-ontologies.com/Ontology1215697160.owl-instances#r00080705"
    The idea is to grab the name after the pound sign at the end as the query name
    hashmap key, and checkbox item value: r00080705
--%>

<form name="qryResults" method="post" action="runSelectedQueries">

	<c:set var="queryErrors" value="${stl:validateQuery(pageContext, complexQuery)}" />
	<c:choose>
	<c:when test="${empty queryErrors}">
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
			<stl:for-each-result query="${complexQuery}">
				<tr>
					<td>
						<input type="checkbox" name="queryResult" value="${fn:substringAfter(queryName,"#")}">
					</td>
					<td>
						${fn:substringAfter(queryName,"#")}
					</td>
					<td>
						${queryDescription} 
					</td>
					<td>
						${sparqlContent} 
					</td>
			</stl:for-each-result>
		</table>
	</c:when>
	<c:otherwise>${queryErrors}</c:otherwise>
	</c:choose>

<br /><br />
<input type="submit" name="submit" value="Cancel">
<input type="submit" name="submit" value="Submit">
</form>



</body>
</html>