package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Situacao;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class SituacaoUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        Long id = 1L;
        String param = "Não entr.";
        Query update = em.createQuery("UPDATE Situacao AS s SET s.descricaoSituacao = ?1 WHERE s.idSituacao = ?2");
        update.setParameter(1, param);
        update.setParameter(2, id);
        update.executeUpdate();
        String jpql = "SELECT s FROM Situacao s WHERE s.idSituacao = :id";
        TypedQuery<Situacao> query = em.createQuery(jpql, Situacao.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Situacao tamanho = query.getSingleResult();
        assertEquals(param, tamanho.getDescricaoSituacao());
    }
    
    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        String descricao = "Rasgado";
        Query delete = em.createQuery("DELETE FROM Situacao AS s WHERE s.descricaoSituacao = ?1");
        delete.setParameter(1, descricao);
        delete.executeUpdate();
        String jpql = "SELECT s FROM Situacao s WHERE s.descricaoSituacao = ?1";
        TypedQuery<Situacao> query = em.createQuery(jpql, Situacao.class);
        query.setParameter(1, descricao);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Situacao bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
