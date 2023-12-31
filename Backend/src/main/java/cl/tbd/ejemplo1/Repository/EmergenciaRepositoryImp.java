package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Emergencia;
import cl.tbd.ejemplo1.models.Habilidad;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


@Repository
public class EmergenciaRepositoryImp {
    public Sql2o sql2o;
    //CRUD
    //Create
    public Emergencia save(Emergencia emergencia){
        try (org.sql2o.Connection con = sql2o.open()) {
            Integer id = con.createQuery("SELECT nextval('emergencia_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO emergencia (id_emergencia, nombre, descripcion, fecha_inicio, fecha_fin, nivel_prioridad, direccion) " +
                    "VALUES (:id_emergencia, :nombre, :descripcion, :fecha_inicio, :fecha_fin, :nivel_prioridad, :direccion)";

            con.createQuery(sql)
                    .addParameter("id_emergencia", id)
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .addParameter("fecha_inicio", emergencia.getFechaInicio())
                    .addParameter("fecha_fin", emergencia.getFechaFin())
                    .addParameter("nivel_prioridad", emergencia.getNivelPrioridad())
                    .addParameter("direccion", emergencia.getDireccion())
                    .executeUpdate()
                    .getKey(Integer.class);

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emergencia;
    }
    //find all
    public List<Emergencia> findAll(){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM emergencia";
            return con.createQuery(sql)
                    .addColumnMapping("id_emergencia", "id")
                    .executeAndFetch(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //find by id
    public Emergencia findById(int idEmergencia){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM emergencia WHERE id_emergencia = :id_emergencia";
            return con.createQuery(sql)
                    .addColumnMapping("id_emergencia", "id")
                    .addParameter("id_emergencia", idEmergencia)
                    .executeAndFetchFirst(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    //set
    public void set(Emergencia emergencia){
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE emergencia SET nombre = :nombre, descripcion = :descripcion, fecha_inicio = :fecha_inicio, fecha_fin = :fecha_fin, nivel_prioridad = :nivel_prioridad, direccion = :direccion WHERE id_emergencia = :id_emergencia";
            con.createQuery(sql)
                    .addParameter("id_emergencia", emergencia.getId())
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .addParameter("fecha_inicio", emergencia.getFechaInicio())
                    .addParameter("fecha_fin", emergencia.getFechaFin())
                    .addParameter("nivel_prioridad", emergencia.getNivelPrioridad())
                    .addParameter("direccion", emergencia.getDireccion())
                    .executeUpdate()
                    .getKey(Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //delete
    public void delete(int idEmergencia){
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM emergencia WHERE id_emergencia = :id_emergencia";
            con.createQuery(sql)
                    .addParameter("id_emergencia", idEmergencia)
                    .executeUpdate()
                    .getKey(Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Habilidad> findAllByEmergenciaId(Integer idEmergencia) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM habilidad WHERE id_habilidad IN (SELECT id_habilidad FROM emergencia_habilidad WHERE id_emergencia = :id_emergencia)";
            return con.createQuery(sql)
                    .addParameter("id_emergencia", idEmergencia)
                    .addColumnMapping("id_habilidad", "id")
                    .executeAndFetch(Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Emergencia[] findAllByHabilidadId(Integer idHabilidad) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM emergencia WHERE id_emergencia IN (SELECT id_emergencia FROM emergencia_habilidad WHERE id_habilidad = :id_habilidad)";
            return con.createQuery(sql)
                    .addParameter("id_habilidad", idHabilidad)
                    .addColumnMapping("id_emergencia", "id")
                    .executeAndFetch(Emergencia[].class).toArray(new Emergencia[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
