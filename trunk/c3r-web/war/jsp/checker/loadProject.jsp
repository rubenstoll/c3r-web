<%--
 * Copyright (c) 2003 UnitedStollutions, Inc.  All rights reserved.  Switzerland.
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Load Project</title>
</head>
<body>

<%--
 * try reading from the following address
 * http://rainbow.essi.fr/~anastasiya/data/test_ifc.ifcxml
--%>

Load your construction project to be checked.
<p>You can choose one of the following <b>default</b> projects: <br />
<form name="ifcReader" method="POST" action="loadProject.form">
<input type="radio" name="projectIfc" value="default" checked="checked">
Default 
<br>
<input type="radio" name="projectIfc" value="default2">
Default 2
<br>
<input type="radio" name="projectIfc" value="default3">
Default 3
<br><p> ... or upload your own construction project by indicating its <b>URI</b>:
<br>
<input type="radio" name="projectIfc" value="uri">
<input type="text" name="ifcUri">
<p>Please note that NO syntax verification of your uploaded project is done
<br />
<br />
<input type="submit" name="submit" value="Submit">
</form>

</body>
</html>