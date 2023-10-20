package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Voluntario;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VoluntarioRepositoryImp {

    public Sql2o sql2o;

    // CRUD - Create
    public void save(Voluntario voluntario) {
        try (Connection con = sql2o.open()) {
            Integer id = con.createQuery("SELECT nextval('voluntario_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO voluntario (id, usuario, puntaje, tareas, habilidades) " +
                    "VALUES (:id, :usuario, :puntaje, :tareas, :habilidades)";

            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("usuario", voluntario.getUsuario())
                    .addParameter("puntaje", voluntario.getPuntaje())
                    .addParameter("tareas", voluntario.getTareas())
                    .addParameter("habilidades", voluntario.getHabilidades())
                    .executeUpdate();

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Find All
    public List<Voluntario> findAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM voluntario";
            return con.createQuery(sql)
                    .addColumnMapping("id_voluntario", "id")
                    .executeAndFetch(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Find by Id
    public Voluntario findById(int idVoluntario) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM voluntario WHERE id_voluntario = :id_voluntario";
            return con.createQuery(sql)
                    .addColumnMapping("id_voluntario", "id")
                    .addParameter("id_voluntario", idVoluntario)
                    .executeAndFetchFirst(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Update
    public void update(Voluntario voluntario) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE voluntario SET usuario = :usuario, puntaje = :puntaje, " +
                    "tareas = :tareas, habilidades = :habilidades WHERE id_voluntario = :id_voluntario";
            con.createQuery(sql)
                    .addParameter("id_voluntario", voluntario.getId())
                    .addParameter("usuario", voluntario.getUsuario())
                    .addParameter("puntaje", voluntario.getPuntaje())
                    .addParameter("tareas", voluntario.getTareas())
                    .addParameter("habilidades", voluntario.getHabilidades())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Delete
    public void delete(int idVoluntario) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM voluntario WHERE id_voluntario = :id_voluntario";
            con.createQuery(sql)
                    .addParameter("id_voluntario", idVoluntario)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
