<%--
 * Copyright (c) 2003 UnitedStollutions, Inc.  All rights reserved.  Switzerland.
--%>

<%@ include file="/jsp/include.jsp"%>

<html>
<head>
<title>Conformity Checking in Construction : Reasoning</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="c3r.css" />
</head>

<body>
<!--<a href="<c:url value="home"/>">Home</a><p />-->

<h2>Date page</h2>
<div>
  <form action="date" method="post">
    Do dates <input type="SUBMIT" name="Submit"></input>
  </form>
</div>

<h2>Run defined query</h2>
<div>
  <form action="check" method="post">
    Run query <input type="SUBMIT" name="Submit"></input>
  </form>
</div>

<h2>Browse the ontology</h2>
<div>
  Open a page to <a href="checker/check">browse ontology.</a>
</div>

<h2>Add a rule</h2>
<div>
  <form action="addrule" method="post">
    Add a person named: <input type="TEXT" name="name" size="30"></input>
    <input type="SUBMIT" name="Submit"></input>
  </form>
</div>

<h2>Edit a rule</h2>
<div>
  <form action="editrules" method="post">
    Actual name: <input type="TEXT" name="name_before" size="30"></input>
    New name: <input type="TEXT" name="name_after" size="30"></input>
    <input type="SUBMIT" name="Submit"></input>
  </form>
</div>

<h2>Delete a rule</h2>
<div>
  <form action="deleterule" method="post">
    Delete the person named: <input type="TEXT" name="name" size="30"></input>
    <input type="SUBMIT" name="Submit"></input>
  </form>
</div>

</body>
</html>
