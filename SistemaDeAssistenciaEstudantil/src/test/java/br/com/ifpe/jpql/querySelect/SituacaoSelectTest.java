package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.crud.GenericTest;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class SituacaoSelectTest extends GenericTest{
    
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
