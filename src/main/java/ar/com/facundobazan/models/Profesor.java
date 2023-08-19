package ar.com.facundobazan.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_profesor;
    //@Column(name = "legajo", nullable = false, unique = true, length = 10)
    @Column(unique = true, nullable = false)
    private int legajo;
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombre;
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellido;
    @Column(name = "telefono", length = 14)
    private String telefono;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Asignatura> asignaturas;

    public Profesor() {

    }

    public Profesor(int legajo, String nombre, String apellido, String telefono) {

        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Profesor(int id_profesor, int legajo, String nombre, String apellido, String telefono, List<Asignatura> asignaturas) {

        this.id_profesor = id_profesor;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.asignaturas = asignaturas;
    }

    public int getId_profesor() {

        return id_profesor;
    }

    public Profesor(String nombre, String apellido, String telefono) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public void setId_profesor(int id_profesor) {

        this.id_profesor = id_profesor;
    }

    public int getLegajo() {

        return legajo;
    }

    public void setLegajo(int legajo) {

        this.legajo = legajo;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getApellido() {

        return apellido;
    }

    public void setApellido(String apellido) {

        this.apellido = apellido;
    }

    public String getTelefono() {

        return telefono;
    }

    public void setTelefono(String telefono) {

        this.telefono = telefono;
    }

    public List<Asignatura> getAsignaturas() {
        if (asignaturas == null) asignaturas = new ArrayList<>();
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {

        this.asignaturas = asignaturas;
    }

    public void addAsignaturas(Asignatura asignatura) {

        this.asignaturas.add(asignatura);
    }
}
