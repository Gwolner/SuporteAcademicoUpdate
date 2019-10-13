package br.com.ifpe.teste;


import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;


public class FardamentoCrudTest extends GenericTest {

    @Test
    public void persistirFardamento() {
        Fardamento fardamento = criarFardamento();      
        em.persist(fardamento);
        em.flush();
        assertNotNull(fardamento.getIdFardamento());
    }
    
    @Test
    public void atualizarFardamento() {
        int novaQuantidade = 2;
        Long id = 3L;
        
        Fardamento fardamento = em.find(Fardamento.class, id);
        fardamento.setQuantidadeEntregue(novaQuantidade);
        fardamento.setAluno(criarAluno());
        fardamento.setSituacao(criarSituacao());
        fardamento.setTamanho(criarTamanho());
        
        
        em.flush();
        
        String jpql = "SELECT c FROM Fardamento c WHERE c.idFardamento = ?1";
        TypedQuery<Fardamento> query = em.createQuery(jpql, Fardamento.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        fardamento = query.getSingleResult();

        assertEquals(novaQuantidade, fardamento.getQuantidadeEntregue());  
    }
    
    @Test
    public void atualizarFardamentoMerge() {
        int novaQuantidade = 2;
        Long id = 3L;
        
        Fardamento fardamento = em.find(Fardamento.class, id);
        fardamento.setQuantidadeEntregue(novaQuantidade);
        fardamento.setAluno(criarAluno());
        fardamento.setSituacao(criarSituacao());
        fardamento.setTamanho(criarTamanho());

        em.clear();
        em.merge(fardamento);

        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        fardamento = em.find(Fardamento.class, id, properties);
        
        assertEquals(novaQuantidade, fardamento.getQuantidadeEntregue());
    }
    
    @Test
    public void removerFardamento() {
        Fardamento fardamento = em.find(Fardamento.class, 2L);
        em.remove(fardamento);
        fardamento = em.find(Fardamento.class, 2L);
        assertNull(fardamento);
    }

    
}
