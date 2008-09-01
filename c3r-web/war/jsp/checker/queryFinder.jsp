<%@ include file="/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Query Finder</title>
</head>
<body>


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

Found the following queries:
<div>
<ul>
	<stl:for-each-result
		query="SELECT ?nameQuery ?textQuery WHERE { ?nameQuery annoComplexe:textRequete ?textQuery }">
		<li>${nameQuery} <br /> ${textQuery}</li>
	</stl:for-each-result>
</ul>
</div>
choose thematics
<div>
<ul>
	<stl:for-each-result
		query="SELECT ?nameQuery ?themQuery WHERE { ?nameQuery annoComplexe:thematiqueRequete ?themQuery }">
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
complex search - does not work
<div>
<ul>
	<stl:for-each-result
		query="SELECT ?subclasses ?numberQuery ?applicationValue ?correspondingClass WHERE 
				{ ?subclasses direct::rdfs:subClassOf ?correspondingClass filter(?subclasses ^ontoCC:) 
				?numberQuery annoComplexe:sousDomaineApplication ?applicationValue ?applicationValue rdf:type 
				?correspondingClass FILTER (?correspondingClass ~ 'salle') }">
		<li>${subclasses} number ${numberQuery} hqs the follozing applicationValue <br /> ${applicationValue} </li>
	</stl:for-each-result>
</ul>
</div>
</body>
</html>