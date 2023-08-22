package ar.com.facundobazan.controllers;

import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProfesorDelete", urlPatterns = "/profesor/del")
public class ProfesorDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));

            try (EntityManager em = JPAUtils.getEntity()) {

                ProfesorDAO profesorDAO = new ProfesorDAO(em);

                em.getTransaction().begin();
                profesorDAO.delete(id);
                em.getTransaction().commit();
                resp.sendRedirect("/profesores");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, "Parametro incorrecto");
        }
    }
}
