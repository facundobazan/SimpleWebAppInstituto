package ar.com.facundobazan.tests;

import ar.com.facundobazan.dao.AsignaturaDAO;
import ar.com.facundobazan.models.Asignatura;
import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class PoblarTablas {

    static List<Profesor> profesores = new ArrayList<>();
    static List<Asignatura> asignaturas = new ArrayList<>();


    public static void main(String[] args) {

        poblarListaProfesores();

        poblarListaAsignaturas();

        persistirAsignaturas();
    }

    private static void poblarListaAsignaturas() {

        poblarAsignaturas(profesores.get(0),"MATEMÁTICA");
        poblarAsignaturas(profesores.get(0),"LENGUA Y LITERATURA");
        poblarAsignaturas(profesores.get(1),"FORMACIÓN ÉTICA Y CIUDADANA");
        poblarAsignaturas(profesores.get(1),"EDUCACIÓN FÍSICA");
        poblarAsignaturas(profesores.get(2),"LENGUA EXTRANJERA: INGLES");
        poblarAsignaturas(profesores.get(2),"EDUCACIÓN TECNOLÓGICA");
        poblarAsignaturas(profesores.get(3),"HISTORIA");
        poblarAsignaturas(profesores.get(3),"GEOGRAFÍA");
        poblarAsignaturas(profesores.get(4),"BIOLOGÍA");
        poblarAsignaturas(profesores.get(4),"FÍSICA Y QUÍMICA");
        poblarAsignaturas(profesores.get(5),"MUSICA");
        poblarAsignaturas(profesores.get(5),"ARTES VISUALES");
        poblarAsignaturas(profesores.get(6),"TEATRO");
        poblarAsignaturas(profesores.get(6),"LENGUAJE CORPORAL Y DANZA");
        poblarAsignaturas(profesores.get(7),"DIBUJO Y COMPOSICIÓN VISUAL");
        poblarAsignaturas(profesores.get(7),"HISTORIA SOCIOCULTURAL DEL ARTE I");
        poblarAsignaturas(profesores.get(8),"LENGUA EXTRANJERA: FRANCES");
        poblarAsignaturas(profesores.get(8),"LENGUA EXTRANJERA: ALEMÁN");
        poblarAsignaturas(profesores.get(9),"LENGUA EXTRANJERA: PORTUGUÉS");
        poblarAsignaturas(profesores.get(9),"INFORMÁTICA");
    }

    private static void poblarListaProfesores() {

        poblarProfesores(1, "MARCOS GABRIEL", "SALVATIERRA", "+5493815098217");
        poblarProfesores(2, "FEDERICO", "RODRIGUEZ", "+5493816473641");
        poblarProfesores(3, "MATIAS", "GONZALEZ", "+5493811217422");
        poblarProfesores(4, "MARIA LAURA", "FERNANDEZ", "+5493813181418");
        poblarProfesores(5, "FABIANA", "MARTINEZ", "+5493811283280");
        poblarProfesores(6, "JUAN", "FLORES", "+5493813226437");
        poblarProfesores(7, "CRISTINA", "SERRANO", "+5493815812125");
        poblarProfesores(8, "MORENA SOLEDAD", "RUIZ", "+5493813210320");
        poblarProfesores(9, "ARIEL", "AGUIRRE", "+5493814343357");
        poblarProfesores(10, "MAXIMILIANO EMILIO", "FLORES", "+5493813528390");
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

            em.getTransaction().begin();

            for (Asignatura asignatura : asignaturas) {

                asignaturaDAO.create(asignatura);
            }

            em.getTransaction().commit();
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }
}
