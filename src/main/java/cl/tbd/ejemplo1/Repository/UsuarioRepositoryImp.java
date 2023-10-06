package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Usuario;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

@Repository
public class UsuarioRepositoryImp {
    public Sql2o sql2o;
    //save
    public void save(Usuario usuario){
        sql2o.open().createQuery("INSERT INTO usuario (id, nombre, nacimiento, sexo, id_institucion) VALUES (:usuarioId, :usuarioNombre, :usuarioNacimiento, :usuarioSexo, :usuarioId_institucion)", true)
                .addParameter("usuarioId", usuario.getId())
                .addParameter("usuarioNombre", usuario.getNombre())
                .addParameter("usuarioNacimiento", usuario.getNacimiento())
                .addParameter("usuarioSexo", usuario.getSexo())
                .addParameter("usuarioId_institucion", usuario.getId_institucion())
                .executeUpdate().getKey();
    }
}
