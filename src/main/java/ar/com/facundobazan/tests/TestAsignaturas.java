package ar.com.facundobazan.tests;

import ar.com.facundobazan.dao.AsignaturaDAO;
import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.models.Asignatura;
import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;

public class TestAsignaturas {

    public static void main(String[] args) {

        Profesor profesor = new Profesor(1, "Facundo", "Bazán", "3813535730");
        Asignatura asignatura = new Asignatura(profesor, "Matemáticas");

        EntityManager em = JPAUtils.getEntity();
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
        ProfesorDAO profesorDAO = new ProfesorDAO(em);

        em.getTransaction().begin();
        profesorDAO.create(profesor);
        profesorDAO.create(new Profesor(2, "Federico", "Rosa", "+5493814451350"));
        asignaturaDAO.create(asignatura);
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(2), "Lenguas"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(1), "Física"));
        Asignatura test = asignaturaDAO.getById(1);
        System.out.println(String.format("%s - %s, %s", test.getAsignatura(), test.getProfesor().getApellido(), test.getProfesor().getNombre()));
        em.getTransaction().commit();
        em.close();
    }
}
