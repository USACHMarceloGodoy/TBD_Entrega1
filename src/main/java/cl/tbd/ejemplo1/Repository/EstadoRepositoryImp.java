package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Estado;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class EstadoRepositoryImp {
    public Sql2o sql2o;

    public void save(Estado estado){
        try (org.sql2o.Connection con = sql2o.open()) {
            Integer id = con.createQuery("SELECT nextval('estado_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO estado (id_estado, descripcion) " +
                    "VALUES (:id_estado, :descripcion)";

            con.createQuery(sql)
                    .addParameter("id_estado", id)
                    .addParameter("descripcion", estado.getDescripcion())
                    .executeUpdate()
                    .getKey(Integer.class);

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //find all
    public List<Estado> findAll(){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM estado";
            return con.createQuery(sql)
                    .addColumnMapping("id_estado", "id")
                    .executeAndFetch(Estado.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    //find by id
    public Estado findById(int idEstado){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM estado WHERE id_estado = :id_estado";
            return con.createQuery(sql)
                    .addColumnMapping("id_estado", "id")
                    .addParameter("id_estado", idEstado)
                    .executeAndFetchFirst(Estado.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    //set
    public void set(Estado estado){
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE estado SET descripcion = :descripcion WHERE id_estado = :id_estado";
            con.createQuery(sql)
                    .addParameter("id_estado", estado.getId())
                    .addParameter("descripcion", estado.getDescripcion())
                    .executeUpdate()
                    .getKey(Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //delete
    public void delete(int idEstado){
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM estado WHERE id_estado = :id_estado";
            con.createQuery(sql)
                    .addParameter("id_estado", idEstado)
                    .executeUpdate()
                    .getKey(Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
