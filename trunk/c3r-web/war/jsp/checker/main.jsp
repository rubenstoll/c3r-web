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
<jsp:useBean id="project" class="org.unitedstollutions.c3r.model.ProjectIfc" scope="application"/> 

<div> 
<a href="<c:url value="/checker/loadProject"/>">Load Project</a><br />
<a href="<c:url value="/checker/configure"/>">Configure</a><br />
<a href="<c:url value="/checker/queryFinder"/>">Get Queries</a><br />
<!-- a href="<c:url value="/checker/date"/>">Sun Date Example</a><br /> -->
</div>
<p>
<b>Current settings</b><p>
<div>
The checking will be done for the following <b>construction project</b>: 
${project.ifcFile} 
<br> 
</div>

<div> 
<br/>
The <b>scope of conformity checking</b> is defined by the following characteristics:<br />

 - thematic of conformity requirements:
<jsp:getProperty name="thematicConfig"  property="value"/> <br />
 - destination of a building:
<jsp:getProperty name="destinationRequete"  property="value"/><br />
 - type of the regulation text:
<jsp:getProperty name="extraitTypeLEG"  property="value"/><br />
 - regulation text:
<jsp:getProperty name="extraitTitre"  property="value"/><br />
 - building elements to be checked:
<jsp:getProperty name="domainApplication"  property="value"/><br />
 - and more precisely their components:
<jsp:getProperty name="subDomainApplication"  property="value"/><br />

</div>
