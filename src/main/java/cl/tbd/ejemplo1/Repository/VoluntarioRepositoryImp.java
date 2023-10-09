package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Voluntario;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

@Repository
public class VoluntarioRepositoryImp {
    public Sql2o sql2o;
    public void save(Voluntario voluntario) {
        String sql = "INSERT INTO voluntario (id, usuario, puntaje, tareas, habilidades) " +
                "VALUES (:voluntarioId, :usuario, :puntaje, :tareas, :habilidades)";

        try {
            sql2o.open()
                    .createQuery(sql, true)
                    .addParameter("voluntarioId", voluntario.getId())
                    .addParameter("usuario", voluntario.getUsuario())
                    .addParameter("puntaje", voluntario.getPuntaje())
                    .addParameter("tareas", voluntario.getTareas())
                    .addParameter("habilidades", voluntario.getHabilidades())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            // Manejar la excepción, imprimir un mensaje de error o lanzar una excepción personalizada si es necesario
            ex.printStackTrace(); // O utiliza un logger para manejar adecuadamente las excepciones
        }
    }

}
