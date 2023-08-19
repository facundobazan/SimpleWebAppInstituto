package ar.com.facundobazan.dao;

import ar.com.facundobazan.models.Profesor;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProfesorDAO implements Crud<Profesor> {

    private final EntityManager MANAGER;

    public ProfesorDAO(EntityManager entityManager) {

        this.MANAGER = entityManager;
    }

    @Override
    public int create(Profesor profesor) {

        this.MANAGER.persist(profesor);
        return 0;
    }

    @Override
    public Profesor getById(int id) {

        return this.MANAGER.find(Profesor.class, id);
    }

    @Override
    public List<Profesor> getAll() {

        String query = "SELECT P FROM Profesor P";
        return this.MANAGER.createQuery(query, Profesor.class).getResultList();
    }

    @Override
    public Boolean update(Profesor profesor) {

        this.MANAGER.merge(profesor);
        return null;
    }

    @Override
    public Boolean delete(int id) {

        Profesor profesor = this.MANAGER.find(Profesor.class, id);
        this.MANAGER.remove(profesor);
        return null;
    }
}
