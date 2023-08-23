<%@page import="ar.com.facundobazan.models.Asignatura" %>
<%@page import="ar.com.facundobazan.dao.AsignaturaDAO" %>
<%@page import="ar.com.facundobazan.utils.JPAUtils" %>
<%@page import="jakarta.persistence.EntityManager" %>
<%@page import="java.util.List" %>

<%@ include file="../share/html-start.jsp" %>

<h1 class="text-center mt-3 mb-3">Profesor</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%;">
    
    <%
    List<Asignatura> asignaturas = (List<Asignatura>) request.getSession().getAttribute("asignaturas");
    %>

    <form class="card pt-2 p-4" action="/profesor/new" method="post">

        <div class="mb-3">
            <label for="legajo" class="form-label">Legajo</label>
            <input type="number" class="form-control" id="legajo" name="legajo" aria-describedby="legajoHelp" value="1" min="1" max="999" >
            <div id="legajoHelp" class="form-text">Ingresa el legajo.</div>
        </div>

        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos</label>
            <input type="text" class="form-control" id="apellidos" name="apellidos" aria-describedby="apellidosHelp" value="">
            <div id="apellidosHelp" class="form-text">Ingresa el/los apellido(s).</div>
        </div>

        <div class="mb-3">
            <label for="nombres" class="form-label">Nombres</label>
            <input type="text" class="form-control" id="nombres" name="nombres" aria-describedby="nombresHelp" value="">
            <div id="nombresHelp" class="form-text">Ingresa el/los nombres(s).</div>
        </div>

        <div class="mb-3">
            <label for="telefono" class="form-label">Telefono</label>
            <input type="tel" class="form-control" id="telefono" name="telefono" aria-describedby="nameHelp" value="">
            <div id="nameHelp" class="form-text">Ingresa una por nombre de asignatura.</div>
        </div>

        <div class="mb-3">
            <label for="telefono" class="form-label">Asignatura que dictara</label>
            <select class="form-select" aria-label="Asignaturas combo"  id="asignatura" name="asignatura">
                <option value=0> NINGUNA</option>
                <% for (Asignatura a : asignaturas) { %>
                <option value= <%= a.getId_asignatura() %> > <%= a.getAsignatura() %></option>
                <%}%>
            </select>
            <div id="nameHelp" class="form-text">Selecciona la asignatura que dictara.</div>
        </div>

        <div class="d-grid d-md-flex justify-content-md-center gap-2">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="/profesores" class="btn btn-danger">Cancelar</a>
        </div>

    </form>

</div>

<%@ include file="../share/html-end.jsp" %>