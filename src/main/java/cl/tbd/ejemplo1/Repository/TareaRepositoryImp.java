package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Tarea;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

@Repository
public class TareaRepositoryImp {
    public Sql2o sql2o;
    //save
    public void save(Tarea tarea){
        sql2o.open().createQuery("INSERT INTO tarea (id, id_emergencia, id_voluntario, puntaje) VALUES (:tareaId, :tareaId_emergencia, :tareaId_voluntario, :tareaPuntaje)", true)
                .addParameter("tareaId", tarea.getId())
                .addParameter("nombre", tarea.getNombre())
                .addParameter("descripcion", tarea.getDescripcion())
                .addParameter("voluntarios_requeridos", tarea.getVoluntariosRequeridos())
                .addParameter("voluntarios_inscritos", tarea.getVoluntariosInscritos())
                .addParameter("fechaInicio", tarea.getFechaInicio())
                .addParameter("fechaFin", tarea.getFechaFin())
                .addParameter("estado", tarea.getEstado())
                .addParameter("habilidades", tarea.getHabilidades())
                .addParameter("emergencia", tarea.getEmergencia())
                .addParameter("direccion", tarea.getDireccion())
                .executeUpdate().getKey();
    }
}
