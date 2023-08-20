<%@page import="ar.com.facundobazan.models.Profesor" %>
<%@page import="java.util.ArrayList" %>

<%@ include file="../share/html-start.jsp" %>

<h1>Profesores</h1>

<table class="table table-hover">
<thead>

<tr>
<th scope="col">Legajo</th>
<th scope="col">Nombre</th>
<th scope="col">Telefono</th>
<th scope="col">Acciones</th>
</tr>

</thead>
<tbody>

<% ArrayList<Profesor> profesores= (ArrayList<Profesor>)
request.getSession().getAttribute("profesores");
for (Profesor profesor : profesores) { %>

<tr>
<th scope="row"><%= profesor.getLegajo() %></th>
<td><%= profesor.getApellido() %>, <%= profesor.getNombre() %></td>
<td><%= profesor.getTelefono() %></td>

<td>
<a class="bi bi-search text-primary" href="/profesores/profesor?id=<%= profesor.getId_profesor() %>"></a>
<a class="bi bi-pencil-fill text-warning" href="#"></a>
<a class="bi bi-x-circle-fill text-danger" href="#"></a>
</td>
</tr>

<%}%>
</tbody>
</table>

<%@ include file="../share/html-end.jsp" %>