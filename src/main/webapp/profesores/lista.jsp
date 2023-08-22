<%@page import="ar.com.facundobazan.models.Profesor" %>
<%@page import="ar.com.facundobazan.dao.ProfesorDAO" %>
<%@page import="ar.com.facundobazan.utils.JPAUtils" %>
<%@page import="jakarta.persistence.EntityManager" %>
<%@page import="java.util.List" %>

<%@ include file="../share/html-start.jsp" %>

<h1 class="text-center mt-3 mb-3">Profesores</h1>

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

<%

List<Profesor> profesores = (List<Profesor>) request.getSession().getAttribute("profesores");

if(profesores == null){

    try (EntityManager em = JPAUtils.getEntity()){

    ProfesorDAO profesorDAO = new ProfesorDAO(em);
    profesores = profesorDAO.getAll();
    } catch (Exception e){

      throw new RuntimeException(e.getMessage());
    }
}
    
    for (Profesor p : profesores) {

%>

<tr>
<th scope="row"><%= p.getLegajo() %></th>
<td><%= p.getApellido() %>, <%= p.getNombre() %></td>
<td><%= p.getTelefono() %></td>

<td>

<form class="d-inline" action="/profesor/view" method="get">
<input type="hidden" name="id" value="<%= p.getId_profesor() %>" />
    <button type="submit" class="btn bi bi-search text-primary"></button>
</form>
<form class="d-inline" action="/profesor/edit" method="get">
<input type="hidden" name="id" value="<%= p.getId_profesor() %>" />
    <button type="submit" class="btn bi bi-pencil-fill text-warning"></button>
</form>
<form class="d-inline" action="/profesor/del" method="post">
<input type="hidden" name="id" value="<%= p.getId_profesor() %>" />
    <button type="submit" class="btn bi bi-x-circle-fill text-danger"></button>
</form>

</td>
</tr>

<%}%>
</tbody>
</table>

<%@ include file="../share/html-end.jsp" %>