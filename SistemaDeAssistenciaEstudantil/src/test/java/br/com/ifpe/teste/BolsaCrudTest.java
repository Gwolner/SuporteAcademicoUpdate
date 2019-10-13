package br.com.ifpe.teste;


import br.com.ifpe.modelo.Bolsa;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;

public class BolsaCrudTest extends GenericTest{
    @Test
    public void persistirBolsa() {
        Bolsa bolsa = criarBolsa();        
        em.persist(bolsa);
        em.flush();
        assertNotNull(bolsa.getIdBolsa());
    }
    
    @Test
    public void atualizarBolsa() {
        String novoNome = "Monitor Tecnico";
        String novoTipo ="Monitoria";
        Double novoValor = 320.0;
        Long id = 6L;

        Bolsa bolsa = em.find(Bolsa.class, id);
        bolsa.setNomeBolsa(novoNome);
        bolsa.setTipo(novoTipo);
        bolsa.setValor(novoValor);
        
        em.flush();
        
        String jpql = "SELECT c FROM Bolsa c WHERE c.idBolsa = ?1";
        TypedQuery<Bolsa> query = em.createQuery(jpql, Bolsa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        bolsa = query.getSingleResult();

        assertEquals(novoNome, bolsa.getNomeBolsa());      
        assertEquals(novoTipo, bolsa.getTipo());   
        assertEquals(novoValor, bolsa.getValor());  
    }
    
    @Test
    public void atualizarBolsaMerge() {
        String novoNome = "Estagiario Superior";
        String novoTipo ="Estagio";
        Double novoValor = 496.00;
        Long id = 12L;

        Bolsa bolsa = em.find(Bolsa.class, id);
        bolsa.setNomeBolsa(novoNome);
        bolsa.setTipo(novoTipo);
        bolsa.setValor(novoValor);
        
        em.clear();
        em.merge(bolsa);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        bolsa = em.find(Bolsa.class, id, properties);

        assertEquals(novoNome, bolsa.getNomeBolsa());      
        assertEquals(novoTipo, bolsa.getTipo());
        assertEquals(novoValor, bolsa.getValor()); 
    }
    
    @Test
    public void removerBolsa() {
        Bolsa bolsa = em.find(Bolsa.class, 15L);
        em.remove(bolsa);
        bolsa = em.find(Bolsa.class, 15L);
        assertNull(bolsa);
    }
    
    
    
}
