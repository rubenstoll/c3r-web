<%@ include file="/jsp/include.jsp"%>

<html>
<head>
<title>Configure Project</title>
</head>
<body>


<center>
<h2>Scope of Conformity Checking</h2>
</center>
<!-- query configuration beans available. -->
<jsp:useBean
	id="thematicConfig"
	class="org.unitedstollutions.c3r.model.ThematicQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="destinationRequete"
	class="org.unitedstollutions.c3r.model.DestinationQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="extraitTypeLEG"
	class="org.unitedstollutions.c3r.model.ExtraiteTypeLEGQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="extraitTitre"
	class="org.unitedstollutions.c3r.model.ExtraiteTitreQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="domainApplication"
	class="org.unitedstollutions.c3r.model.DomainApplicationQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="subDomainApplication"
	class="org.unitedstollutions.c3r.model.SubDomainApplicationQueryGroupConfiguration" scope="session"/>


<form name="qryCfg" method="post"
	action="processQueryGroupConfigurations">

Choose the <b>thematics</b> of the conformity requirement to be checked
<c:set var="selected" value="${thematicConfig.value}" />
<select name="thematic" size="1">
	<c:forEach var="configurations" items="${thematicConfig.contents}">
		<c:if test="${configurations.key == selected}">
			<option selected value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>
		<c:if test="${configurations.key != selected}">
			<option value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>
	</c:forEach>
</select><br />
<br />

Choose the <b>type of the building</b> you want to check 
<c:set var="selected" value="${destinationRequete.value}" />
<select name="destination" size="1">
	<c:forEach var="configurations" items="${destinationRequete.contents}">
		<c:if test="${configurations.key == selected}">
			<option selected value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>
		<c:if test="${configurations.key != selected}">
			<option value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>	</c:forEach>
</select><br />
<br />

Choose the <b>type of the regulation text</b>  
<c:set var="selected" value="${extraitTypeLEG.value}" />
<select name="extraitTyp" size="1">
	<c:forEach var="configurations" items="${extraitTypeLEG.contents}">
		<c:if test="${configurations.key == selected}">
			<option selected value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>
		<c:if test="${configurations.key != selected}">
			<option value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>	</c:forEach>
</select><br />
<br />

Choose the <b>regulation text</b> from a list of available ones 
<c:set var="selected" value="${extraitTitre.value}" />
<select name="extraitTitre" size="1">
	<c:forEach var="configurations" items="${extraitTitre.contents}">
		<c:if test="${configurations.key == selected}">
			<option selected value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>
		<c:if test="${configurations.key != selected}">
			<option value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>	</c:forEach>
</select><br />
<br />

Which <b>building element</b> you would like to check?  
<c:set var="selected" value="${domainApplication.value}" />
<select name="domainApp" size="1">
	<c:forEach var="configurations" items="${domainApplication.contents}">
		<c:if test="${configurations.key == selected}">
			<option selected value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>
		<c:if test="${configurations.key != selected}">
			<option value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>	</c:forEach>
</select><br />
<br />

<!-- The sub domain depends on what is chosen for the application
domain.  There is a one to one mapping in the C3R descriptions document.
A master detail list is needed to implement this. -->
Would you like to <b>detail</b> the type of the <b>building element</b>?  
<c:set var="selected" value="${subDomainApplication.value}" />
<select name="subDomainApp" size="1">
	<c:forEach var="configurations"
		items="${subDomainApplication.contents}">
		<c:if test="${configurations.key == selected}">
			<option selected value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>
		<c:if test="${configurations.key != selected}">
			<option value="${configurations.key}" name="value">${configurations.value} </option>
		</c:if>	</c:forEach>
</select><br />
<br />

<input type="submit" name="submit" value="Cancel">
<input type="submit" name="submit" value="Submit">
</form>


</body>
</html>