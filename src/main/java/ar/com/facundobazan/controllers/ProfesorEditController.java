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
        name = "profesorEdit",
        urlPatterns = {"/profesor/edit"}
)
public class ProfesorEditController extends HttpServlet {

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
                resp.sendRedirect("/profesores/edit.jsp");
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
            //int legajo = Integer.parseInt(req.getParameter("legajo"));
            String apellidos = req.getParameter("apellidos");
            String nombres = req.getParameter("nombres");
            String telefono = req.getParameter("telefono");


            if (id < 1 || /*legajo < 1  || */apellidos == null || nombres == null || telefono == null) {

                resp.sendError(400, "Parametros invalidos.");
                return;
            }

            try (EntityManager em = JPAUtils.getEntity()) {

                ProfesorDAO profesorDAO = new ProfesorDAO(em);
                Profesor profesor = profesorDAO.getById(id);

                if (profesor == null) {

                    resp.sendError(400, "No se encontro el profesor");
                    return;
                }

                profesor.setApellido(apellidos);
                profesor.setNombre(nombres);
                profesor.setTelefono(telefono);

                em.getTransaction().begin();
                profesorDAO.update(profesor);
                em.getTransaction().commit();
                resp.sendRedirect("/profesores");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, "Formato de parametro incorrecto.");
        }
    }
}
