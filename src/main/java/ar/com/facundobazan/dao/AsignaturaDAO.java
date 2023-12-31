package ar.com.facundobazan.dao;

import ar.com.facundobazan.models.Asignatura;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class AsignaturaDAO implements Crud<Asignatura> {

    private final EntityManager MANAGER;

    public AsignaturaDAO(EntityManager entityManager) {

        this.MANAGER = entityManager;
    }

    @Override
    public void create(Asignatura asignatura) {

        this.MANAGER.persist(asignatura);
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

    public List<Asignatura> getAllUnassigned() {

        String query = "SELECT A FROM Asignatura A WHERE profesor = null";
        return this.MANAGER.createQuery(query, Asignatura.class).getResultList();
    }

    @Override
    public void update(Asignatura asignatura) {

        this.MANAGER.merge(asignatura);
    }

    @Override
    public void delete(int id) {

        Asignatura asignatura = this.MANAGER.find(Asignatura.class, id);
        this.MANAGER.remove(asignatura);
    }

    public List<Asignatura> findByName(String name) {

        String param = "%".concat(name == null ? "" : name).concat("%");
        CriteriaBuilder builder = this.MANAGER.getCriteriaBuilder();
        CriteriaQuery<Asignatura> query = builder.createQuery(Asignatura.class);
        Root<Asignatura> root = query.from(Asignatura.class);

        query.select(root).where(builder.like(root.get("asignatura"), param));

        return this.MANAGER.createQuery(query).getResultList();
    }
}
