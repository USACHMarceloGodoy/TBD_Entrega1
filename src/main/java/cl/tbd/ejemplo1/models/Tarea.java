package cl.tbd.ejemplo1.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tarea {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer voluntariosRequeridos;
    private Integer voluntariosInscritos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaFin;
    private Estado estado;
    private List<Habilidad> habilidades = new ArrayList<>();
    private Emergencia emergencia;
    private String direccion;

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }
}