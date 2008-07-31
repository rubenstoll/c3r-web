<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<c:set var="contextPath"
	value="/${pageContext.servletContext.servletContextName}" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${contextPath}/examples.css" />
<title>Engine Initializer</title>
</head>

<body>
<div id="content">
<h2>init/reload demo</h2>
<p>First of all, the init tag MUST be called in order to be able to
work with the CORESE engine.</p>
<p>The init tag creates an instance of the CORESE engine, intializes
it and loads the ontologies, annotations and rules. Nota that it is
possible to work with more than one engine in the same application. In
this case, just specify a name to the engine while intializing it with
the init tag "var" attribute in order to be able to call this instance
of CORESE later. (The CORESE instance is of type IEngine. See the <a
	href="http://www-sop.inria.fr/edelweiss/wiki/wakka.php?wiki=CoreseDocumentations">Corese
API</a> for more details). You can also specified the scope of this
variable. Default is application's scope. If no name is specified, you
can work with it later by calling the default one (i.e without
specifying an engine attribute).</p>
<p>The reload tag reloads the specified engine or the default one if
no engine is specified.</p>

<p>In this page, an engine with the name "testEngine" is created and
intialized on the following semantic data sources:</p>
<ul>
	<li>Ontology directory: 'WEB-INF/data/schemas'</li>
	<li>Annotations directory: 'WEB-INF/data/annotations'</li>
	<li>Rules directory: 'WEB-INF/data/rules'</li>
</ul>
<p>It can be created in the scope of the current page, the current
session or the application (here: the current page's scope).</p>
<p>The used namespace(s) can be declared in order not to have to do
so while sending SPARQL queries with PREFIX clause (here the geo
namespace).</p>
<p>A set of parameters can be set on the engine while initializing
it. See the TLD doc.</p>
<p>The engine is reloaded in this page in order to show how it works
only.</p>

<stl:init var="testEngine" scope="page" ontoDir="WEB-INF/data/schemas"
	annotDir="WEB-INF/data/annotations" ruleDir="WEB-INF/data/rules"
	geo="http://rdf.insee.fr/geo#" /> <stl:reload engine="${testEngine}" />
</div>

</body>
</html>