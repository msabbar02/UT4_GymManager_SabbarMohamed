package org.ut4.gymmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede ser nulo.")
    private String nombre;
    private String descripcion;
    private String nivel;
    @OneToMany(mappedBy = "rutina",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RutinaEjercicio> rutinaEjercicios = new ArrayList<>();

    public Rutina() {
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<RutinaEjercicio> getRutinaEjercicios() {
        return rutinaEjercicios;
    }
    public void setRutinaEjercicios(List<RutinaEjercicio> rutinaEjercicios) {
        this.rutinaEjercicios = rutinaEjercicios;
    }
    @Override
    public String toString() {
        return STR."Rutina{id=\{id}, nombre='\{nombre}', descripcion='\{descripcion}', rutinaEjercicios=\{rutinaEjercicios}}";
    }
}
