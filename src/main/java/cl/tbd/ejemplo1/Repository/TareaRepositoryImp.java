package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Tarea;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaRepositoryImp {

    public Sql2o sql2o;

    // CRUD - Create
    public void save(Tarea tarea) {
        try (Connection con = sql2o.open()) {
            Integer id = con.createQuery("SELECT nextval('tarea_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO tarea (id, nombre, descripcion, voluntarios_requeridos, voluntarios_inscritos, " +
                    "fechaInicio, fechaFin, estado, habilidades, emergencia, direccion) " +
                    "VALUES (:id, :nombre, :descripcion, :voluntariosRequeridos, :voluntariosInscritos, " +
                    ":fechaInicio, :fechaFin, :estado, :habilidades, :emergencia, :direccion)";

            con.createQuery(sql)
                    .addParameter("id", id)
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

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Find All
    public List<Tarea> findAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM tarea";
            return con.createQuery(sql)
                    .addColumnMapping("id_tarea", "id")
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Find by Id
    public Tarea findById(int idTarea) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM tarea WHERE id_tarea = :id_tarea";
            return con.createQuery(sql)
                    .addColumnMapping("id_tarea", "id")
                    .addParameter("id_tarea", idTarea)
                    .executeAndFetchFirst(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Update
    public void update(Tarea tarea) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE tarea SET nombre = :nombre, descripcion = :descripcion, " +
                    "voluntarios_requeridos = :voluntariosRequeridos, voluntarios_inscritos = :voluntariosInscritos, " +
                    "fechaInicio = :fechaInicio, fechaFin = :fechaFin, estado = :estado, habilidades = :habilidades, " +
                    "emergencia = :emergencia, direccion = :direccion WHERE id_tarea = :id_tarea";
            con.createQuery(sql)
                    .addParameter("id_tarea", tarea.getId())
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Delete
    public void delete(int idTarea) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM tarea WHERE id_tarea = :id_tarea";
            con.createQuery(sql)
                    .addParameter("id_tarea", idTarea)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
