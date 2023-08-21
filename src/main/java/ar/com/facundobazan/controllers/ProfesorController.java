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

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        name = "profesores",
        urlPatterns = {"/profesores"}
)
public class ProfesorController extends HttpServlet {

    ProfesorDAO profesorDAO = new ProfesorDAO(JPAUtils.getEntity());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if (id != null) {

            try {

                Profesor profesor = profesorDAO.getById(Integer.parseInt(id));

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

        } else {

            List<Profesor> profesores = profesorDAO.findByName(name);
            HttpSession session = req.getSession();
            session.setAttribute("profesores", profesores);
            resp.sendRedirect("profesores/lista.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String legajo = req.getParameter("legajo");
        String apellidos = req.getParameter("apellidos");
        String nombre = req.getParameter("nombres");
        String telefono = req.getParameter("telefono");
        String asignatura = req.getParameter("asignatura");

        try {

            Profesor profesor = new Profesor();

            profesor.setLegajo(Integer.parseInt(legajo));
            profesor.setApellido(apellidos);
            profesor.setNombre(nombre);
            profesor.setTelefono(telefono);

            JPAUtils.getEntity().merge(profesor);

            resp.sendRedirect("profesores");
        } catch (Exception e){

            resp.sendError(500, e.getMessage());
        }
        finally {

        }



        /*String name = req.getParameter("name");
        if (!name.isBlank() || !name.isEmpty()) {

            List<Profesor> profesores = profesorDAO.findByName(name);
            HttpSession session = req.getSession();
            session.setAttribute("profesores", profesores);
            resp.sendRedirect("profesores/lista.jsp");
        }*/
        /*
        try {

            Profesor profesor = (Profesor) req.getSession().getAttribute("profesor");
            profesorDAO.create(profesor);
        } catch (Exception e) {

            resp.sendError(500, e.getMessage());
        }
        */
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
