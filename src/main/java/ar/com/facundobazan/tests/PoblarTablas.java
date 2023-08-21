package ar.com.facundobazan.tests;

import ar.com.facundobazan.dao.AsignaturaDAO;
import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.models.Asignatura;
import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class PoblarTablas {

    public static void main(String[] args) {

        Profesor profesor1 = new Profesor(1, "Marcos Gabriel", "Salvatierra", "+5493815098217");
        Profesor profesor2 = new Profesor(2, "Federico", "Rodriguez", "+5493816473641");
        Profesor profesor3 = new Profesor(3, "Matias", "Gonzalez", "+5493811217422");
        Profesor profesor4 = new Profesor(4, "Maria Laura", "Fernandez", "+5493813181418");
        Profesor profesor5 = new Profesor(5, "Fabiana", "Martinez", "+5493811283280");
        Profesor profesor6 = new Profesor(6, "Juan", "Flores", "+5493813226437");
        Profesor profesor7 = new Profesor(7, "Cristina", "Serrano", "+5493815812125");
        Profesor profesor8 = new Profesor(8, "Morena Soledad", "Ruiz", "+5493813210320");
        Profesor profesor9 = new Profesor(9, "Ariel", "Aguirre", "+5493814343357");
        Profesor profesor10 = new Profesor(10, "Maximiliano Emilio", "Flores", "+5493813528390");

        profesor1.addAsignatura(new Asignatura(profesor1, "Educación Física"));
        profesor2.addAsignatura(new Asignatura(profesor2, "Inglés"));
        profesor3.addAsignatura(new Asignatura(profesor3, "Matemáticas"));
        profesor4.addAsignatura(new Asignatura(profesor4, "Ciencias Sociales"));
        profesor5.addAsignatura(new Asignatura(profesor5, "Ciencias Naturales"));
        profesor6.addAsignatura(new Asignatura(profesor6, "Francés"));
        profesor7.addAsignatura(new Asignatura(profesor7, "Biología"));
        profesor8.addAsignatura(new Asignatura(profesor8, "Geografía"));
        profesor9.addAsignatura(new Asignatura(profesor9, "Literatura"));
        profesor10.addAsignatura(new Asignatura(profesor10, "Pisicología"));

        EntityManager em = JPAUtils.getEntity();
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
        ProfesorDAO profesorDAO = new ProfesorDAO(em);

        em.getTransaction().begin();

        profesorDAO.create(profesor1);
        profesorDAO.create(profesor2);
        profesorDAO.create(profesor3);
        profesorDAO.create(profesor4);
        profesorDAO.create(profesor5);
        profesorDAO.create(profesor6);
        profesorDAO.create(profesor7);
        profesorDAO.create(profesor8);
        profesorDAO.create(profesor9);
        profesorDAO.create(profesor10);

        em.flush();

        /*asignaturaDAO.create(new Asignatura(profesorDAO.getById(2), "Inglés"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(3), "Matemáticas"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(4), "Ciencias Sociales"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(5), "Ciencias Naturales"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(6), "Francés"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(7), "Biología"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(8), "Geografía"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(9), "Literatura"));
        asignaturaDAO.create(new Asignatura(profesorDAO.getById(10), "Pisicología"));*/

        em.getTransaction().commit();
        em.close();
    }
}
