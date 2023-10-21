package cl.tbd.ejemplo1.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEmeHabilidadRequest {
    @NotNull
    Integer idHabilidad;
}
