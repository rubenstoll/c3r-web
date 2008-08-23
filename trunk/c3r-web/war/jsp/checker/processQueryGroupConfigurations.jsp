<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Configuring Queries</title>
</head>
<body>

<jsp:useBean
	id="thematicConfig"
	class="org.unitedstollutions.c3r.model.ThematicQueryGroupConfiguration" scope="session" />
<jsp:useBean id="destinationRequete"
	class="org.unitedstollutions.c3r.model.DestinationQueryGroupConfiguration" scope="session" />
<jsp:useBean id="extraitTypeLEG"
	class="org.unitedstollutions.c3r.model.ExtraiteTypeLEGQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="extraitTitre"
	class="org.unitedstollutions.c3r.model.ExtraiteTitreQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="domainApplication"
	class="org.unitedstollutions.c3r.model.DomainApplicationQueryGroupConfiguration" scope="session"/>
<jsp:useBean id="subDomainApplication"
	class="org.unitedstollutions.c3r.model.SubDomainApplicationQueryGroupConfiguration" scope="session"/>



<jsp:setProperty name="thematicConfig" property="value" value="${param.thematic}" />
<jsp:setProperty name="destinationRequete" property="value" value="${param.destination}" />
<jsp:setProperty name="extraitTypeLEG" property="value" value="${param.extraitTyp}" />
<jsp:setProperty name="extraitTitre" property="value" value="${param.extraitTitre}" />
<jsp:setProperty name="domainApplication" property="value" value="${param.domainApp}" />
<jsp:setProperty name="subDomainApplication" property="value" value="${param.subDomainApp}" />

<jsp:forward page="/checker/main" />

<!--Configuration done ...-->
<!---->
<!---->
<!--The following values were configured:-->
<!---->
<!--Thematic:-->
<!--<jsp:getProperty name="thematicConfig"  property="value"/>-->
<!--<jsp:getProperty name="destinationRequete"  property="value"/>-->
<!--<jsp:getProperty name="extraitTypeLEG"  property="value"/>-->
<!--<jsp:getProperty name="extraitTitre"  property="value"/>-->
<!--<jsp:getProperty name="domainApplication"  property="value"/>-->
<!--<jsp:getProperty name="subDomainApplication"  property="value"/>-->

</body>
</html>