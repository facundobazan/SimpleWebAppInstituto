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
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "profesores",
        urlPatterns = {"/profesores"}
)
public class ProfesorController extends HttpServlet {

    //ProfesorDAO profesorDAO = new ProfesorDAO(JPAUtils.getEntity());
    //AsignaturaDAO asignaturaDAO = new AsignaturaDAO(JPAUtils.getEntity());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if (id != null) {

            try (EntityManager em = JPAUtils.getEntity()) {

                ProfesorDAO profesorDAO = new ProfesorDAO(em);
                Profesor profesor = profesorDAO.getById(Integer.parseInt(id));
                //profesor.getAsignaturas().size()

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
                List<Profesor> profesores = profesorDAO.findByName(name);
                HttpSession session = req.getSession();
                session.setAttribute("profesores", profesores);
                resp.sendRedirect("profesores/lista.jsp");
            } catch (Exception e) {

                resp.sendError(500, e.getMessage());
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //EntityManager em = JPAUtils.getEntity();
        //em.close();
        //AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);

        try (EntityManager em = JPAUtils.getEntity()) {

            ProfesorDAO profesorDAO = new ProfesorDAO(em);
            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
            em.getTransaction().begin();

            //String legajo = req.getParameter("legajo");
            String apellidos = req.getParameter("apellidos");
            String nombre = req.getParameter("nombres");
            String telefono = req.getParameter("telefono");
            int id_asignatura = Integer.parseInt(req.getParameter("asignatura"));

            Asignatura asignatura = asignaturaDAO.getById(id_asignatura);

            if (asignatura != null ) {

                profesorDAO.create(new Profesor(apellidos, nombre, telefono, asignatura));
            } else {

                resp.sendError(400, "La asignatura seleccionada no existe.");
            }

            em.getTransaction().commit();

            resp.sendRedirect("/profesores");
        } catch (Exception e) {

            resp.sendError(500, e.getMessage());
        }


        //String asignatura = req.getParameter("asignatura");

        /*try {
            
            EntityManager em = JPAUtils.getEntity();
            em.getTransaction().begin();
            *//*profesor.setLegajo(Integer.parseInt(legajo));
            profesor.setApellido(apellidos);
            profesor.setNombre(nombre);
            profesor.setTelefono(telefono);*//*
            //profesor.addAsignatura(asignaturaDAO.getById(Integer.parseInt(asignatura)));

            profesorDAO.create(new Profesor(Integer.parseInt(legajo), apellidos, nombre, telefono));
            JPAUtils.getEntity().getTransaction().commit();

            resp.sendRedirect("profesores");
        } catch (Exception e){

            resp.sendError(500, e.getMessage());
        }
        finally {

        }*/



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

        try (EntityManager em = JPAUtils.getEntity()) {

            ProfesorDAO profesorDAO = new ProfesorDAO(em);
            em.getTransaction().begin();

            try{

                int id = Integer.parseInt(req.getParameter("id"));

                if(id<1) {

                    resp.sendError(400, "Objeto no encontrado.");
                    return;
                }
            } catch (NumberFormatException e){

                resp.sendError(400, "Parametro incorrecto.");
            }

            profesorDAO.delete(Integer.parseInt(req.getParameter("id")));
            em.getTransaction().commit();

            resp.sendRedirect("/");
        } catch (Exception e) {

            resp.sendError(400, e.getMessage());
        }
    }
}
