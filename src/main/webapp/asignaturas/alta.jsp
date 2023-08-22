<%@page import="ar.com.facundobazan.utils.JPAUtils" %>
<%@page import="jakarta.persistence.EntityManager" %>
<%@page import="java.util.List" %>

<%@ include file="../share/html-start.jsp" %>

<h1 class="text-center mt-3 mb-3">Nueva asignatura</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%; ">

    <form class="card pt-2 p-4" action="/asignaturas" method="post">

        <div class="mb-3">
            <label for="asignatura" class="form-label">Asignatura</label>
            <input type="text" class="form-control" id="asignatura" name="asignatura" aria-describedby="asignaturaHelp" value="">
            <div id="asignaturaHelp" class="form-text">Ingresa la asignatura.</div>
        </div>

        <div class="d-grid d-md-flex justify-content-md-center gap-2">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="/" class="btn btn-danger">Cancelar</a>
        </div>

    </form>

</div>

<%@ include file="../share/html-end.jsp" %>