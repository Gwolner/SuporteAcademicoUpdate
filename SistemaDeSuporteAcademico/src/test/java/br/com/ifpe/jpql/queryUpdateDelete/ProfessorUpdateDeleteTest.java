package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Professor;
import br.com.ifpe.modelo.Professor;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class ProfessorUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        int siape = 9534198;
        Long ramal = 21159L;
        Query update = em.createQuery("UPDATE Professor AS p SET p.ramal = ?1 WHERE p.siape = ?2");
        update.setParameter(1, ramal);
        update.setParameter(2, siape);
        update.executeUpdate();
        String jpql = "SELECT p FROM Professor p WHERE p.siape = :siape";
        TypedQuery<Professor> query = em.createQuery(jpql, Professor.class);
        query.setParameter("siape", siape);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Professor prof = query.getSingleResult();
        assertEquals(ramal, prof.getRamal());
    }
    
    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        Long id = 11L;
        Query delete = em.createQuery("DELETE FROM Professor AS p WHERE p.idUsuario = ?1");
        delete.setParameter(1, id);
        delete.executeUpdate();
        String jpql = "SELECT p FROM Professor p WHERE p.idUsuario = ?1";
        TypedQuery<Professor> query = em.createQuery(jpql, Professor.class);
        query.setParameter(1, id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Professor bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
