package org.ut4.gymmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class RutinaEjercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relación con Rutina
    @ManyToOne
    @JoinColumn(name = "rutina_id")
    @JsonIgnore
    private Rutina rutina;

    // relación con Ejercicio
    @ManyToOne
    @JoinColumn(name = "ejercicio_id")
    private Ejercicio ejercicio;

    private Integer series;        // Ej.: 3, 4...
    private Integer repeticiones;  // Ej.: 8, 10...
    private Integer orden;         // Posición dentro de la rutina

    // constructores, getters, setters, toString()
    public RutinaEjercicio() {

    }
    public RutinaEjercicio(Rutina rutina, Ejercicio ejercicio, Integer series, Integer repeticiones, Integer orden) {
        this.rutina = rutina;
        this.ejercicio = ejercicio;
        this.series = series;
        this.repeticiones = repeticiones;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    @Override
    public String toString() {
        return STR."RutinaEjercicio{id=\{id}, ejercicio=\{ejercicio}, series=\{series}, repeticiones=\{repeticiones}, orden=\{orden}}";
    }
}
