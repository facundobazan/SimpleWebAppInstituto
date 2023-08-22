package ar.com.facundobazan.controllers;

import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        name = "profesor",
        urlPatterns = {"/profesores/profesor"}
)
public class ProfesorViewController extends HttpServlet {

    //ProfesorDAO profesorDAO = new ProfesorDAO(JPAUtils.getEntity());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));

            if (id < 1) {

                resp.sendError(400, "Parametro invalido.");
                return;
            }

            try (EntityManager em = JPAUtils.getEntity()) {

                ProfesorDAO profesorDAO = new ProfesorDAO(em);
                Profesor profesor = profesorDAO.getById(id);
                HttpSession session = req.getSession();
                session.setAttribute("profesor", profesor);
                resp.sendRedirect("/profesores/profesor.jsp");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, "Formato de parametro incorrecto.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(EntityManager em = JPAUtils.getEntity()) {

            ProfesorDAO profesorDAO = new ProfesorDAO(em);
            Profesor profesor = (Profesor) req.getSession().getAttribute("profesor");

            if (profesor == null){

                resp.sendError(400, "No se puedo insertar el registro");
                return;
            }

            em.getTransaction().begin();
            profesorDAO.create(profesor);
            em.getTransaction().commit();
        } catch (Exception e) {

            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));

            if (id < 1) {
                resp.sendError(400, "Parametro invalido.");
                return;
            }

            try(EntityManager em = JPAUtils.getEntity()) {

                ProfesorDAO profesorDAO = new ProfesorDAO(em);
                em.getTransaction().begin();
                profesorDAO.delete(id);
                em.getTransaction().commit();

                resp.sendRedirect("/");
            } catch (Exception e) {

                resp.sendError(400, e.getMessage());
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, "Formato de parametro incorrecto.");
        }
    }
}
