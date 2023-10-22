package cl.tbd.ejemplo1.services;

import cl.tbd.ejemplo1.errors.ApiErrorException;
import cl.tbd.ejemplo1.models.Habilidad;
import cl.tbd.ejemplo1.models.Tarea;
import cl.tbd.ejemplo1.models.Usuario;
import cl.tbd.ejemplo1.Repository.*;
import cl.tbd.ejemplo1.request.CreateVolTareaRequest;
import cl.tbd.ejemplo1.request.UpdateHabilidadVoluntarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoluntarioService {
    @Autowired
    private final VoluntarioRepositoryImp voluntarioRepository;

    @Autowired
    private final UsuarioRepositoryImp usuarioRepository;

    @Autowired
    private final TareaRepositoryImp tareaRepository;

    @Autowired
    private final EmergenciaRepositoryImp emergenciaRepository;

    @Autowired
    private final HablidadRepositoryImp habilidadRepository;

}
