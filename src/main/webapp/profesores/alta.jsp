<%@page import="ar.com.facundobazan.models.Asignatura" %>
<%@page import="ar.com.facundobazan.dao.AsignaturaDAO" %>
<%@page import="ar.com.facundobazan.utils.JPAUtils" %>
<%@page import="java.util.List" %>

<%@ include file="../share/html-start.jsp" %>

<h1>Profesor</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%; padding: 50px;">
    
    <%
    List<Asignatura> asignaturas = (List<Asignatura>) request.getSession().getAttribute("asignaturas");

    if(asignaturas == null){
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO(JPAUtils.getEntity());
        asignaturas = asignaturaDAO.getAll();
    }
    %>

    <form class="card pt-2 p-4" action="/profesores" method="post">

        <div class="mb-3">
            <label for="legajo" class="form-label">Legajo</label>
            <input type="number" class="form-control" id="legajo" name="legajo" aria-describedby="legajoHelp" value="">
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

        <select class="form-select mb-4" aria-label="Asignaturas combo"  id="asignatura" name="asignatura">
            <% for (Asignatura a : asignaturas) { %>
            <option value= <%= a.getId_asignatura() %> > <%= a.getAsignatura() %></option>
            <%}%>
        </select>

        <div class="d-grid d-md-flex justify-content-md-center gap-2">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="/" class="btn btn-danger">Cancelar</a>
        </div>

    </form>

</div>

<%@ include file="../share/html-end.jsp" %>