<%@ include file="../share/html-start.jsp" %>

<div class="m-5" style="display: flex; flex-direction: row; width: 100%; min-height: 100%; justify-content: center;">

    <form class="card pt-2 p-4" action="/asignaturas" method="get">

        <div class="mb-3">
          <label for="name" class="form-label">Buscar en asignaturas</label>
          <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp" value="">
          <div id="nameHelp" class="form-text">Ingresa una por nombre de asignatura.</div>
        </div>

        <div class="d-grid d-md-flex justify-content-md-center">
            <button type="submit" class="btn btn-primary">Buscar</button>
        </div>

    </form>
</div>
<%@ include file="../share/html-end.jsp" %>