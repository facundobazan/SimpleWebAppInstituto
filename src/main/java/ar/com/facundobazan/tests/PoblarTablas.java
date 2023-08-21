package ar.com.facundobazan.tests;

import ar.com.facundobazan.dao.AsignaturaDAO;
import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.models.Asignatura;
import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;

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

        /*profesor1.addAsignatura(new Asignatura(profesor1, "Educación Física"));
        profesor2.addAsignatura(new Asignatura(profesor2, "Inglés"));
        profesor3.addAsignatura(new Asignatura(profesor3, "Matemáticas"));
        profesor4.addAsignatura(new Asignatura(profesor4, "Ciencias Sociales"));
        profesor5.addAsignatura(new Asignatura(profesor5, "Ciencias Naturales"));
        profesor6.addAsignatura(new Asignatura(profesor6, "Francés"));
        profesor7.addAsignatura(new Asignatura(profesor7, "Biología"));
        profesor8.addAsignatura(new Asignatura(profesor8, "Geografía"));
        profesor9.addAsignatura(new Asignatura(profesor9, "Literatura"));
        profesor10.addAsignatura(new Asignatura(profesor10, "Pisicología"));
*/
        EntityManager em = JPAUtils.getEntity();
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO(em);
        ProfesorDAO profesorDAO = new ProfesorDAO(em);

        em.getTransaction().begin();

        Asignatura asignatura1 = new Asignatura(profesor1, "Educación Física");
        Asignatura asignatura2 = new Asignatura(profesor2, "Inglés");
        Asignatura asignatura3 = new Asignatura(profesor3, "Matemáticas");
        Asignatura asignatura4 = new Asignatura(profesor4, "Ciencias Sociales");
        Asignatura asignatura5 = new Asignatura(profesor5, "Ciencias Naturales");
        Asignatura asignatura6 = new Asignatura(profesor6, "Francés");
        Asignatura asignatura7 = new Asignatura(profesor7, "Biología");
        Asignatura asignatura8 = new Asignatura(profesor8, "Geografía");
        Asignatura asignatura9 = new Asignatura(profesor9, "Literatura");
        Asignatura asignatura10 = new Asignatura(profesor10, "Pisicología");

        asignaturaDAO.create(asignatura1);
        asignaturaDAO.create(asignatura2);
        asignaturaDAO.create(asignatura3);
        asignaturaDAO.create(asignatura4);
        asignaturaDAO.create(asignatura5);
        asignaturaDAO.create(asignatura6);
        asignaturaDAO.create(asignatura7);
        asignaturaDAO.create(asignatura8);
        asignaturaDAO.create(asignatura9);
        asignaturaDAO.create(asignatura10);

        em.flush();

        profesor1.addAsignatura(asignatura1);
        profesor1.addAsignatura(asignatura3);
        profesor2.addAsignatura(asignatura2);
        profesor2.addAsignatura(asignatura5);
        profesor3.addAsignatura(asignatura3);
        profesor3.addAsignatura(asignatura4);
        profesor4.addAsignatura(asignatura4);
        profesor4.addAsignatura(asignatura1);
        profesor5.addAsignatura(asignatura5);
        profesor5.addAsignatura(asignatura8);
        profesor6.addAsignatura(asignatura6);
        profesor6.addAsignatura(asignatura9);
        profesor7.addAsignatura(asignatura7);
        profesor7.addAsignatura(asignatura10);
        profesor8.addAsignatura(asignatura8);
        profesor8.addAsignatura(asignatura5);
        profesor9.addAsignatura(asignatura9);
        profesor9.addAsignatura(asignatura2);
        profesor10.addAsignatura(asignatura10);
        profesor10.addAsignatura(asignatura7);

        profesorDAO.update(profesor1);
        profesorDAO.update(profesor2);
        profesorDAO.update(profesor3);
        profesorDAO.update(profesor4);
        profesorDAO.update(profesor5);
        profesorDAO.update(profesor6);
        profesorDAO.update(profesor7);
        profesorDAO.update(profesor8);
        profesorDAO.update(profesor9);
        profesorDAO.update(profesor10);

        em.getTransaction().commit();
        em.close();
    }
}
