<%--
 * Copyright (c) 2003 UnitedStollutions, Inc.  
 * All rights reserved. Switzerland.
--%>

<c:set var="contextPath"
	value="/${pageContext.servletContext.servletContextName}" />

<!--<c:out value="${contextPath}"/>-->

<div> 
<a href="<c:url value="/checker/loadProject"/>">Load Project</a><br />
<a href="<c:url value="/checker/configure"/>">Configure</a><br />
<a href="<c:url value="/checker/date"/>">Sun Date Example</a><br />
</div>