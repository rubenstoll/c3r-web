<%@ include file="/jsp/include.jsp"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>

<c:if test="${!empty param['name']}">  
  <stl:modify-annot kind="add"
    xpath="/rdf:RDF"
    forceUpdate="true"
    file="human_2007_04_17.rdf"
    humans="http://www.inria.fr/2007/04/17/humans.rdfs#"
    rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
    <humans:Person rdf:ID="${stl:encode(param['name'])}"
     xmlns:humans="http://www.inria.fr/2007/04/17/humans.rdfs#"
       xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <humans:name>${param["name"]}</humans:name>
    </humans:Person>
  </stl:modify-annot>
  <p>A new person named "${param['name']}" was created.</p> 
</c:if>

</body>
</html>