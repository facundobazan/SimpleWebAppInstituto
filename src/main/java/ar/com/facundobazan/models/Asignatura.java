package ar.com.facundobazan.models;

import jakarta.persistence.*;

@Entity
@Table(name = "asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_categoria;
    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String asignatura;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Profesor profesor = new Profesor();

    public Asignatura() {

    }

    public Asignatura(Profesor profesor, String asignatura) {

        this.profesor = profesor;
        this.asignatura = asignatura;
    }

    public int getId_categoria() {

        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {

        this.id_categoria = id_categoria;
    }

    public String getAsignatura() {

        return asignatura;
    }

    public void setAsignatura(String asignatura) {

        this.asignatura = asignatura;
    }

    public Profesor getProfesor() {

        return profesor;
    }

    public void setProfesor(Profesor profesor) {

        this.profesor = profesor;
    }
}
