package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Institucion;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

@Repository
public class InstitucionRepositoryImp {
    public Sql2o sql2o;
    //save
    public void save(Institucion institucion){
        sql2o.open().createQuery("INSERT INTO institucion (id, nombre, direccion, cant_voluntarios) VALUES (:institucionId, :institucionNombre, :institucionDireccion, :institucionCant_voluntarios)", true)
                .addParameter("institucionId", institucion.getId())
                .addParameter("nombre", institucion.getNombre())
                .addParameter("descripcion", institucion.getDescripcion())
                .addParameter("direccion", institucion.getDireccion())
                .addParameter("emergencias", institucion.getEmergencias())
                .executeUpdate().getKey();
    }
}
