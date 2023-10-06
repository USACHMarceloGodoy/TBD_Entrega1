package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Voluntario;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

@Repository
public class VoluntarioRepositoryImp {
    public Sql2o sql2o;
    public void save(Voluntario voluntario){
        sql2o.open().createQuery("INSERT INTO voluntario (id, nombre, apellido, edad, id_institucion) VALUES (:voluntarioId, :voluntarioNombre, :voluntarioApellido, :voluntarioEdad, :voluntarioId_institucion)", true)
                .addParameter("voluntarioId", voluntario.getId())
                .addParameter("voluntarioNombre", voluntario.getNombre())
                .addParameter("voluntarioApellido", voluntario.getApellido())
                .addParameter("voluntarioEdad", voluntario.getEdad())
                .addParameter("voluntarioId_institucion", voluntario.getId_institucion())
                .executeUpdate().getKey();
    }
}
