<%@page import="ar.com.facundobazan.models.Asignatura" %>
<%@page import="ar.com.facundobazan.models.Profesor" %>

<%@page import="java.util.List" %>

<%@ include file="../share/html-start.jsp" %>

<h1 class="text-center mt-3 mb-3">Modificar asignatura</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%; ">

<%
Asignatura asignatura = (Asignatura) request.getSession().getAttribute("asignatura");
List<Profesor> profesores = (List<Profesor>) request.getSession().getAttribute("profesores");
%>

    <form class="card pt-2 p-4" action="/asignatura/edit" method="post">
    <input type="hidden" name="id" value="<%= asignatura.getId_asignatura() %>" />

        <div class="mb-3">
            <label for="asignatura" class="form-label">Asignatura</label>
            <input type="text" class="form-control" id="asignatura" name="asignatura" aria-describedby="asignaturaHelp" value="<%= asignatura.getAsignatura() %>">
            <div id="asignaturaHelp" class="form-text">Ingresa la asignatura.</div>
        </div>
        <div class="mb-3">
            <label for="profesor" class="form-label">Profesor</label>
            <select class="form-select" aria-label="Profesor combo"  id="profesor" name="profesor">
                <option value=0>SIN ASIGNAR</option>
                <%
                for (Profesor profesor : profesores) {
                    if(profesor == asignatura.getProfesor()) {
                        out.print("<option value='" + profesor.getId_profesor() + "' selected>" + profesor.getApellido() + ", " + profesor.getNombre() + "</option>");
                    }else{
                        out.print("<option value='" + profesor.getId_profesor() + "'>" + profesor.getApellido() + ", " + profesor.getNombre() + "</option>");
                    }
                }
                %>
            </select>
            <div id="profesorHelp" class="form-text">Selecciona el profesor que la dictara.</div>
        </div>

        <div class="d-grid d-md-flex justify-content-md-center gap-2">
            <button type="submit" class="btn btn-warning">Modificar</button>
            <a href="/asignaturas" class="btn btn-primary">Cancelar</a>
        </div>

    </form>

</div>

<%@ include file="../share/html-end.jsp" %>