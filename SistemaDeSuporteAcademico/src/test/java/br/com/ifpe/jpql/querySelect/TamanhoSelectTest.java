package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.crud.GenericTest;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class TamanhoSelectTest extends GenericTest{
    
    /*############################ NAMEDQUERY ############################*/
    
    //Uso do NAMEDQUERY Tamanho.POR_LETRA
    @Test
    public void tamanhosIniciandoComP() {
        logger.info("Executando tamanhosIniciandoComP()");
        TypedQuery<Tamanho> query = em.createNamedQuery(
                Tamanho.POR_LETRA, Tamanho.class
        );
        query.setParameter("param", "P%");
        List<Tamanho> tamanhos = query.getResultList();

        for (Tamanho tam : tamanhos) {
            assertTrue(tam.getDescricaoTamanho().startsWith("P"));
        }

        assertEquals(2, tamanhos.size());
    }    
    
    /*########################### CREATEQUERY ############################*/
    
    //Uso de SELECT DISTINCT
    @Test
    public void tamanhosDistintos() {
        logger.info("Executando tamanhosDistintos()");
        TypedQuery<String> query
                = em.createQuery("SELECT DISTINCT(t.descricaoTamanho) "
                        + "FROM Tamanho t "
                        + "ORDER BY t.descricaoTamanho", String.class
                );
        List<String> tamanhos = query.getResultList();
        
        for (String tamanho : tamanhos) {
            assertTrue("PP".equals(tamanho)||
                       "P".equals(tamanho) ||
                       "m".equals(tamanho) ||
                       "g".equals(tamanho) ||
                       "XG".equals(tamanho)
            );
        }
        
        assertEquals(5, tamanhos.size());
    }
}
