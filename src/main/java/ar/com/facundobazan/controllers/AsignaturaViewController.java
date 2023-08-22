package ar.com.facundobazan.controllers;

import ar.com.facundobazan.dao.AsignaturaDAO;
import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.models.Asignatura;
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
        name = "asignaturaView",
        urlPatterns = {"/asignatura/view"}
)
public class AsignaturaViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));

            if (id < 1) {

                resp.sendError(400, "Parametro invalido.");
                return;
            }

            try (EntityManager em = JPAUtils.getEntity()) {

                AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
                Asignatura asignatura = asignaturaDAO.getById(id);
                HttpSession session = req.getSession();
                session.setAttribute("asignatura", asignatura);
                resp.sendRedirect("/asignaturas/asignatura.jsp");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, "Formato de parametro incorrecto.");
        }
        /*int id = Integer.parseInt(req.getParameter("id"));
        Asignatura asignatura = asignaturaDAO.getById(id);
        HttpSession session = req.getSession();
        resp.sendRedirect("/asignaturas/asignatura.jsp");
        session.setAttribute("asignatura", asignatura);*/
    }
/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        *//*try {

            Asignatura asignatura = (Asignatura) req.getSession().getAttribute("asignatura");
            asignaturaDAO.create(asignatura);
        } catch (Exception e) {

            resp.sendError(500, e.getMessage());
        }*//*
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        *//*try {

            int id = Integer.parseInt(req.getParameter("id"));
            asignaturaDAO.delete(id);

            if (id < 1) {

                resp.sendError(400, "Objeto no encontrado");
                return;
            }
            resp.sendRedirect("/");
        } catch (NumberFormatException e) {

            resp.sendError(400, "Formato de parametro incorrecto");
        }*//*
    }*/
}
