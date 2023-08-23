<%@page import="ar.com.facundobazan.models.Profesor" %>
<%@page import="java.util.List" %>

<%@ include file="../share/html-start.jsp" %>

<h1 class="text-center mt-3 mb-3">Nueva asignatura</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%; ">

<%
List<Profesor> profesores = (List<Profesor>) request.getSession().getAttribute("profesores");
%>

    <form class="card pt-2 p-4" action="/asignatura/new" method="post">

        <div class="mb-2">
            <label for="asignatura" class="form-label">Asignatura</label>
            <input type="text" class="form-control" id="asignatura" name="asignatura" aria-describedby="asignaturaHelp" value="">
            <div id="asignaturaHelp" class="form-text">Ingresa la asignatura.</div>
        </div>

        <div class="mb-2">
            <label for="profesor" class="form-label">Profesor</label>
            <select class="form-select mb-3" aria-label="Profesores combo"  id="profesor" name="profesor">
                <% for (Profesor profesor : profesores) { %>
                <option value= <%= profesor.getId_profesor() %> > <%= profesor.getApellido() %>, <%= profesor.getNombre() %></option>
                <%}%>
            </select>
            <div id="asignaturaHelp" class="form-text">Selecciona el profesor que la dictara.</div>
        </div>

        <div class="d-grid d-md-flex justify-content-md-center gap-2">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="/asignaturas" class="btn btn-danger">Cancelar</a>
        </div>

    </form>

</div>

<%@ include file="../share/html-end.jsp" %>