package ar.com.facundobazan.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Entity
@Table(name = "asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_asignatura;
    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String asignatura;

    @ManyToOne()
    private Profesor profesor;

    public Asignatura() {

    }

    public Asignatura(String asignatura) {

        this.asignatura = asignatura;
    }

    public Asignatura(Profesor profesor, String asignatura) {

        this.profesor = profesor;
        this.asignatura = asignatura;
    }

    public int getId_asignatura() {

        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {

        this.id_asignatura = id_asignatura;
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
