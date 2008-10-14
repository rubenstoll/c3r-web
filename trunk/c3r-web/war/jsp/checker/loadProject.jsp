<%--
 * Copyright (c) 2003 UnitedStollutions, Inc.  All rights reserved.  Switzerland.
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Load Project</title>
</head>
<body>

<br />
Select one of the following project configuration location: <br /><br />
<form name="ifcReader" method="POST" action="loadProject.form">

<input type="radio" name="projectIfc" value="default" checked="checked">
Default 
<br>
<input type="radio" name="projectIfc" value="uri">
Define URI
<input type="text" name="ifcUri">
<br>
<br />
<br />
<br />
<br />
<input type="submit" name="submit" value="Submit">
</form>

</body>
</html>