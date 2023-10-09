package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Institucion;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.security.Key;

@Repository
public class InstitucionRepositoryImp {
    public Sql2o sql2o;
    //save
    public void save(Institucion institucion) {
        String sql = "INSERT INTO institucion (id, nombre, descripcion, direccion, emergencias) " +
                "VALUES (:institucionId, :nombre, :descripcion, :direccion, :emergencias)";

        try {
            sql2o.open()
                    .createQuery(sql, true)
                    .addParameter("institucionId", institucion.getId())
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("descripcion", institucion.getDescripcion())
                    .addParameter("direccion", institucion.getDireccion())
                    .addParameter("emergencias", institucion.getEmergencias())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }


}
