<%@page import="ar.com.facundobazan.models.Asignatura" %>

<%@ include file="../share/html-start.jsp" %>

<h1>Asignatura:</h1>
<% Asignatura asignatura=(Asignatura)request.getSession().getAttribute("asignatura");%>
<div>
<div>ID: <%= asignatura.getId_categoria() %></div>
<div>Asignatura: <%= asignatura.getAsignatura() %></div>
</div>

<%@ include file="../share/html-end.jsp" %>