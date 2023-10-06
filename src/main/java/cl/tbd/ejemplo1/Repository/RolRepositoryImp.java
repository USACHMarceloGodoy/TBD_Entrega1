package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class RolRepositoryImp {
    @Autowired
    private Sql2o sql2o;

    public void save(Rol rol) {
        try (Connection con = sql2o.beginTransaction()) {
            Integer id = con.createQuery("SELECT nextval('rol_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO rol (id_rol, nombre) " +
                    "VALUES (:id_rol, :nombre)";

            con.createQuery(sql)
                    .addParameter("id_rol", id)
                    .addParameter("nombre", rol.getNombre())
                    .executeUpdate()
                    .getKey(Integer.class);

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

