<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/include.jsp"%>

<html>
<head>
<title>Conformity Checking in Construction : Reasoning</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="c3r.css" />
</head>

<body>

<h1>Conformity Checking in Construction : Reasoning</h1>
<c:if test="${empty applicationScope['defaultEngineWrapper']}">
	<stl:init ontoDir="/data/schemas" annotDir="/data/annotations"
		ruleDir="/data/rules"
		ontoCC="http://www.owl-ontologies.com/Ontology1205837312.owl#" />
	<h2>Engine initialization</h2>
</c:if>

<h2>Test Query</h2>
<div>
<ul>
	<stl:for-each-result
		query="SELECT ?x ?name WHERE { ?x ontoCC:overallWidth ?name }">
		<li>${x} has length ${name}</li>
	</stl:for-each-result>
</ul>
</div>
<h2>Second Test Query</h2>
<div>

<ul>

	<%-- 
	<stl:for-each-result
		query="select distinct ?porte ?rfe ?ro ?locaux display xml 
where
{
{
?porte 	rdf:type 		ontoCC:IfcDoor

?rfe	rdf:type		ontoCC:IfcRelFillsElement
?rfe	ontoCC:relatingOpeningElement	?ro
?ro	rdf:type		ontoCC:IfcOpeningElement
?rfe	ontoCC:relatedBuildingElement	?porte

?rsb	rdf:type		ontoCC:IfcRelSpaceBoundary
?rsb	ontoCC:relatingSpace	?locaux
?rsb	ontoCC:relatedBuildingElement	?ro

?locaux	rdf:type		ontoCC:LocauxMoins100Personnes
}
UNION
{
?porte 	rdf:type 		ontoCC:IfcDoor

?rfe	rdf:type		ontoCC:IfcRelFillsElement
?rfe	ontoCC:relatingOpeningElement	?ro
?ro	rdf:type		ontoCC:IfcOpeningElement
?rfe	ontoCC:relatedBuildingElement	?porte

?rsb	rdf:type		ontoCC:IfcRelSpaceBoundary
?rsb	ontoCC:relatingSpace	?locaux
?rsb	ontoCC:relatedBuildingElement	?ro

?locaux	rdf:type		ontoCC:Locaux
?locaux ontoCC:occupancyNumberPeak ?eff
FILTER ( xsd:integer(?eff) &lt;= 100 ).
}
OPTIONAL 
{
?porte ontoCC:overallWidth 	?width
FILTER ( xsd:integer(?width) &gt;= 90)
}
FILTER (! bound( ?width) )
}">
		<li>${porte} hh ${rfe} ff ${ro} jj ${locaux}</li>
	</stl:for-each-result>
	--%>

</ul>


</div>

</body>
</html>
