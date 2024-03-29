package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Volume;
import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Volume;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class VolumeSelectTest extends GenericTest{

    /*############################ NAMEDQUERY ############################*/
    
    //Uso do NAMEDQUERY Volume.VOLUME_POR_DESCRICAO
    @Test
    public void volumePorDescricao() {
        logger.info("Executando volumePorDescricao()");
        TypedQuery<Volume> query = em.createNamedQuery(
                Volume.VOLUME_POR_DESCRICAO, Volume.class
        );
        query.setParameter("descricao", "V%");
        List<Volume> volumes = query.getResultList();

        for (Volume tam : volumes) {
            assertTrue(tam.getDescricaoVolume().startsWith("V"));
        }

        assertEquals(4, volumes.size());
    }
    
    /*########################### CREATEQUERY ############################*/
    
    //Uso de ORDER BY DESC
    @Test
    public void ordenacaoDescVolumes() {
        logger.info("Executando ordenacaoDescVolumes()");
        TypedQuery<Volume> query;
        query = em.createQuery(
                "SELECT v FROM Volume v ORDER BY v.descricaoVolume DESC",
                Volume.class);
        List<Volume> volumes = query.getResultList();
        assertEquals(5, volumes.size());
        
        //O primeiro é Volume 3, e o último é edição rara.
        assertEquals("Volume 3", volumes.get(0).getDescricaoVolume());        
        assertEquals("Volume 2", volumes.get(1).getDescricaoVolume());
        assertEquals("Vol. Unico", volumes.get(2).getDescricaoVolume());
        assertEquals("Vol. 1", volumes.get(3).getDescricaoVolume());        
        assertEquals("Edição rara", volumes.get(4).getDescricaoVolume());
    }

    //Uso do NOT NULL
    @Test
    public void volumesNaoNulos() {
        logger.info("Executando volumesNaoNulos()");
        Query query = em.createQuery(
                "SELECT v FROM Volume v WHERE v.descricaoVolume IS NOT NULL",
                Volume.class
        );
        List<Volume> volumes = query.getResultList();

        for (Volume volume : volumes) {
            assertNotNull(volume.getDescricaoVolume());
        }
        
        assertEquals(5, volumes.size());
    }
}
