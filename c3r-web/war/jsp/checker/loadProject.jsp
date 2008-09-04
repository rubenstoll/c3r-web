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
Select the project configuration location: <br /><br />
<form name="projectConfig" method="POST" action="configureProject">

<input type="radio" name="ProjectIfc" value="Default" checked="checked">
Default 
<br>
<input type="radio" name="ProjectIfc" value="Local">
Local 
<br>
<input type="radio" name="ProjectIfc" value="Custom">
Custom
<input type="file" name="fileName">
<br>
</form>

</body>
</html>