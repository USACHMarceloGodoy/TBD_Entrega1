package cl.tbd.ejemplo1.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTareaVoluntarioRequest {
    @NotNull
    Integer idTarea;
}
