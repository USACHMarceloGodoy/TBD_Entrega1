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
                .addParameter("tareaId_emergencia", tarea.getId_emergencia())
                .addParameter("tareaId_voluntario", tarea.getId_voluntario())
                .addParameter("tareaPuntaje", tarea.getPuntaje())
                .executeUpdate().getKey();
    }
}
