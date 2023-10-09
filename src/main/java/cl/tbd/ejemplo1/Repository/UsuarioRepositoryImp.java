package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Usuario;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class UsuarioRepositoryImp {

    public Sql2o sql2o;

    // CRUD - Create
    public void save(Usuario usuario) {
        try (Connection con = sql2o.open()) {
            Integer id = con.createQuery("SELECT nextval('usuario_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO usuario (id, nombre, apellido, email, password, roles) " +
                    "VALUES (:id, :nombre, :apellido, :email, :password, :roles)";

            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("apellido", usuario.getApellido())
                    .addParameter("email", usuario.getEmail())
                    .addParameter("password", usuario.getPassword())
                    .addParameter("roles", usuario.getRoles())
                    .executeUpdate();

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Find All
    public List<Usuario> findAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM usuario";
            return con.createQuery(sql)
                    .addColumnMapping("id_usuario", "id")
                    .executeAndFetch(Usuario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Find by Id
    public Usuario findById(int idUsuario) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM usuario WHERE id_usuario = :id_usuario";
            return con.createQuery(sql)
                    .addColumnMapping("id_usuario", "id")
                    .addParameter("id_usuario", idUsuario)
                    .executeAndFetchFirst(Usuario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Update
    public void update(Usuario usuario) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE usuario SET nombre = :nombre, apellido = :apellido, " +
                    "email = :email, password = :password, roles = :roles WHERE id_usuario = :id_usuario";
            con.createQuery(sql)
                    .addParameter("id_usuario", usuario.getId())
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("apellido", usuario.getApellido())
                    .addParameter("email", usuario.getEmail())
                    .addParameter("password", usuario.getPassword())
                    .addParameter("roles", usuario.getRoles())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Delete
    public void delete(int idUsuario) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM usuario WHERE id_usuario = :id_usuario";
            con.createQuery(sql)
                    .addParameter("id_usuario", idUsuario)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
