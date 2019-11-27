package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Livro;
import br.com.ifpe.modelo.Livro;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class LivroUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        Long isbn = 9781478253236L;
        String autor = "Edmario SebRoso";
        Query update = em.createQuery("UPDATE Livro AS e SET e.autor = ?1 WHERE e.isbn = ?2");
        update.setParameter(1, autor);
        update.setParameter(2, isbn);
        update.executeUpdate();
        String jpql = "SELECT e FROM Livro e WHERE e.isbn = :isbn";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setParameter("isbn", isbn);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Livro fard = query.getSingleResult();
        assertEquals(autor, fard.getAutor());
    }
    
    @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        Long id = 7L;
        Query delete = em.createQuery("DELETE FROM Livro AS l WHERE l.idLivro = ?1");
        delete.setParameter(1, id);
        delete.executeUpdate();
        String jpql = "SELECT l FROM Livro l WHERE l.idLivro = ?1";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setParameter(1, id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Livro bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
