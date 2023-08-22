package ar.com.facundobazan.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_profesor;
    @Column(unique = true, nullable = false)
    private int legajo;
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombre;
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellido;
    @Column(name = "telefono", length = 14)
    private String telefono;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Asignatura> asignaturas;

    public Profesor() {

    }

    public Profesor(int legajo, String nombre, String apellido, String telefono) {

        setLegajo(legajo);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    public int getId_profesor() {

        return id_profesor;
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

        if (this.asignaturas == null) this.asignaturas = new ArrayList<>();
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {

        this.asignaturas = asignaturas;
    }

    public void addAsignatura(Asignatura asignatura) {

        if (this.asignaturas == null) this.asignaturas = new ArrayList<>();
        this.asignaturas.add(asignatura);
    }
}
