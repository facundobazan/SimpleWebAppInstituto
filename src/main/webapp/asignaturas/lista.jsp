<%@page import="ar.com.facundobazan.models.Asignatura" %>
<%@page import="ar.com.facundobazan.dao.AsignaturaDAO" %>
<%@page import="ar.com.facundobazan.utils.JPAUtils" %>
<%@page import="jakarta.persistence.EntityManager" %>

<%@page import="java.util.List" %>

<%@ include file="../share/html-start.jsp" %>

<h1 class="text-center mt-3 mb-3">Asignaturas</h1>

<table class="table table-hover">
<thead>
<tr>
<th scope="col">#</th>
<th scope="col">Asignatura</th>
<th scope="col">Acciones</th>
</tr>
</thead>
<tbody>

<%

List<Asignatura> asignaturas = (List<Asignatura>) request.getSession().getAttribute("asignaturas");

if(asignaturas == null){

    try (EntityManager em = JPAUtils.getEntity()){

    AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
    asignaturas = asignaturaDAO.getAll();
    } catch (Exception e){

      throw new RuntimeException(e.getMessage());
    }
}

for (Asignatura a : asignaturas) {
%>

<tr>
<th scope="row"><%= a.getId_asignatura() %></th>

<td><%= a.getAsignatura() %></td>

<td>
<a class="bi bi-search text-primary"href="/asignaturas/asignatura?id=<%= a.getId_asignatura() %>"></a>
<a class="bi bi-pencil-fill text-warning" href="#"></a>
<a class="bi bi-x-circle-fill text-danger" href="#"></a>
</td>
</tr>

<%}%>

</tbody>
</table>

<%@ include file="../share/html-end.jsp" %>