package cl.tbd.ejemplo1.Repository;

import cl.tbd.ejemplo1.models.Ranking;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RankingRepositoryIMP {

    public Sql2o sql2o;

    // CRUD - Create
    public void save(Ranking ranking) {
        try (Connection con = sql2o.open()) {
            Integer id = con.createQuery("SELECT nextval('ranking_seq')")
                    .executeScalar(Integer.class);

            String sql = "INSERT INTO ranking (id, puntaje, flagInvitado, flagParticipa) " +
                    "VALUES (:id, :puntaje, :flagInvitado, :flagParticipa)";

            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("puntaje", ranking.getPuntaje())
                    .addParameter("flagInvitado", ranking.getFlagInvitado())
                    .addParameter("flagParticipa", ranking.getFlagParticipa())
                    .executeUpdate();

            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Find All
    public List<Ranking> findAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM ranking";
            return con.createQuery(sql)
                    .addColumnMapping("id_ranking", "id")
                    .executeAndFetch(Ranking.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Find by Id
    public Ranking findById(int idRanking) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM ranking WHERE id_ranking = :id_ranking";
            return con.createQuery(sql)
                    .addColumnMapping("id_ranking", "id")
                    .addParameter("id_ranking", idRanking)
                    .executeAndFetchFirst(Ranking.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // CRUD - Update
    public void update(Ranking ranking) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE ranking SET puntaje = :puntaje, flagInvitado = :flagInvitado, " +
                    "flagParticipa = :flagParticipa WHERE id_ranking = :id_ranking";
            con.createQuery(sql)
                    .addParameter("id_ranking", ranking.getId())
                    .addParameter("puntaje", ranking.getPuntaje())
                    .addParameter("flagInvitado", ranking.getFlagInvitado())
                    .addParameter("flagParticipa", ranking.getFlagParticipa())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD - Delete
    public void delete(int idRanking) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM ranking WHERE id_ranking = :id_ranking";
            con.createQuery(sql)
                    .addParameter("id_ranking", idRanking)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
