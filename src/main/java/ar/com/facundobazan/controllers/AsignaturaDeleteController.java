package ar.com.facundobazan.controllers;

import ar.com.facundobazan.dao.AsignaturaDAO;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AsignaturaDelete", urlPatterns = "/asignatura/del")
public class AsignaturaDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));

            try (EntityManager em = JPAUtils.getEntity()) {

                AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);

                em.getTransaction().begin();
                asignaturaDAO.delete(id);
                em.getTransaction().commit();
                resp.sendRedirect("/asignaturas");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, "Parametro incorrecto");
        }
    }
}
