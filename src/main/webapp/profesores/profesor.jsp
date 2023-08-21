<%@page import="ar.com.facundobazan.models.Profesor" %>
<%@page import="ar.com.facundobazan.models.Asignatura" %>

<%@ include file="../share/html-start.jsp" %>
<h1>Profesores</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%; padding: 50px;">
    <% Profesor profesor=(Profesor)request.getSession().getAttribute("profesor");%>

    <div class="card p-2" style="width: 18rem;">
        <img src="https://cdn.pixabay.com/photo/2014/04/02/10/25/man-303792_960_720.png" class="card-img-top" alt="Avatar">
        <div class="card-body">
          <h5 class="card-title text-center"><%= profesor.getApellido() %>, <%= profesor.getNombre() %></h5>
          <p class="card-text">ID: <%= profesor.getId_profesor() %></p>
          <p class="card-text">Legajo: <%= profesor.getLegajo() %></p>
          <p class="card-text">Telefono: <%= profesor.getTelefono() %></p>
          <p class="card-text">Asignatura: <%= profesor.getAsignaturas().get(0).getAsignatura() %></p>

          <a href="/profesores" class="btn btn-primary">Regresar</a>
        </div>
    </div>
</div>

<%@ include file="../share/html-end.jsp" %>