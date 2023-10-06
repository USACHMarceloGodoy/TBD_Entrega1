package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RolRepositoryImp {
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

    public List<Rol> findAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM rol";
            return con.createQuery(sql)
                    .addColumnMapping("id_rol", "id")
                    .executeAndFetch(Rol.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Rol findById(int idRol){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM rol WHERE id_rol = :id_rol";
            return con.createQuery(sql)
                    .addColumnMapping("id_rol", "id")
                    .addParameter("id_rol", idRol)
                    .executeAndFetchFirst(Rol.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void set(Rol rol){
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE rol SET nombre = :nombre WHERE id_rol = :id_rol";
            con.createQuery(sql)
                    .addParameter("id_rol", rol.getId())
                    .addParameter("nombre", rol.getNombre())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int idRol){
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM rol WHERE id_rol = :id_rol";
            con.createQuery(sql)
                    .addParameter("id_rol", idRol)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

