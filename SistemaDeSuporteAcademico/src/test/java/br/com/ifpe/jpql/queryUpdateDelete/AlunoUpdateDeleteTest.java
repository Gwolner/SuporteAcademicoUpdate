package br.com.ifpe.jpql.queryUpdateDelete;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Tamanho;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class AlunoUpdateDeleteTest extends GenericTest{
    
    @Test
    public void queryUpdate() {
        logger.info("Executando queryUpdate()");
        String matricula = "1234ABCD";
        String curso = "Eletrotecnica";
        Query update = em.createQuery("UPDATE Aluno AS a SET a.curso = ?1 WHERE a.matricula = ?2");
        update.setParameter(1, curso);
        update.setParameter(2, matricula);
        update.executeUpdate();
        String jpql = "SELECT a FROM Aluno a WHERE a.matricula = :mat";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setParameter("mat", matricula);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Aluno aluno = query.getSingleResult();
        assertEquals(curso, aluno.getCurso());
    }
    
     @Test(expected = NoResultException.class)
    public void queryDelete() {
        logger.info("Executando queryDelete()");
        Long id = 10L;
        Query delete = em.createQuery("DELETE FROM Aluno AS a WHERE a.idUsuario = ?1");
        delete.setParameter(1, id);
        delete.executeUpdate();
        String jpql = "SELECT a FROM Aluno a WHERE a.idUsuario = ?1";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setParameter(1, id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Aluno bolsa = query.getSingleResult();
        assertNull(bolsa);
    }
}
