package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Tarea;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

@Repository
public class TareaRepositoryImp {
    public Sql2o sql2o;
    //save
    public void save(Tarea tarea) {
        String sql = "INSERT INTO tarea (id, nombre, descripcion, voluntarios_requeridos, voluntarios_inscritos, " +
                "fechaInicio, fechaFin, estado, habilidades, emergencia, direccion) " +
                "VALUES (:tareaId, :nombre, :descripcion, :voluntariosRequeridos, :voluntariosInscritos, " +
                ":fechaInicio, :fechaFin, :estado, :habilidades, :emergencia, :direccion)";

        try {
            sql2o.open()
                    .createQuery(sql, true)
                    .addParameter("tareaId", tarea.getId())
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("voluntariosRequeridos", tarea.getVoluntariosRequeridos())
                    .addParameter("voluntariosInscritos", tarea.getVoluntariosInscritos())
                    .addParameter("fechaInicio", tarea.getFechaInicio())
                    .addParameter("fechaFin", tarea.getFechaFin())
                    .addParameter("estado", tarea.getEstado())
                    .addParameter("habilidades", tarea.getHabilidades())
                    .addParameter("emergencia", tarea.getEmergencia())
                    .addParameter("direccion", tarea.getDireccion())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            // Manejar la excepción, imprimir un mensaje de error o lanzar una excepción personalizada si es necesario
            ex.printStackTrace(); // O utiliza un logger para manejar adecuadamente las excepciones
        }
    }

}
