package org.ut4.gymmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ejercicio")
public class Ejercicio {
   // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String dificultad;
    @ManyToOne
    @JoinColumn(name = "grupo_muscular_id")
    private GrupoMuscular grupoMuscular;

    public Ejercicio() {

    }

    public Ejercicio(Long id, String nombre, String descripcion, String dificultad,GrupoMuscular grupoMuscular ) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.grupoMuscular = grupoMuscular;
    }
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }
    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    @Override
    public String toString() {
        return STR."Ejercicio{id=\{id}, nombre=\{nombre}, descripcion=\{descripcion}, dificultad=\{dificultad}, grupoMuscular=\{grupoMuscular}}";
    }
}