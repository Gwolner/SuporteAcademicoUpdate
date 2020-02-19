package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Emprestimo;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class EmprestimoUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        Long id = 10L;
        String status = "Devolvido";
        Query update = em.createQuery("UPDATE Emprestimo AS e SET e.status = ?1 WHERE e.idEmprestimo = ?2");
        update.setParameter(1, status);
        update.setParameter(2, id);
        update.executeUpdate();
        String jpql = "SELECT e FROM Emprestimo e WHERE e.idEmprestimo = :id";
        TypedQuery<Emprestimo> query = em.createQuery(jpql, Emprestimo.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Emprestimo prof = query.getSingleResult();
        assertEquals(status, prof.getStatus());
    }
    
    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        Long id = 11L;
        Query delete = em.createQuery("DELETE FROM Emprestimo AS e WHERE e.idEmprestimo = ?1");
        delete.setParameter(1, id);
        delete.executeUpdate();
        String jpql = "SELECT e FROM Emprestimo e WHERE e.idEmprestimo = ?1";
        TypedQuery<Emprestimo> query = em.createQuery(jpql, Emprestimo.class);
        query.setParameter(1, id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Emprestimo bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
