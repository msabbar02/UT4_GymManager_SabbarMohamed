package org.ut4.gymmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "ejercicio")
public class Ejercicio {
   // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede ser vacío.")
    private String nombre;
    @Size(max = 250,message = "No puede superar los 250 caracteres.")
    private String descripcion;
    @NotBlank(message = "Las dificultades validas 'PRINCIPIANTE','INTERMEDIO','AVANZADO'")
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    @Override
    public String toString() {
        return STR."Ejercicio{id=\{id}, nombre=\{nombre}, descripcion=\{descripcion}, dificultad=\{dificultad}, grupoMuscular=\{grupoMuscular}}";
    }
}