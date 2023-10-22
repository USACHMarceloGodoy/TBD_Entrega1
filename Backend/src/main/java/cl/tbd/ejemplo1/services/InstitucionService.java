package cl.tbd.ejemplo1.services;

import cl.tbd.ejemplo1.errors.ApiErrorException;
import cl.tbd.ejemplo1.errors.InvalidDatesException;
import cl.tbd.ejemplo1.models.Emergencia;
import cl.tbd.ejemplo1.models.Habilidad;
import cl.tbd.ejemplo1.models.Institucion;
import cl.tbd.ejemplo1.Repository.InstitucionRepositoryImp;
import cl.tbd.ejemplo1.request.CreateEmergenciaRequest;
import cl.tbd.ejemplo1.request.CreateInstitucionRequest;
import cl.tbd.ejemplo1.request.UpdateHabilidadRequest;
import cl.tbd.ejemplo1.request.UpdateInstitucionRequest;
import cl.tbd.ejemplo1.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitucionService {
    private InstitucionRepositoryImp institucionRepository;
    public void createInstitucion(CreateInstitucionRequest request){
        try{
            Institucion institucion = new Institucion();
            institucion.setNombre(request.getNombre());
            institucion.setDescripcion(request.getDescripcion());
            institucionRepository.save(institucion);
        }catch(Exception e){
            throw new ApiErrorException("No se pudo crear la institución " + e.getMessage());
        }
    }

    public List<Institucion> getInstituciones(){
        return institucionRepository.findAll();
    }

    public void updateInstitucion(Integer id, Institucion newInstitucion){
        try{
            Institucion institucion = institucionRepository.findById(id);
            if(institucion == null) throw new ApiErrorException("La institución a a<ctualizar no existe");
            if(newInstitucion.getNombre() != null){
                institucion.setNombre(newInstitucion.getNombre());
            }
            if(newInstitucion.getDescripcion() != null) {
                institucion.setDescripcion(newInstitucion.getDescripcion());
            }
            institucionRepository.set(institucion);
        }catch(Exception e){
            throw new ApiErrorException("Error al actualizar la institución " + e.getMessage());
        }
    }

    public void deleteInstitucion(Integer idInstitucion) {
        try{
            Institucion institucion = institucionRepository.findById(idInstitucion);
            if(institucion == null) throw new ApiErrorException("La institucion no existe.");
            institucionRepository.delete(idInstitucion);
        }catch(Exception e){
            throw new ApiErrorException("Error al eliminar la institución " + e.getMessage());
        }
    }
}
