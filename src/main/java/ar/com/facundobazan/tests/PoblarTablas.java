package ar.com.facundobazan.tests;

import ar.com.facundobazan.dao.AsignaturaDAO;
import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.models.Asignatura;
import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoblarTablas {

    static List<Profesor> profesores = new ArrayList<>();
    static List<Asignatura> asignaturas = new ArrayList<>();


    public static void main(String[] args) {

        poblarListaProfesores();

        persistirProfesores();

        poblarListaAsignaturas();

        persistirAsignaturas();

        asignarAsigaturasAProfesores();

        actualizarAsignaturasAProfesores();
    }

    private static void actualizarAsignaturasAProfesores() {

        try (EntityManager em = JPAUtils.getEntity()) {

            ProfesorDAO profesorDAO = new ProfesorDAO(em);

            em.getTransaction().begin();

            for (Profesor profesor : profesores) profesorDAO.update(profesor);

            em.getTransaction().commit();
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    private static void asignarAsigaturasAProfesores() {

        Random random = new Random();

        for (Profesor profesor: profesores) {

            profesor.addAsignatura(asignaturas.get(random.nextInt(10)));
            profesor.addAsignatura(asignaturas.get(random.nextInt(10)));
        }
    }

    private static void poblarListaAsignaturas() {

        poblarAsignaturas(profesores.get(0), "Educación Física");
        poblarAsignaturas(profesores.get(1), "Inglés");
        poblarAsignaturas(profesores.get(2), "Matemáticas");
        poblarAsignaturas(profesores.get(3), "Ciencias Sociales");
        poblarAsignaturas(profesores.get(4), "Ciencias Naturales");
        poblarAsignaturas(profesores.get(5), "Francés");
        poblarAsignaturas(profesores.get(6), "Biología");
        poblarAsignaturas(profesores.get(7), "Geografía");
        poblarAsignaturas(profesores.get(8), "Literatura");
        poblarAsignaturas(profesores.get(9), "Pisicología");
    }

    private static void poblarListaProfesores() {

        poblarProfesores(1, "Marcos Gabriel", "Salvatierra", "+5493815098217");
        poblarProfesores(2, "Federico", "Rodriguez", "+5493816473641");
        poblarProfesores(3, "Matias", "Gonzalez", "+5493811217422");
        poblarProfesores(4, "Maria Laura", "Fernandez", "+5493813181418");
        poblarProfesores(5, "Fabiana", "Martinez", "+5493811283280");
        poblarProfesores(6, "Juan", "Flores", "+5493813226437");
        poblarProfesores(7, "Cristina", "Serrano", "+5493815812125");
        poblarProfesores(8, "Morena Soledad", "Ruiz", "+5493813210320");
        poblarProfesores(9, "Ariel", "Aguirre", "+5493814343357");
        poblarProfesores(10, "Maximiliano Emilio", "Flores", "+5493813528390");
    }

    private static void poblarProfesores(int legajo, String nombre, String apellido, String telefono) {

        profesores.add(new Profesor(legajo, nombre, apellido, telefono));
    }

    private static void poblarAsignaturas(Profesor profesor, String asignatura) {

        asignaturas.add(new Asignatura(profesor, asignatura));
    }

    private static void persistirAsignaturas() {

        try (EntityManager em = JPAUtils.getEntity()) {

            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
            ProfesorDAO profesorDAO = new ProfesorDAO(em);

            em.getTransaction().begin();

            for (Asignatura asignatura : asignaturas) {

                asignatura.setProfesor(profesorDAO.getById(asignatura.getProfesor().getId_profesor()));
                asignaturaDAO.create(asignatura);
            }

            em.getTransaction().commit();
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    private static void persistirProfesores() {

        try (EntityManager em = JPAUtils.getEntity()) {

            ProfesorDAO profesorDAO = new ProfesorDAO(em);

            em.getTransaction().begin();

            for (Profesor profesor : profesores) profesorDAO.create(profesor);

            em.getTransaction().commit();
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }
}
