package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Situacao;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class SituacaoSelectTest extends GenericTest{
    
    /*############################ NAMEDQUERY ############################*/
    
    //Uso do NAMEDQUERY Situacao.SITUACAO_POR_DESCRICAO
    @Test
    public void situacaoPorDescricao() {
        logger.info("Executando situacaoPorDescricao()");
        TypedQuery<Situacao> query = em.createNamedQuery(
                Situacao.SITUACAO_POR_DESCRICAO, Situacao.class
        );
        query.setParameter("descricao", "%ent%");
        List<Situacao> volumes = query.getResultList();

        assertEquals(2, volumes.size());
    }
    
    /*########################### CREATEQUERY ############################*/
    
    //Uso de ORDER BY ASC
    @Test
    public void ordenacaoAscSituacao() {
        logger.info("Executando ordenacaoAscSituacao()");
        TypedQuery<Situacao> query;
        query = em.createQuery(
                "SELECT s FROM Situacao s ORDER BY s.descricaoSituacao ASC",
                Situacao.class);
        List<Situacao> situacao = query.getResultList();
        assertEquals(3, situacao.size());
        
        assertEquals("Entr.", situacao.get(0).getDescricaoSituacao());        
        assertEquals("Nao entr.", situacao.get(1).getDescricaoSituacao());
        assertEquals("Rasgado", situacao.get(2).getDescricaoSituacao());
 
    }
}
