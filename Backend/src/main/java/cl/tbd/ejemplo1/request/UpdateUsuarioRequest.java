package cl.tbd.ejemplo1.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import cl.tbd.ejemplo1.models.Rol;
import cl.tbd.ejemplo1.models.Voluntario;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUsuarioRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Integer idInstitucion;
    private boolean voluntario;
    private boolean coordinador;
    private Double longit;
    private Double latit;
}
