package ar.com.facundobazan.dao;

import ar.com.facundobazan.models.Asignatura;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AsignaturaDAO implements Crud<Asignatura> {

    private final EntityManager MANAGER;

    public AsignaturaDAO(EntityManager entityManager) {

        this.MANAGER = entityManager;
    }

    @Override
    public int create(Asignatura asignatura) {

        this.MANAGER.persist(asignatura);
        return 0;
    }

    @Override
    public Asignatura getById(int id) {

        return this.MANAGER.find(Asignatura.class, id);
    }

    @Override
    public List<Asignatura> getAll() {

        String query = "SELECT A FROM Asignatura A";
        return this.MANAGER.createQuery(query, Asignatura.class).getResultList();
    }

    @Override
    public Boolean update(Asignatura asignatura) {

        this.MANAGER.merge(asignatura);
        return null;
    }

    @Override
    public Boolean delete(int id) {

        Asignatura asignatura = this.MANAGER.find(Asignatura.class, id);
        this.MANAGER.remove(asignatura);
        return null;
    }
}
