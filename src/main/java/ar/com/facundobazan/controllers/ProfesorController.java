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
import java.util.List;

@WebServlet(
        name = "profesores",
        urlPatterns = {"/profesores"}
)
public class ProfesorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if (id != null) {

            try (EntityManager em = JPAUtils.getEntity()) {

                ProfesorDAO profesorDAO = new ProfesorDAO(em);
                Profesor profesor = profesorDAO.getById(Integer.parseInt(id));

                if (profesor == null) {

                    resp.sendError(400, "Objeto no encontrado");
                    return;
                }

                HttpSession session = req.getSession();
                resp.sendRedirect("profesores/profesor.jsp");
                session.setAttribute("profesor", profesor);
            } catch (Exception e) {

                resp.sendError(400, "Parametro de busqueda erroneo");
            }

        } else {

            try (EntityManager em = JPAUtils.getEntity()) {

                ProfesorDAO profesorDAO = new ProfesorDAO(em);
                List<Profesor> profesores = name != null ? profesorDAO.findByName(name.toUpperCase()) : profesorDAO.getAll();
                HttpSession session = req.getSession();
                session.setAttribute("profesores", profesores);
                resp.sendRedirect("profesores/lista.jsp");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        }
    }
}
