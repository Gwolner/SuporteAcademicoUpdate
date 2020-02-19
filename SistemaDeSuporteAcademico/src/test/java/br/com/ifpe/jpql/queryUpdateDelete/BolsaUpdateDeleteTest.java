package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Bolsa;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class BolsaUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        Long id = 6L;
        Double valor = 300.50;
        Query update = em.createQuery("UPDATE Bolsa AS b SET b.valor = ?1 WHERE b.idBolsa = ?2");
        update.setParameter(1, valor);
        update.setParameter(2, id);
        update.executeUpdate();
        String jpql = "SELECT b FROM Bolsa b WHERE b.idBolsa = :id";
        TypedQuery<Bolsa> query = em.createQuery(jpql, Bolsa.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Bolsa prof = query.getSingleResult();
        assertEquals(valor, prof.getValor());
    }
    
    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        Double valor = 600.00;
        Query delete = em.createQuery("DELETE FROM Bolsa AS b WHERE b.valor = ?1");
        delete.setParameter(1, valor);
        delete.executeUpdate();
        String jpql = "SELECT b FROM Bolsa b WHERE b.valor = ?1";
        TypedQuery<Bolsa> query = em.createQuery(jpql, Bolsa.class);
        query.setParameter(1, valor);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Bolsa bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
