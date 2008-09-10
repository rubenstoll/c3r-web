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
<c:set var="selectedDestinationRequest" value="${destinationRequete.value}" />
<c:set var="selectedExtraitTypeLEG" value="${extraitTypeLEG.value}" />
<c:set var="selectedExtractedTitle" value="${extraitTitre.value}" />
<c:set var="selectedDomainApplication" value="${domainApplication.value}" />
<c:set var="selectedSubDomainApplication" value="${subDomainApplication.value}" />

<!--sparql query definitions-->
<c:set var="thematicQuery">
	SELECT ?queryName ?queryType ?queryDescription WHERE { 
		?queryName annoComplexe:thematiqueRequete ?queryType 
		FILTER (xsd:string(?queryType) ~ '${selectedThematic}') 
		?queryName annoComplexe:textRequete ?queryDescription
	}
</c:set>
<c:set var="destinationRequestQuery">
	SELECT ?queryName ?queryType ?queryDescription WHERE {
		?queryName annoComplexe:destinationRequete ?queryType 
		FILTER (xsd:string(?queryType) ~ '${selectedDestinationRequest}') 
		?queryName annoComplexe:textRequete ?queryDescription }
</c:set>
<c:set var="extraitTypeLEGQuery">
	SELECT ?queryName ?queryType ?queryDescription WHERE { 
		?queryName annoComplexe:extraitTypeLEG ?queryType 
		FILTER (xsd:string(?queryType) ~ '${selectedExtraitTypeLEG}') 
		?queryName annoComplexe:textRequete ?queryDescription }
</c:set>
<c:set var="extraitTitreQuery">
	SELECT ?queryName ?queryType ?queryDescription WHERE {
		?queryName annoComplexe:extraitTitre ?queryType 
		FILTER (xsd:string(?queryType) ~ '${selectedExtractedTitle}') 
		?queryName annoComplexe:textRequete ?queryDescription }
</c:set>
<c:set var="domainApplicationQuery">
	SELECT ?queryName ?queryType ?queryDescription WHERE {
		?queryName annoComplexe:domaineApplication ?queryType 
		FILTER (xsd:string(?queryType) ~ '${selectedDomainApplication}') 
		?queryName annoComplexe:textRequete ?queryDescription }
</c:set>
<c:set var="subDomainApplicationQuery">
	SELECT ?queryName ?queryType ?queryDescription WHERE {
		?queryName annoComplexe:sousDomaineApplication ?queryType 
		FILTER (xsd:string(?queryType) ~ '${selectedSubDomainApplication}') 
		?queryName annoComplexe:textRequete ?queryDescription }
</c:set>

<!-- The last filter is hard coded because the master-detail list
 	feature does not work yet -->
<c:set var="complexQuery">
	PREFIX annoComplexe: <http://www.owl-ontologies.com/Ontology1215697160.owl#>
	SELECT ?queryName ?queryDescription ?them ?dest ?leg ?titre ?domaine 
			?sousdomaine ?sparqlContent display xml WHERE {
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
			FILTER (xsd:string(?sousdomaine) ~ '${subDomainApplicationQuery}')        
	}
</c:set>

<!-- all below is what the user sees -->

<b>The query group configuration values</b> <br />
Thematic: <c:out value="${selectedThematic}" /><br />
Destination Request: <c:out value="${selectedDestinationRequest}" /><br />
Extrait Type LEG: <c:out value="${selectedExtraitTypeLEG}" /><br />
Extracted Title: <c:out value="${selectedExtractedTitle}" /><br />
Domain Application: <c:out value="${selectedDomainApplication}" /><br />
Sub Domain Application: <c:out value="${selectedSubDomainApplication}" /><br />

<br /><br />


Found the following queries for thematic:
<div>
<ul>
	<stl:for-each-result query="${thematicQuery}">
		<li>${queryType} <br /> ${queryName} <br /> ${queryDescription} </li>
	</stl:for-each-result>
</ul>
</div>

Found the following queries for destination request:
<div>
<ul>
	<stl:for-each-result query="${destinationRequestQuery}">
		<li>${queryType} <br /> ${queryName} <br /> ${queryDescription} </li>
	</stl:for-each-result>
</ul>
</div>

Found the following queries for extraitTypeLEG:
<div>
<ul>
	<stl:for-each-result query="${extraitTypeLEGQuery}">
		<li>${queryType} <br /> ${queryName} <br /> ${queryDescription} </li>
	</stl:for-each-result>
</ul>
</div>

Found the following queries for extrait Titre:
<div>
<ul>
	<stl:for-each-result query="${extraitTitreQuery}">
		<li>${queryType} <br /> ${queryName} <br /> ${queryDescription} </li>
	</stl:for-each-result>
</ul>
</div>

Found the following queries for domain application:
<div>
<ul>
	<stl:for-each-result query="${domainApplicationQuery}">
		<li>${queryType} <br /> ${queryName} <br /> ${queryDescription} </li>
	</stl:for-each-result>
</ul>
</div>

Found the following queries for sub domain application:
<div>
<ul>
	<stl:for-each-result query="${subDomainApplicationQuery}">
		<li>${queryType} <br /> ${queryName} <br /> ${queryDescription} </li>
	</stl:for-each-result>
</ul>
</div>

Found the following queries for the complex query:
<div>
<ul>
	<stl:for-each-result query="${complexQuery}">
 		<li>
 			${queryName}<br /> ${queryDescription}<br /> ${them}<br /> ${dest}<br /> 
 			${leg}<br />${titre} <br />${domaine}<br />${sousdomaine}<br /> 
 			${sparqlContent}<br /> 
		</li>
	</stl:for-each-result>
</ul>
</div>

</body>
</html>