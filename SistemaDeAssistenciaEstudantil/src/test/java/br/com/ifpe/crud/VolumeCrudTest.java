package br.com.ifpe.crud;

import br.com.ifpe.modelo.Volume;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;


public class VolumeCrudTest extends GenericTest{
    
    @Test
    public void persistirVolume(){        
        Volume volume = criarVolume();
        em.persist(volume);
        em.flush();
        assertNotNull(volume.getIdVolume());
    }
    
    @Test
    public void atualizarVolume() {
        String novoVolume = "Vol. 2";        
        Long id = 2L;        

        Volume volume = em.find(Volume.class, id);
        volume.setDescricaoVolume(novoVolume);

        em.flush();
        
        String jpql = "SELECT c FROM Volume c WHERE c.idVolume = ?1";
        TypedQuery<Volume> query = em.createQuery(jpql, Volume.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        volume = query.getSingleResult();
        
        assertEquals(novoVolume, volume.getDescricaoVolume());  
    }
    
    @Test
    public void atualizarVolumeMerge() {
        String novoVolume = "Vol. 3";        
        Long id = 3L;        

        Volume volume = em.find(Volume.class, id);
        volume.setDescricaoVolume(novoVolume);

        em.clear();
        em.merge(volume);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        volume = em.find(Volume.class, id, properties);
        
        assertEquals(novoVolume, volume.getDescricaoVolume());
    }
    
    @Test
    public void removerVolume() {
        Volume volume = em.find(Volume.class, 5L);
        em.remove(volume);
        volume = em.find(Volume.class, 5L);
        assertNull(volume);
    }
    
    
}
