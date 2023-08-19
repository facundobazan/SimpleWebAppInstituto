<%@page import="ar.com.facundobazan.models.Profesor" %>
<%@page import="ar.com.facundobazan.models.Asignatura" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="es">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Profesores</title>
        </head>

        <body>
            <h1>Profesor:</h1>
            <% Profesor profesor=(Profesor)request.getSession().getAttribute("profesor");%>

                <div>
                    <div>Legajo: <%= profesor.getLegajo() %>
                    </div>
                    <div>Nombre: <%= profesor.getApellido() %>, <%= profesor.getNombre() %>
                    </div>
                    <div>Telefono: <%= profesor.getTelefono() %>
                    </div>
                    <div>Materias que dicta:</div>
                    <ul>
                        <% for (Asignatura a: profesor.getAsignaturas()){%>
                            <li>
                                <%= a.getAsignatura() %>
                            </li>
                            <%}%>
                    </ul>
                </div>
                </div>
        </body>

        </html>