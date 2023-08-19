<!--<%@page import="ar.com.facundobazan.models.Profesor" %>-->
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
            <h1>Asignatura:</h1>
            <% Asignatura asignatura=(Asignatura)request.getSession().getAttribute("asignatura");%>

                <div>
                    <div>ID: <%= asignatura.getId_categoria() %></div>
                    <div>Asignatura: <%= asignatura.getAsignatura() %></div>
                </div>
        </body>

        </html>