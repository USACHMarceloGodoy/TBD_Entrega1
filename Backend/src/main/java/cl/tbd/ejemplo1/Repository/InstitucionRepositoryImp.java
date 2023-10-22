package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Institucion;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitucionRepositoryImp {

    public Sql2o sql2o;

    // CRUD - Create
    public void save(Institucion institucion) {
        try (Connection con = sql2o.open()) {
            Integer id = con.createQuery("SELECT nextval('institucion_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO institucion (id, nombre, descripcion, direccion, emergencias) " +
                    "VALUES (:id, :nombre, :descripcion, :direccion, :emergencias)";

            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("descripcion", institucion.getDescripcion())
                    .addParameter("direccion", institucion.getDireccion())
                    .addParameter("emergencias", institucion.getEmergencias())
                    .executeUpdate();

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Find All
    public List<Institucion> findAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM institucion";
            return con.createQuery(sql)
                    .addColumnMapping("id_institucion", "id")
                    .executeAndFetch(Institucion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Find by Id
    public Institucion findById(int idInstitucion) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM institucion WHERE id_institucion = :id_institucion";
            return con.createQuery(sql)
                    .addColumnMapping("id_institucion", "id")
                    .addParameter("id_institucion", idInstitucion)
                    .executeAndFetchFirst(Institucion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Update
    public void update(Institucion institucion) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE institucion SET nombre = :nombre, descripcion = :descripcion, " +
                    "direccion = :direccion, emergencias = :emergencias WHERE id_institucion = :id_institucion";
            con.createQuery(sql)
                    .addParameter("id_institucion", institucion.getId())
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("descripcion", institucion.getDescripcion())
                    .addParameter("direccion", institucion.getDireccion())
                    .addParameter("emergencias", institucion.getEmergencias())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Delete
    public void delete(int idInstitucion) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM institucion WHERE id_institucion = :id_institucion";
            con.createQuery(sql)
                    .addParameter("id_institucion", idInstitucion)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void set(Institucion institucion){
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE institucion SET nombre = :nombre, descripcion = :descripcion, direccion = :direccion WHERE id_institucion = :id_institucion";
            con.createQuery(sql)
                    .addParameter("id_institucion", institucion.getId())
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("descripcion", institucion.getDescripcion())
                    .addParameter("direccion", institucion.getDireccion())
                    .executeUpdate()
                    .getKey(Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

