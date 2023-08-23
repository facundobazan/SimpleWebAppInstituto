<%@page import="ar.com.facundobazan.models.Profesor" %>

<%@ include file="../share/html-start.jsp" %>

<h1 class="text-center mt-3 mb-3">Modificar profesor</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%; ">

<%

Profesor profesor = (Profesor) request.getSession().getAttribute("profesor");

%>

    <form class="card pt-2 p-4" action="/profesor/edit" method="post">
    <input type="hidden" name="id" value="<%= profesor.getId_profesor() %>" />

                <div class="mb-3 mt-3">
                    <label for="legajo" class="form-label">Legajo</label>
                    <input type="number" class="form-control" id="legajo" name="legajo" aria-describedby="legajoHelp" value="<%= profesor.getLegajo() %>" min="1" max="9999" >
                    <div id="legajoHelp" class="form-text">Ingresa el legajo.</div>
                </div>

                <div class="mb-3">
                    <label for="apellidos" class="form-label">Apellidos</label>
                    <input type="text" class="form-control" id="apellidos" name="apellidos" aria-describedby="apellidosHelp" value="<%= profesor.getApellido() %>">
                    <div id="apellidosHelp" class="form-text">Ingresa el/los apellido(s).</div>
                </div>

                <div class="mb-3">
                    <label for="nombres" class="form-label">Nombres</label>
                    <input type="text" class="form-control" id="nombres" name="nombres" aria-describedby="nombresHelp" value="<%= profesor.getNombre() %>">
                    <div id="nombresHelp" class="form-text">Ingresa el/los nombres(s).</div>
                </div>

                <div class="mb-3">
                    <label for="telefono" class="form-label">Telefono</label>
                    <input type="tel" class="form-control" id="telefono" name="telefono" aria-describedby="telefonoHelp" value="<%= profesor.getTelefono() %>">
                    <div id="telefonoHelp" class="form-text">Ingresa una por nombre de asignatura.</div>
                </div>

                <div class="d-grid d-md-flex justify-content-md-center gap-2">
                    <button type="submit" class="btn btn-success">Guardar</button>
                    <a href="/profesores" class="btn btn-danger">Cancelar</a>
                </div>

    </form>

</div>

<%@ include file="../share/html-end.jsp" %>