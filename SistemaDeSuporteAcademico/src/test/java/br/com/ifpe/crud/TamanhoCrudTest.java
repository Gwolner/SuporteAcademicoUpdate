package br.com.ifpe.crud;

import br.com.ifpe.modelo.Tamanho;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;


public class TamanhoCrudTest extends GenericTest {
        
    @Test
    public void persistirTamanho() {
        Tamanho tamanho = criarTamanho();
        em.persist(tamanho);
        em.flush();
        assertNotNull(tamanho.getIdTamanho());
    }
    
    @Test
    public void atualizarTamanho() {
        String novoTamanho = "M";        
        Long id = 3L;        

        Tamanho tamanho = em.find(Tamanho.class, id);
        tamanho.setDescricaoTamanho(novoTamanho);

        em.flush();
        
        String jpql = "SELECT c FROM Tamanho c WHERE c.idTamanho = ?1";
        TypedQuery<Tamanho> query = em.createQuery(jpql, Tamanho.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        tamanho = query.getSingleResult();
        
        assertEquals(novoTamanho, tamanho.getDescricaoTamanho());  
    }
    
    @Test
    public void atualizarTamanhoMerge() {
        String novoTamanho = "G";        
        Long id = 4L;        

        Tamanho tamanho = em.find(Tamanho.class, id);
        tamanho.setDescricaoTamanho(novoTamanho); 

        em.clear();
        em.merge(tamanho);

        Map<String, Object> properties = new HashMap<>();        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);

        assertEquals(novoTamanho, tamanho.getDescricaoTamanho());
    }
    
    @Test
    public void removerTamanho() {
        Tamanho tamanho = em.find(Tamanho.class, 5L);
        em.remove(tamanho);
        tamanho = em.find(Tamanho.class, 5L);
        assertNull(tamanho);
    }
    
    
}
