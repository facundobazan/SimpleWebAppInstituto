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
import java.util.List;

@WebServlet(
        name = "asignaturas",
        urlPatterns = {"/asignaturas"}
)
public class AsignaturaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if (id != null) {

            try (EntityManager em = JPAUtils.getEntity()) {

                AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
                Asignatura asignatura = asignaturaDAO.getById(Integer.parseInt(id));
                HttpSession session = req.getSession();
                session.setAttribute("asignatura", asignatura);
                resp.sendRedirect("asignaturas/asignatura.jsp");
            } catch (NumberFormatException e) {

                resp.sendError(400, "Parametro de busqueda erroneo");
            }

        } else {

            try (EntityManager em = JPAUtils.getEntity()) {

                AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
                List<Asignatura> asignaturas = name != null ? asignaturaDAO.findByName(name) : asignaturaDAO.getAll();
                HttpSession session = req.getSession();
                session.setAttribute("asignaturas", asignaturas);
                resp.sendRedirect("asignaturas/lista.jsp");
            } catch (NumberFormatException e) {

                resp.sendError(400, "Parametro de busqueda erroneo");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String asignatura = (String) req.getParameter("asignatura");
        if (asignatura == null) {

            resp.sendError(400, "No se puedo insertar el registro");
            return;
        }

        try (EntityManager em = JPAUtils.getEntity()) {

            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);

            em.getTransaction().begin();
            asignaturaDAO.create(new Asignatura(asignatura));
            em.getTransaction().commit();
            resp.sendRedirect("/asignaturas");
        } catch (Exception e) {

            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("asignatura"));

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
