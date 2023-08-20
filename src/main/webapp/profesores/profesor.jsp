<%@page import="ar.com.facundobazan.models.Profesor" %>
<%@page import="ar.com.facundobazan.models.Asignatura" %>

<%@ include file="../share/html-start.jsp" %>

<h1>Profesor:</h1>
<% Profesor profesor=(Profesor)request.getSession().getAttribute("profesor");%>
<div>
<div>Legajo: <%= profesor.getLegajo() %>
</div>
<div>Nombre: <%= profesor.getApellido() %>, <%= profesor.getNombre() %>
</div>
<div>Telefono: <%= profesor.getTelefono() %>
</div>

<div>Materias que dicta:</div>
<ul>
<% for (Asignatura a: profesor.getAsignaturas()){%>
<li>
<%= a.getAsignatura() %>
</li>
<%}%>
</ul>
</div>

<%@ include file="../share/html-end.jsp" %>