package cl.tbd.ejemplo1.services;

import cl.tbd.ejemplo1.errors.ApiErrorException;
import cl.tbd.ejemplo1.errors.InstitucionNotFoundException;
import cl.tbd.ejemplo1.errors.InvalidDatesException;
import cl.tbd.ejemplo1.models.*;
import cl.tbd.ejemplo1.Repository.EmergenciaRepositoryImp;
import cl.tbd.ejemplo1.Repository.InstitucionRepositoryImp;
import cl.tbd.ejemplo1.Repository.UsuarioRepositoryImp;
import cl.tbd.ejemplo1.Repository.HablidadRepositoryImp;
import cl.tbd.ejemplo1.request.CreateEmergenciaRequest;
import cl.tbd.ejemplo1.request.CreateTareaRequest;
import cl.tbd.ejemplo1.request.UpdateEmergenciaRequest;
import cl.tbd.ejemplo1.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Geometry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergenciaService {
    private EmergenciaRepositoryImp habilidadRepository;
    private EmergenciaRepositoryImp emergenciaRepository;
    private InstitucionRepositoryImp institucionRepository;
    private UsuarioRepositoryImp usuarioRepository;
    public ResponseEntity<Emergencia> createEmergencia(CreateEmergenciaRequest request){
        try{
            if(!ValidationUtil.validateDates(request.getFechaInicio(), request.getFechaFin())){
                throw new InvalidDatesException("La fecha de inicio debe ser menor a la fecha final");
            };
            Institucion institucion = institucionRepository.findById(request.getId_institucion());
            if(institucion == null) throw new ApiErrorException("La institución no existe.\n");
            Emergencia emergencia = new Emergencia();
            emergencia.setNombre(request.getNombre());
            emergencia.setDescripcion(request.getDescripcion());
            emergencia.setFechaInicio(request.getFechaInicio());
            emergencia.setFechaFin(request.getFechaFin());
            emergencia.setNivelPrioridad(request.getNivelPrioridad());
            Emergencia resultadoEmergencia = emergenciaRepository.save(emergencia);
            return new ResponseEntity<>(resultadoEmergencia, HttpStatus.CREATED);
        }catch(Exception e){
            throw new ApiErrorException("Error al crear la emergencia " + e.getMessage());
        }
    }

    public List<Emergencia> getEmergencias(){
        return emergenciaRepository.findAll();
    }

    public List<Habilidad> getHabilidadesEmergencia(Integer idEmergencia) {
        try{
            if(emergenciaRepository.findById(idEmergencia) == null) throw new ApiErrorException("La emergencia no existe.");
            return habilidadRepository.findAllByEmergenciaId(idEmergencia);
        }catch(Exception e){
            throw new ApiErrorException("Ha ocurrido un error al obtener las emergencias " + e.getMessage());
        }
    }

    public void updateEmergencia(Integer id, UpdateEmergenciaRequest newEmergencia){
        try{
            Emergencia emergencia = emergenciaRepository.findById(id);
            if(emergencia == null) throw new ApiErrorException("La emergencia a actualizar no existe");
            if(newEmergencia.getNombre() != null) {
                emergencia.setNombre(newEmergencia.getNombre());
            }
            if(newEmergencia.getDescripcion() != null){
                emergencia.setDescripcion(newEmergencia.getDescripcion());
            }
            if(newEmergencia.getFechaInicio() != null){
                emergencia.setFechaInicio(newEmergencia.getFechaInicio());
            }
            if(newEmergencia.getFechaFin() != null){
                emergencia.setFechaFin(newEmergencia.getFechaFin());
            }
            if(newEmergencia.getNivelPrioridad() != null){
                emergencia.setNivelPrioridad(newEmergencia.getNivelPrioridad());
            }
            
            emergenciaRepository.set(emergencia);
        }catch(Exception e){
            throw new ApiErrorException("Error al actualizar la emergencia." + e.getMessage());
        }
    }

    public void deleteEmergencia(Integer idEmergencia) {
        try{
            if(emergenciaRepository.findById(idEmergencia) == null) throw new ApiErrorException("La emergencia no existe.");
            emergenciaRepository.delete(idEmergencia);
        }catch (Exception e){
            throw new ApiErrorException("Ha ocurrido un error al eliminar la emergencia. " + e.getMessage());
        }
    }

}
