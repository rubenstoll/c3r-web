
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Context Info</title>

<!--
 - ContextInfo.jsp
 - Copyright (c) 2002 by Dr. Herong Yang. All rights reserved.
-->
<html><body>
hey!
<p>
<b>JSP Page Context Information</b><br/>
</p>
<p>
<b>Pre-defined objects:</b><br/>
<% 
   out.println("out: "+out.getClass().getName()+"<br/>");
   out.println("this: "+this.getClass().getName()+"<br/>");
   out.println("request: "+request.getClass().getName()+"<br/>");
   out.println("response: "+response.getClass().getName()+"<br/>");
   out.println("session: "+session.getClass().getName()+"<br/>");
   out.println("application: "+application.getClass().getName()
      +"<br/>");
   out.println("config: "+config.getClass().getName()+"<br/>");
   out.println("pageContext: "+pageContext.getClass().getName()
      +"<br/>");
%>
</p>
<p>
<b>Information about session:</b><br/>
<i>= pageContext.getSession();</i><br/>
<% 
   out.println("Class Name: "+session.getClass().getName()+"<br/>");
   out.println("Session ID: "+session.getId()+"<br/>");
   java.util.Date d = new java.util.Date();
   d.setTime(session.getCreationTime());
   out.println("Create Time: "+d.toString()+"<br/>");
   d.setTime(session.getLastAccessedTime());
   out.println("Last Access Time: "+d.toString()+"<br/>");
   out.println("Is Session New: "+session.isNew()+"<br/>");
%>
</p>
<p>
<b>Information about sessionContext:</b><br/>
<i>= session.getSessionContext();</i><br/>
<% 
   javax.servlet.http.HttpSessionContext c 
      = session.getSessionContext();
   out.println("Class name: "+c.getClass().getName()+"<br/>");
%>
</p>
<p>
<b>Information about application:</b><br/>
<i>= pageContext.getServletContext();</i><br/>
<% 
   out.println("Class Name: "+application.getClass().getName()+"<br/>");
   out.println("Major Version: "+application.getMajorVersion()+"<br/>");
   out.println("Minor Version: "+application.getMinorVersion()+"<br/>");
   out.println("Server Info: "+application.getServerInfo()+"<br/>");
   out.println("Servlet Context Name: "
      +application.getServletContextName()+"<br/>");
   java.util.Enumeration e = application.getServletNames();
   while (e.hasMoreElements()) {
      String n = (String) e.nextElement();
      out.println("Servlet Name: "+n+"<br/>");
   }
   e = application.getInitParameterNames();
   while (e.hasMoreElements()) {
      String n = (String) e.nextElement();
      out.println("Init Parameter Name: "+n+"<br/>");
   }
%>
</p>
</body></html>
