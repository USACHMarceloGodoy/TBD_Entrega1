package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Habilidad;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
@Repository
public class HablidadRepositoryImp {
    private Sql2o sql2o;
    public void save(Habilidad habilidad) {
        try (Connection con = sql2o.beginTransaction()) {
            Integer id = con.createQuery("SELECT nextval('hablidad_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO hablidad (id_hablidad, descripcion, grado) " +
                    "VALUES (:id_hablidad, :descripcion, :grado)";

            con.createQuery(sql)
                    .addParameter("id_hablidad", id)
                    .addParameter("descripcion", habilidad.getDescripcion())
                    .addParameter("grado", habilidad.getGrado())
                    .executeUpdate()
                    .getKey(Integer.class);

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Habilidad> findAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM habilidad";
            return con.createQuery(sql)
                    .addColumnMapping("id_hablidad", "id")
                    .executeAndFetch(Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Habilidad findById(int idHablidad){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM hablidad WHERE id_hablidad = :id_hablidad";
            return con.createQuery(sql)
                    .addColumnMapping("id_hablidad", "id")
                    .addParameter("id_hablidad", idHablidad)
                    .executeAndFetchFirst(Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void set(Habilidad habilidad){
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE hablidad SET descripcion = :descripcion, grado = :grado WHERE id_hablidad = :id_hablidad";
            con.createQuery(sql)
                    .addParameter("id_hablidad", habilidad.getId())
                    .addParameter("descripcion", habilidad.getDescripcion())
                    .addParameter("grado", habilidad.getGrado())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Habilidad habilidad){
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM hablidad WHERE id_hablidad = :id_hablidad";
            con.createQuery(sql)
                    .addParameter("id_hablidad", habilidad.getId())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
