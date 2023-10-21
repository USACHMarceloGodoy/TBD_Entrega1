package cl.tbd.ejemplo1.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateHabilidadRequest {
    private String descripcion;
}