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
import java.util.List;

@WebServlet(
        name = "ProfesorNew",
        urlPatterns = "/profesor/new"
)
public class ProfesorNewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (EntityManager em = JPAUtils.getEntity()) {

            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
            List<Asignatura> asignaturas = asignaturaDAO.getAllUnassigned();
            for (Asignatura asignatura : asignaturas) System.out.println(asignatura.getAsignatura());
            HttpSession session = req.getSession();
            session.setAttribute("asignaturas", asignaturas);
            resp.sendRedirect("/profesores/alta.jsp");
        } catch (Exception e) {

            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            int asignaturaId = Integer.parseInt(req.getParameter("asignatura"));
            int legajo = Integer.parseInt(req.getParameter("legajo"));
            String apellidos = req.getParameter("apellidos");
            String nombre = req.getParameter("nombres");
            String telefono = req.getParameter("telefono");

            if (apellidos.isBlank() || apellidos.isBlank() || telefono.isBlank()) {

                resp.sendError(400, "Completa todos los campos para continuar.");
                return;
            }

            try (EntityManager em = JPAUtils.getEntity()) {

                Profesor profesor = new Profesor(legajo, apellidos, nombre, telefono);

                if (asignaturaId != 0) {

                    AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
                    Asignatura asignatura = asignaturaDAO.getById(asignaturaId);

                    if (asignatura == null) {

                        resp.sendError(400, "No se pudo obtener la asignatura seleccionada.");
                        return;
                    }

                    asignatura.setProfesor(profesor);
                    em.getTransaction().begin();
                    asignaturaDAO.update(asignatura);
                    em.getTransaction().commit();
                } else {

                    ProfesorDAO profesorDAO = new ProfesorDAO(em);
                    em.getTransaction().begin();
                    profesorDAO.create(profesor);
                    em.getTransaction().commit();
                }

                resp.sendRedirect("/profesores");
            }

        } catch (NumberFormatException e) {

            resp.sendError(500, e.getMessage());
        }
    }
}
