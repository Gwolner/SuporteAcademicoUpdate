package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Fardamento;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class FardamentoUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        Long id = 6L;
        int status = 2;
        Query update = em.createQuery("UPDATE Fardamento AS f SET f.quantidadeEntregue = ?1 WHERE f.idFardamento = ?2");
        update.setParameter(1, status);
        update.setParameter(2, id);
        update.executeUpdate();
        String jpql = "SELECT f FROM Fardamento f WHERE f.idFardamento = :id";
        TypedQuery<Fardamento> query = em.createQuery(jpql, Fardamento.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Fardamento fard = query.getSingleResult();
        assertEquals(status, fard.getQuantidadeEntregue());
    }
    
    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        Long id = 2L;
        Query delete = em.createQuery("DELETE FROM Fardamento AS f WHERE f.idFardamento = ?1");
        delete.setParameter(1, id);
        delete.executeUpdate();
        String jpql = "SELECT f FROM Fardamento f WHERE f.idFardamento = ?1";
        TypedQuery<Fardamento> query = em.createQuery(jpql, Fardamento.class);
        query.setParameter(1, id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Fardamento bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
