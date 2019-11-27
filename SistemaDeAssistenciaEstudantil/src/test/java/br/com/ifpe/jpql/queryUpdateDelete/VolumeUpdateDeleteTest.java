package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Volume;
import br.com.ifpe.modelo.Volume;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class VolumeUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        Long id = 3L;
        String param = "Volume três";
        Query update = em.createQuery("UPDATE Volume AS v SET v.descricaoVolume = ?1 WHERE v.idVolume = ?2");
        update.setParameter(1, param);
        update.setParameter(2, id);
        update.executeUpdate();
        String jpql = "SELECT v FROM Volume v WHERE v.idVolume = :id";
        TypedQuery<Volume> query = em.createQuery(jpql, Volume.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Volume tamanho = query.getSingleResult();
        assertEquals(param, tamanho.getDescricaoVolume());
    }
    
//    @Test(expected = NoResultException.class)
//    public void queryDelete() {
//        logger.info("Executando queryDelete()");
//        Long id = 5L;
//        Query delete = em.createQuery("DELETE FROM Volume AS v WHERE v.idVolume = ?1");
//        delete.setParameter(1, id);
//        delete.executeUpdate();
//        String jpql = "SELECT v FROM Volume v WHERE v.idVolume = ?1";
//        TypedQuery<Volume> query = em.createQuery(jpql, Volume.class);
//        query.setParameter(1, id);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        Volume bolsa = query.getSingleResult();
//        assertNull(bolsa);
//    }
    
    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        String edicao = "Edição rara";
        Query delete = em.createQuery("DELETE FROM Volume AS v WHERE v.descricaoVolume = ?1");
        delete.setParameter(1, edicao);
        delete.executeUpdate();
        String jpql = "SELECT v FROM Volume v WHERE v.descricaoVolume = ?1";
        TypedQuery<Volume> query = em.createQuery(jpql, Volume.class);
        query.setParameter(1, edicao);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Volume bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
