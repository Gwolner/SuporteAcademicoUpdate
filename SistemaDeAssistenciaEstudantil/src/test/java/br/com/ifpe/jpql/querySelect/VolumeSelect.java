/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Volume;
import br.com.ifpe.teste.GenericTest;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author wolner
 */
public class VolumeSelect extends GenericTest{

    //Uso de ORDER BY DESC
    @Test
    public void ordenacaoVolumes() {
        logger.info("Executando ordenacaoVolumes()");
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
