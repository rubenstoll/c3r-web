<%--
 * Copyright (c) 2003 UnitedStollutions, Inc.  
 * All rights reserved. Switzerland.
--%>
<%@ include file="/jsp/include.jsp"%>

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

<div> 
<a href="<c:url value="/checker/loadProject"/>">Load Project</a><br />
<a href="<c:url value="/checker/configure"/>">Configure</a><br />
<a href="<c:url value="/checker/queryFinder"/>">Get Queries</a><br />
<a href="<c:url value="/checker/date"/>">Sun Date Example</a><br />
</div><br /><br />


<div>
The project uses the following IFC file:
<c:if test="${empty sessionScope['projectIfc']}">
Project IFC not defined you. MUST define!<br />
</c:if>
<c:if test="${not empty sessionScope['projectIfc']}">
<c:out value="${sessionScope.projectIfc}" /><br />
</c:if>
</div>

<br /><br />


<div> 
The following values are configured for query groups<br /><br />

Thematic:
<jsp:getProperty name="thematicConfig"  property="value"/> <br />
destination Request:
<jsp:getProperty name="destinationRequete"  property="value"/><br />
Type extract LEG:
<jsp:getProperty name="extraitTypeLEG"  property="value"/><br />
Extract title:
<jsp:getProperty name="extraitTitre"  property="value"/><br />
domain application:
<jsp:getProperty name="domainApplication"  property="value"/><br />
sub domain application:
<jsp:getProperty name="subDomainApplication"  property="value"/><br />

</div>
