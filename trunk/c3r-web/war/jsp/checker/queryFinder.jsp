<%@ include file="/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Query Finder</title>
</head>
<body>

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



<!--create an engine-->
<c:if test="${empty applicationScope['defaultEngineWrapper']}">
Engine does not exits ... initialized a new one
<stl:init
		ontoDir="/data/schemas"
		annotDir="/data/anno_templates" 
		ruleDir="/data/rules"
		annoComplexe="http://www.owl-ontologies.com/Ontology1215697160.owl#"
		ontoCC="http://www.owl-ontologies.com/Ontology1205837312.owl#"
		rdfs="http://www.w3.org/2000/01/rdf-schema#"/>
</c:if>
<!--<stl:reload engine="${c3rEngine}" />-->

Found the following queries for thematic:
<jsp:getProperty name="thematicConfig"  property="value"/>
<div>
<ul>
	<stl:for-each-result
		query="SELECT ?nameQuery ?textQuery WHERE { ?nameQuery annoComplexe:textRequete ?textQuery }">
		<li>${nameQuery} <br /> ${textQuery}</li>
	</stl:for-each-result>
</ul>
</div>


Found the following queries for thematic: heere
<div>
<ul>
	<stl:for-each-result
		query="SELECT ?nameQuery ?themQuery ?textR WHERE { ?nameQuery annoComplexe:thematiqueRequete ?themQuery 
				FILTER (xsd:string(?themQuery) ~ 'acc') ?nameQuery annoComplexe:textRequete ?textR }">

		<li>${nameQuery} <br/> ${themQuery}</li>
	</stl:for-each-result>
</ul>
</div>
choose only circulaire
<div>
<ul>
	<stl:for-each-result
		query="SELECT ?nameQuery ?typeL ?textR WHERE { ?nameQuery annoComplexe:extraitTypeLEG  
				?typeL FILTER (xsd:string(?typeL) ~ 'circulaire') ?nameQuery annoComplexe:textRequete ?textR }">
		<li>${typeL} <br /> ${nameQuery} <br /> ${textR} </li>
	</stl:for-each-result>
</ul>
</div>


</body>
</html>