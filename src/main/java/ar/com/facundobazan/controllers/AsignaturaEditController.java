package ar.com.facundobazan.controllers;

import ar.com.facundobazan.dao.AsignaturaDAO;
import ar.com.facundobazan.models.Asignatura;
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
        name = "asignaturaEdit",
        urlPatterns = {"/asignatura/edit"}
)
public class AsignaturaEditController extends HttpServlet {

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
                resp.sendRedirect("/asignaturas/edit.jsp");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, "Formato de parametro incorrecto.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));
            String asignatura = req.getParameter("asignatura");


            if (id < 1 || asignatura == null || asignatura.isBlank()) {

                resp.sendError(400, "Parametros invalidos.");
                return;
            }

            try (EntityManager em = JPAUtils.getEntity()) {

                AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
                Asignatura asignaturaAux = asignaturaDAO.getById(id);

                if (asignaturaAux == null) {

                    resp.sendError(400, "No se encontro la asignatura");
                    return;
                }

                asignaturaAux.setAsignatura(asignatura);

                em.getTransaction().begin();
                asignaturaDAO.update(asignaturaAux);
                em.getTransaction().commit();
                resp.sendRedirect("/asignaturas");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, "Formato de parametro incorrecto.");
        }
    }
}