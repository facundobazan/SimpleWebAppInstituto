package ar.com.facundobazan.controllers;

import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.models.Asignatura;
import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        name = "profesor",
        urlPatterns = {"/profesor"}
)
public class ProfesorController extends HttpServlet {

    ProfesorDAO profesorDAO = new ProfesorDAO(JPAUtils.getEntity());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));
            Profesor profesor = profesorDAO.getById(id);
            for (Asignatura a : profesor.getAsignaturas()) System.out.println(a);

            if (profesor == null) {

                resp.sendError(400, "Objeto no encontrado");
                return;
            }

            HttpSession session = req.getSession();
            resp.sendRedirect("profesores/profesor.jsp");
            session.setAttribute("profesor", profesor);
        } catch (NumberFormatException e) {

            resp.sendError(400, "Parametro de busqueda erroneo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Profesor profesor = (Profesor) req.getSession().getAttribute("profesor");
            profesorDAO.create(profesor);
        }catch (Exception e){

            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));
            profesorDAO.delete(id);

            if (id < 1) {

                resp.sendError(400, "Objeto no encontrado");
                return;
            }
            resp.sendRedirect("/");
        } catch (NumberFormatException e) {

            resp.sendError(400, "Formato de parametro incorrecto");
        }
    }
}
