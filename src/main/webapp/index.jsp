<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="share/html-start.jsp" %>

<h1>TRABAJO FINAL</h1>
<h2>JAVA NIVEL INTERMEDIO</h2>
<h3>CONSIGNA</h3>

<ol><li>1. Cree una base de datos llamada <b>bdfinal</b> que contenga dos tablas, una llamada <b>profesores</b> y otra llamada <b>asignaturas</b>. Un profesor puede dictar varias asignaturas. Una asignatura puede ser dictada por un solo profesor.

<h5>La tabla profesores contendrá los siguientes campos:</h5>
<ul><li>id_profesor</li>
<li>legajo</li>
<li>nombres</li>
<li>apellidos</li>
<li>telefono</li></ul>

<h5>La tabla asignaturas contendrá los siguientes campos:</h5>
<ul><li>id_asignatura</li>
<li>nombre</li></ul></li>

<li>2. Cree un Proyecto Web Maven que permita listar, dar de alta y baja a los profesores a través de una página web.

<h5>El proyecto debe hacerse con las siguientes consideraciones:</h5>
<ul><li>Debe hacerse con el patrón arquitectónico MVC.</li>
<li>Para el acceso a la base de datos, se puede emplear o no el patrón de diseño DAO. Si emplea el patrón puede obtener una nota final de 100 para el trabajo, considerando además el resto de los puntos.</li></ul>
</li>
<ol>

<h5>Productos a entregar:</h5>

<ul><li>1 proyecto web maven comprimido con el nombre: pfinal_ApellidoNombre.zip</li>
<li>1 script que permita crear la base de datos, las tablas y cargue datos de prueba.</li>
<li>1 imágen con el diseño de la base de datos.</li></ul>

<p>Puede enviar capturas de la salida de su proyecto (no envíe videos). Una captura para el alta, otra para la baja y otra para el listado.</p>

<div><h5>IMPORTANTE</h5>
<p>Por favor, verifique la fecha de entrega del presente trabajo práctico. El mismo deberá entregarse en el plazo establecido para su correspondiente evaluación.</p></div>

<%@ include file="share/html-end.jsp" %>