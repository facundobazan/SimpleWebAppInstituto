package ar.com.facundobazan.dao;

import ar.com.facundobazan.models.Profesor;
import ar.com.facundobazan.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProfesorDAO implements Crud<Profesor> {

    EntityManager entityManager = JPAUtils.getEntity();
    private final EntityManager MANAGER;

    public ProfesorDAO(EntityManager entityManager) {

        this.MANAGER = entityManager;
    }

    @Override
    public int create(Profesor profesor) {

        this.entityManager.getTransaction().begin();
        this.MANAGER.persist(profesor);
        this.entityManager.getTransaction().commit();
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

    public List<Profesor> findByName(String name) {

        String param = "%".concat(name == null ? "" : name).concat("%");
        CriteriaBuilder builder = this.MANAGER.getCriteriaBuilder();
        CriteriaQuery<Profesor> query = builder.createQuery(Profesor.class);
        Root<Profesor> root = query.from(Profesor.class);

        query.select(root).where(builder.or(builder.like(root.get("nombre"), param), builder.like(root.get("apellido"), param)));

        return this.MANAGER.createQuery(query).getResultList();
    }
}
