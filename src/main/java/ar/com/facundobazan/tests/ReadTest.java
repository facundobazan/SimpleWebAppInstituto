package ar.com.facundobazan.tests;

import ar.com.facundobazan.dao.ProfesorDAO;
import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ReadTest {

    public static void main(String[] args) {

        //final EntityManager em = JPAUtils.getEntity();
        try(EntityManager em = JPAUtils.getEntity()) {

            final ProfesorDAO profesorDAO = new ProfesorDAO(em);
            List<Profesor> profesores = profesorDAO.getAll();

            for (Profesor profesor : profesores) System.out.println(profesor.getApellido());
        }
    }
}
