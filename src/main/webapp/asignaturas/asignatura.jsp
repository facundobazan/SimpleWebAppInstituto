<%@page import="ar.com.facundobazan.models.Asignatura" %>

<%@ include file="../share/html-start.jsp" %>
<h1 class="text-center mt-3 mb-3">Asignatura</h1>
<div style="display: flex; flex-flow: row nowrap; justify-content: center; min-height: 100%;">
    <% Asignatura asignatura=(Asignatura)request.getSession().getAttribute("asignatura");%>

    <div class="card p-2 m-2" style="width: 18rem;">
          <h5 class="card-title mt-2 mb-3 text-center"><%= asignatura.getAsignatura() %></h5>
          <a href="/asignaturas/lista.jsp" class="btn btn-primary">Regresar</a>
        </div>
    </div>
</div>

<%@ include file="../share/html-end.jsp" %>