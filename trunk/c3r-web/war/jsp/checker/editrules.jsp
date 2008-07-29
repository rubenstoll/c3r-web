<%--
 * Copyright (c) 2003 UnitedStollutions, Inc.  All rights reserved.  Switzerland.
--%>

<%@ include file="/jsp/include.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>

<c:if
	test="${!empty param['name_before'] and !empty param['name_after']}">
	<p>Parameters found "${param['name_before']}" and
	"${param['name_after']}".</p>
	<%--   
	<c:set var="myQuery">ASK { ?x ontoCC:Porte "${param['name_before']}" }</c:set>
 <stl:if test="${myQuery}">
    <p>Person found.</p>
    <stl:modify-annot kind="replace"
      xpath="/rdf:RDF/ontoCC:Porte/overallWidth[text()='${param['name_before']}']"
      forceUpdate="true"
      file="data/annotations/ifc02.rdf"
      ontoCC="http://www.owl-ontologies.com/Ontology1205837312.owl#"
      rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <ontoCC:Porte xmlns:ontoCC="http://www.owl-ontologies.com/Ontology1205837312.owl">${param['name_after']}</humans:name>
    </stl:modify-annot>
    <p>The name "${param['name_before']}" was changed to "${param['name_after']}".</p>
  </stl:if>
  --%>

</c:if>

</body>
</html>