<%@page import="ar.com.facundobazan.models.Profesor" %>
<%@page import="ar.com.facundobazan.models.Asignatura" %>

<%@page import="java.util.List" %>

<%@ include file="../share/html-start.jsp" %>
<h1 class="text-center mt-3 mb-3">Profesores</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%;">
    <% Profesor profesor=(Profesor)request.getSession().getAttribute("profesor");%>

    <div class="card p-2" style="width: 18rem;">
        <img src="https://cdn.pixabay.com/photo/2014/04/02/10/25/man-303792_960_720.png" class="card-img-top" alt="Avatar">
        <div class="card-body">
          <h5 class="card-title text-center"><%= profesor.getApellido() %>, <%= profesor.getNombre() %></h5>
          <p class="card-text mb-1">ID: <%= profesor.getId_profesor() %></p>
          <p class="card-text mb-1">Legajo: <%= profesor.getLegajo() %></p>
          <p class="card-text mb-1">Telefono: <%= profesor.getTelefono() %></p>
          <p class="card-text mb-2 mt-2">Asignaturas:</p>
          <ul>
          <%
          List<Asignatura> asignaturas = (List<Asignatura>) profesor.getAsignaturas();

          if (asignaturas.isEmpty()) {

            out.print("<li>Sin asignar</li>");
          } else {

            for (Asignatura asignatura: asignaturas) out.print("<li>" + asignatura.getAsignatura() + "</li>");
          };
          %>
          </ul>
          <a href="/profesores" class="btn btn-primary">Regresar</a>
        </div>
    </div>
</div>

<%@ include file="../share/html-end.jsp" %>