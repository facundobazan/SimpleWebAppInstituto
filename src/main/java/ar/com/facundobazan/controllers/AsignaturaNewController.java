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
        name = "AsignaturaNew",
        urlPatterns = {"/asignatura/new"}
)
public class AsignaturaNewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (EntityManager em = JPAUtils.getEntity()) {

            ProfesorDAO profesorDAO = new ProfesorDAO(em);
            List<Profesor> profesores = profesorDAO.getAll();

            if (profesores.isEmpty()) {

                resp.sendError(400, "No se encontraron profesores para mostrar");
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute("profesores", profesores);
            resp.sendRedirect("../asignaturas/alta.jsp");
        } catch (NumberFormatException e) {

            resp.sendError(400, "Parametro de busqueda erroneo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String asignatura = req.getParameter("asignatura");
        int id_profesor = Integer.parseInt(req.getParameter("profesor"));

        if (asignatura == null) {

            resp.sendError(500, "Los datos ingresados son inválidos");
            return;
        }

        try (EntityManager em = JPAUtils.getEntity()) {

            Asignatura asignaturaAux = new Asignatura(asignatura.toUpperCase());

            if (id_profesor != 0){

                ProfesorDAO profesorDAO = new ProfesorDAO(em);
                Profesor profesorAux = profesorDAO.getById(id_profesor);

                if (profesorAux != null) {

                    asignaturaAux.setProfesor(profesorAux);
                }
            }

            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
            em.getTransaction().begin();
            asignaturaDAO.create(asignaturaAux);
            em.getTransaction().commit();
            resp.sendRedirect("/asignaturas");
        } catch (Exception e) {

            System.out.println(e.getMessage());
            resp.sendError(500, e.getMessage());
        }
    }
}
