package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

@Repository
public class RankingRepositoryIMP {
    public Sql2o sql2o;
    //save
    public void save(Ranking ranking) {
        String sql = "INSERT INTO ranking (id, puntaje, flagInvitado, flagParticipa) " +
                "VALUES (:rankingId, :puntaje, :flagInvitado, :flagParticipa)";

        try {
            sql2o.open()
                    .createQuery(sql, true)
                    .addParameter("rankingId", ranking.getId())
                    .addParameter("puntaje", ranking.getPuntaje())
                    .addParameter("flagInvitado", ranking.getFlagInvitado())
                    .addParameter("flagParticipa", ranking.getFlagParticipa())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            // Manejar la excepción, imprimir un mensaje de error o lanzar una excepción personalizada si es necesario
            ex.printStackTrace(); // O utiliza un logger para manejar adecuadamente las excepciones
        }
    }

}
