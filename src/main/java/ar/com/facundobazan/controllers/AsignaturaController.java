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

        String name = req.getParameter("name");

        try (EntityManager em = JPAUtils.getEntity()) {

            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
            List<Asignatura> asignaturas = name != null ? asignaturaDAO.findByName(name.toUpperCase()) : asignaturaDAO.getAll();
            HttpSession session = req.getSession();
            session.setAttribute("asignaturas", asignaturas);
            resp.sendRedirect("asignaturas/lista.jsp");
        } catch (Exception e) {

            System.out.println(e.getMessage());
            resp.sendError(400, "Parametro de busqueda erroneo");
        }
    }
}
