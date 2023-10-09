package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Usuario;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

@Repository
public class UsuarioRepositoryImp {
    public Sql2o sql2o;
    //save
    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuario (id, nombre, apellido, email, password, roles) " +
                "VALUES (:usuarioId, :usuarioNombre, :apellido, :email, :password, :roles)";

        try {
            sql2o.open()
                    .createQuery(sql, true)
                    .addParameter("usuarioId", usuario.getId())
                    .addParameter("usuarioNombre", usuario.getNombre())
                    .addParameter("apellido", usuario.getApellido())
                    .addParameter("email", usuario.getEmail())
                    .addParameter("password", usuario.getPassword())
                    .addParameter("roles", usuario.getRoles())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            // Manejar la excepción, imprimir un mensaje de error o lanzar una excepción personalizada si es necesario
            ex.printStackTrace(); // O utiliza un logger para manejar adecuadamente las excepciones
        }
    }

}
