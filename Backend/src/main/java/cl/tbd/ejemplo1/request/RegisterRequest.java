package cl.tbd.ejemplo1.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Debe ingresar un nombre.")
    private String nombre;
    @NotBlank(message = "Debe ingresar un apellido.")
    private String apellido;
    @NotBlank(message = "Debe ingresar un email.")
    @Email(message = "El email ingresado no es válido.")
    private String email;
    @NotBlank(message = "Debe ingresar una contraseña.")
    private String password;
    @NotNull(message = "Debe ingresar una institución.")
    private Integer idInstitucion;


}
