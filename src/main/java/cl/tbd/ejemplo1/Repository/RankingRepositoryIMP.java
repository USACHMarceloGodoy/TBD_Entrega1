package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

@Repository
public class RankingRepositoryIMP {
    public Sql2o sql2o;
    //save
    public void save(Ranking ranking){
        sql2o.open().createQuery("INSERT INTO ranking (id, id_emergencia, id_voluntario, puntaje) VALUES (:rankingId, :rankingId_emergencia, :rankingId_voluntario, :rankingPuntaje)", true)
                .addParameter("rankingId", ranking.getId())
                .addParameter("puntaje", ranking.getPuntaje())
                .addParameter("flagInvitado", ranking.getFlagInvitado())
                .addParameter("flagParticipa", ranking.getFlagParticipa())
                .executeUpdate().getKey();
    }
}
