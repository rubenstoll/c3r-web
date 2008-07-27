<%@ include file="/jsp/include.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>

<c:if test="${!empty param['name_before'] and !empty param['name_after']}">
 <p>Parameters found "${param['name_before']}" and "${param['name_after']}".</p>
  <c:set var="myQuery">ASK { ?x humans:name "${param['name_before']}" }</c:set>
  <stl:if test="${myQuery}">
    <p>Person found.</p>
    <stl:modify-annot kind="replace"
      xpath="/rdf:RDF/humans:Person/humans:name[text()='${param['name_before']}']"
      forceUpdate="true"
      file="human_2007_04_17.rdf"
      humans="http://www.inria.fr/2007/04/17/humans.rdfs#"
      rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <humans:name xmlns:humans="http://www.inria.fr/2007/04/17/humans.rdfs#">${param['name_after']}</humans:name>
    </stl:modify-annot>
    <p>The name "${param['name_before']}" was changed to "${param['name_after']}".</p>
  </stl:if>
</c:if>

</body>
</html>