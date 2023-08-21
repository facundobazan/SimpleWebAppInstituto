package ar.com.facundobazan.models;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor_asignaturas")
public class ProfesorAsignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profesor profesor;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Asignatura asignatura;

    public ProfesorAsignatura() {

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Profesor getProfesor() {

        return profesor;
    }

    public void setProfesor(Profesor profesor) {

        this.profesor = profesor;
    }

    public Asignatura getAsignatura() {

        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {

        this.asignatura = asignatura;
    }
}
