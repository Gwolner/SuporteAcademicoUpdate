package br.com.ifpe.teste;

import br.com.ifpe.modelo.Situacao;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;


public class SituacaoCrudTest extends GenericTest {
    
    @Test
    public void persistirSituacao() {
        Situacao situacao = criarSituacao();
        em.persist(situacao);
        em.flush();
        assertNotNull(situacao.getIdSituacao());
    }
    
    @Test
    public void atualizarSituacao() {
        String novaSituacao = "Nao entregue";    
        Long id = 1L;
        
        Situacao situacao = em.find(Situacao.class, id);
        situacao.setDescricaoSituacao(novaSituacao);        
        
        em.flush();
        
        String jpql = "SELECT c FROM Situacao c WHERE c.idSituacao = ?1";
        TypedQuery<Situacao> query = em.createQuery(jpql, Situacao.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        situacao = query.getSingleResult();
        
        assertEquals(novaSituacao, situacao.getDescricaoSituacao());  
    }
    
    @Test
    public void atualizarSituacaoMerge() {
        String novaSituacao = "Entregue";    
        Long id = 2L;
        
        Situacao situacao = em.find(Situacao.class, id);
        situacao.setDescricaoSituacao(novaSituacao);  
        
        em.clear();
        em.merge(situacao);
        
        Map<String, Object> properties = new HashMap<>();        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);        
        situacao = em.find(Situacao.class, id, properties);
        
        assertEquals(novaSituacao, situacao.getDescricaoSituacao());
    }
    
    @Test
    public void removerSituacao() {
        Situacao situacao = em.find(Situacao.class, 3L);
        em.remove(situacao);
        situacao = em.find(Situacao.class, 3L);
        assertNull(situacao);
    }
    
    
}
