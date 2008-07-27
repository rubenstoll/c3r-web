<%@ include file="/jsp/include.jsp"%>

<html>
<head>
<title>Conformity Checking in Construction : Reasoning</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="c3r.css" />
</head>

<body>
<a href="<c:url value="home"/>">Home</a><p />

Note: Fix this page because once results have been rendered to user the servlet is
not executed again<p />

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


<h2>Browse the ontology</h2>
<div>
  Open a page to <a href="browse.jsp">browse ontology.</a>
</div>

<h2>Add a rule</h2>
<div>
  <form action="add_person.jsp" method="post">
    Add a person named: <input type="TEXT" name="name" size="30"></input>
    <input type="SUBMIT" name="Submit"></input>
  </form>
</div>

<h2>Edit a rule</h2>
<div>
  <form action="edit_person.jsp" method="post">
    Actual name: <input type="TEXT" name="name_before" size="30"></input>
    New name: <input type="TEXT" name="name_after" size="30"></input>
    <input type="SUBMIT" name="Submit"></input>
  </form>
</div>

<h2>Delete a rule</h2>
<div>
  <form action="delete_person.jsp" method="post">
    Delete the person named: <input type="TEXT" name="name" size="30"></input>
    <input type="SUBMIT" name="Submit"></input>
  </form>
</div>

</body>
</html>
