package br.com.ifpe.teste;


import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Emprestimo;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;


public class EmprestimoCrudTest extends GenericTest{

    @Test
    public void persistirEmprestimo() {
        
        Emprestimo emprestimo = criarEmprestimo();
        em.persist(emprestimo);
        em.flush();
        assertNotNull(emprestimo.getIdEmprestimo());
    }
    
    @Test
    public void atualizarEmprestimo() {        
        String novoStatus = "Emprestado";
        Long id = 1L;
        
        Emprestimo emprestimo = em.find(Emprestimo.class, id);
        emprestimo.setStatus(novoStatus);
        
        em.flush();
        
        String jpql = "SELECT c FROM Emprestimo c WHERE c.idEmprestimo = ?1";
        TypedQuery<Emprestimo> query = em.createQuery(jpql, Emprestimo.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        emprestimo = query.getSingleResult();

        assertEquals(novoStatus, emprestimo.getStatus());      
    }
    
    @Test
    public void atualizarEmprestimoMerge() {
        String novoStatus = "Emprestado";
        Long id = 6L;
        
        Emprestimo emprestimo = em.find(Emprestimo.class, id);
        emprestimo.setStatus(novoStatus);
        
        em.clear();
        em.merge(emprestimo);
        
        Map<String, Object> properties = new HashMap<>();        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);   
        emprestimo = em.find(Emprestimo.class, id, properties);
        
        assertEquals(novoStatus, emprestimo.getStatus());
    }
    
    @Test
    public void removerEmprestimo() {
        Emprestimo emprestimo = em.find(Emprestimo.class, 11L);
        em.remove(emprestimo);
        emprestimo = em.find(Emprestimo.class, 11L);
        assertNull(emprestimo);
    }

    

    
    
    
}
