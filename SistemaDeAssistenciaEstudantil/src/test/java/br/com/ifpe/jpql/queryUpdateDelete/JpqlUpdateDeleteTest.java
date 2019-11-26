package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.modelo.Bolsa;
import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.teste.GenericTest;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

public class JpqlUpdateDeleteTest extends GenericTest {

    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        Long id = 1L;
        //atualmente é XG, vamos atualizar para gg no nosso teste.
        String param = "GG";
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
        logger.info(tamanho.getDescricaoTamanho());
    }

    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        Long id = (long) 2;
        //Apagando uma bolsa
        Query delete = em.createQuery("DELETE FROM Bolsa AS b WHERE b.idBolsa = ?1");
        delete.setParameter(1, id);
        delete.executeUpdate();
        String jpql = "SELECT b FROM Bolsa b WHERE b.idBolsa = ?1";
        TypedQuery<Bolsa> query = em.createQuery(jpql, Bolsa.class);
        query.setParameter(1, id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Bolsa bolsa = query.getSingleResult();
        assertNull(bolsa);
    }

}
