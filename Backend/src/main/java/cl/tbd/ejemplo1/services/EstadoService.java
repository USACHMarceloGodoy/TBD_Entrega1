package cl.tbd.ejemplo1.services;

import cl.tbd.ejemplo1.errors.ApiErrorException;
import cl.tbd.ejemplo1.errors.InvalidDatesException;
import cl.tbd.ejemplo1.models.Emergencia;
import cl.tbd.ejemplo1.models.Estado;
import cl.tbd.ejemplo1.models.Institucion;
import cl.tbd.ejemplo1.Repository.EstadoRepositoryImp;
import cl.tbd.ejemplo1.request.CreateEmergenciaRequest;
import cl.tbd.ejemplo1.request.CreateEstadoRequest;
import cl.tbd.ejemplo1.request.UpdateEmergenciaRequest;
import cl.tbd.ejemplo1.request.UpdateEstadoRequest;
import cl.tbd.ejemplo1.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private EstadoRepositoryImp estadoRepository;
    public List<Estado> getEstados(){
        try{
            return estadoRepository.findAll();
        }catch(Exception e){
            throw new ApiErrorException("Los estados buscados no existen.");
        }
    }

    public void updateEstado(Integer id, UpdateEstadoRequest newEstado){
        try{
            Estado estado = estadoRepository.findById(id);
            if(estado == null) throw new ApiErrorException("El estado a actualizar no existe");
            if(newEstado.getDescripcion() != null) {
                estado.setDescripcion(newEstado.getDescripcion());
            }
            estadoRepository.set(estado);
        }catch(Exception e){
            throw new ApiErrorException("Error al actualizar la emergencia." + e.getMessage());
        }
    }

    public void createEstado(CreateEstadoRequest request){
        try{
            Estado estado = new Estado();
            estado.setDescripcion(request.getDescripcion());
            estadoRepository.save(estado);
        }catch(Exception e){
            throw new ApiErrorException("El estado no ha podido ser creado." + e.getMessage());
        }
    }

    public void deleteEstado(Integer idEstado) {
        try{
            if(estadoRepository.findById(idEstado) == null) throw new ApiErrorException("El estado no existe");
            estadoRepository.delete(idEstado);
        }catch (Exception e){
            throw new ApiErrorException("El estado no ha podido ser eliminado. " + e.getMessage());
        }
    }
}
