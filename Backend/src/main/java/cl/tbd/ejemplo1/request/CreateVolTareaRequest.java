package cl.tbd.ejemplo1.request;

import lombok.Data;

@Data
public class CreateVolTareaRequest {
    Integer puntaje;
    Integer flag_participa;
    Integer flag_invitado;
}
