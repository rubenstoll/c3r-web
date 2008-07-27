<%@ include file="/jsp/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Browse Ontologies</title>
</head>
<body>

<c:choose>
  <c:when test="${!empty param['uri']}">
    <h1>
      <stl:for-each-label uri="${stl:decode(param['uri'])}" var="currentLabel" lang="en" varStatus="myStatus">
        ${currentLabel} 
        <c:if test="${!myStatus.last}"> - </c:if>
      </stl:for-each-label>
    </h1>
    <h2>Comments:</h2>
    <stl:for-each-comment uri="${stl:decode(param['uri'])}" var="currentComment" lang="en" varStatus="myStatus">
      <p>${currentComment}</p>
    </stl:for-each-comment>        
    <h2>Parents:</h2>
    <ul>
      <stl:for-each-parent root="${stl:decode(param['uri'])}" var="current">
      <li><a href="?uri=${stl:encode(current)}">${stl:label(pageContext, current,'en', true)}</a></li>
      </stl:for-each-parent>  
    </ul>           
    <h2>Children:</h2>
    <ul>
      <stl:for-each-child root="${stl:decode(param['uri'])}" var="current">
      <li><a href="?uri=${stl:encode(current)}">${stl:label(pageContext, current,'en', true)}</a></li>
      </stl:for-each-child>  
    </ul>
    <h2><a href="?uri=">back to roots</a></h2>
  </c:when>
  <c:otherwise>
    <h1>Roots</h1>
    <h2>concepts:</h2>
    <ul>
      <stl:for-each-root-concept var="current">
        <c:set var="label" value="${stl:label(pageContext, current, 'en', true)}" />
        <c:if test="${!empty label}"><li><a href="?uri=${stl:encode(current)}">${label}</a></li></c:if>
      </stl:for-each-root-concept>
    </ul>
    <h2>properties:</h2>
    <ul>
      <stl:for-each-root-property var="current">
      <c:set var="label" value="${stl:label(pageContext, current, 'en', true)}" />
        <c:if test="${!empty label}"><li><a href="?uri=${stl:encode(current)}">${label}</a></li></c:if>
      </stl:for-each-root-property>
    </ul>
  </c:otherwise>
</c:choose>

</body>
</html>