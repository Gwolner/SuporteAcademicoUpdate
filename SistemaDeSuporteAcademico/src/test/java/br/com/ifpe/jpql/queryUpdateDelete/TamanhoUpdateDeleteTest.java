package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.modelo.Tamanho;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class TamanhoUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        Long id = 1L;
        String param = "PP";
        Query update = em.createQuery("UPDATE Tamanho AS t SET t.descricaoTamanho = ?1 WHERE t.idTamanho = ?2");
        update.setParameter(1, param);
        update.setParameter(2, id);
        update.executeUpdate();
        String jpql = "SELECT t FROM Tamanho t WHERE t.idTamanho = :id";
        TypedQuery<Tamanho> query = em.createQuery(jpql, Tamanho.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Tamanho tamanho = query.getSingleResult();
        assertEquals(param, tamanho.getDescricaoTamanho());
    }
    
    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        String tamanho = "XG";
        Query delete = em.createQuery("DELETE FROM Tamanho AS t WHERE t.descricaoTamanho = ?1");
        delete.setParameter(1, tamanho);
        delete.executeUpdate();
        String jpql = "SELECT t FROM Tamanho t WHERE t.descricaoTamanho = ?1";
        TypedQuery<Tamanho> query = em.createQuery(jpql, Tamanho.class);
        query.setParameter(1, tamanho);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Tamanho bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
