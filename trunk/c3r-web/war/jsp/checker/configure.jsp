<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Configure Project</title>

<script type="text/javascript">
	function displaymessage() {
		alert("Hello World!" + "The current value of the selected item is:" +
				document.qryCfg.domainApp.value);
	}

	function displayFormData() {
		win2 = open("", "window2")
		win2.document.open("text/plain")
		win2.document.writeln("This document has " + document.forms.length
				+ " forms.")
		var i = 0;
		var j = 0;
		for (i = 0; i < document.forms.length; ++i) {
			win2.document.writeln("Form " + i + " has "
					+ document.forms[i].elements.length + " elements.")
			win2.document.writeln("Form name:" 	+ document.forms[i].name)
			for (j = 0; j < document.forms[i].elements.length; ++j) {
				win2.document.writeln((j + 1) + " A "
						+ document.forms[i].elements[j].type + " element.")
				win2.document.writeln((j + 1) + " Name: "
								+ document.forms[i].elements[j].name + " !")
				win2.document.writeln((j + 1) + " Value: "
								+ document.forms[i].elements[j].value + " ...")
			}
		}
		win2.document.close()
		return false
	}
</script>

</head>
<body>

<center>
<h1>Query Configuration</h1>
</center>
<br />



<form name="qryCfg" method="POST" action="/checker/main">Thematic <jsp:useBean
	id="thematicConfig"
	class="org.unitedstollutions.c3r.model.ThematicQueryGroupConfiguration" />
<select name="thematic" size="1">
	<c:forEach var="configurations" items="${thematicConfig.contents}">
		<option value="${configurations.key}">${configurations.value}</option>
	</c:forEach>
</select><br />
<br />

destination Request <jsp:useBean id="destinationRequete"
	class="org.unitedstollutions.c3r.model.DestinationQueryGroupConfiguration" />
<select name="destination" size="1">
	<c:forEach var="configurations" items="${destinationRequete.contents}">
		<option value="${configurations.key}">${configurations.value}</option>
	</c:forEach>
</select><br />
<br />

extrait Type LEG <jsp:useBean id="extraitTypeLEG"
	class="org.unitedstollutions.c3r.model.ExtraiteTypeLEGQueryGroupConfiguration" />
<select name="extraitTyp" size="1">
	<c:forEach var="configurations" items="${extraitTypeLEG.contents}">
		<option value="${configurations.key}">${configurations.value}</option>
	</c:forEach>
</select><br />
<br />

extrait Titre <jsp:useBean id="extraitTitre"
	class="org.unitedstollutions.c3r.model.ExtraiteTitreQueryGroupConfiguration" />
<select name="extraitTitre" size="1">
	<c:forEach var="configurations" items="${extraitTitre.contents}">
		<option value="${configurations.key}">${configurations.value}</option>
	</c:forEach>
</select><br />
<br />

domaine Application <jsp:useBean id="domainApplication"
	class="org.unitedstollutions.c3r.model.DomainApplicationQueryGroupConfiguration" />
<select name="domainApp" onchange="displaymessage()" size="1">
	<c:forEach var="configurations" items="${domainApplication.contents}">
		<option value="${configurations.key}">${configurations.value}</option>
	</c:forEach>
</select><br />
<br />

<!-- The sub domain depends on what is chosen for the application
domain.  There is a one to one mapping in the C3R descriptions document. -->
sousDomaineApplication <jsp:useBean id="subDomainApplication"
	class="org.unitedstollutions.c3r.model.SubDomainApplicationQueryGroupConfiguration" />
<select name="subDomainApp" onchange="displayFormData()" size="1">
	<c:forEach var="configurations"
		items="${subDomainApplication.contents}">
		<option value="${configurations.key}">${configurations.value}</option>
	</c:forEach>
</select><br />
<br />


<input type="submit" name="Submit" value="Configure"></form>



</body>
</html>