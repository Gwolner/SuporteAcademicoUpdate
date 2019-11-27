package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Fardamento;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class FardamentoSelectTest extends GenericTest{
    
    /*############################ NAMEDQUERY ############################*/
    
    //Uso do NAMEDQUERY Fardamento.QUANTIDADE_ENTREGUE_POR_ID
    @Test
    public void quantidadeEntreguePorId() {
        logger.info("Executando quantidadeEntreguePorId()");
        TypedQuery<Fardamento> query = em.createNamedQuery(
                Fardamento.QUANTIDADE_ENTREGUE_POR_ID, Fardamento.class
        );
        query.setParameter(1, 4);
        List<Fardamento> fardamento = query.getResultList();

        assertEquals(1, fardamento.size());       
    }
    
    //Uso do NAMEDQUERY Fardamento.FARDAMENTOS
    @Test
    public void situacaoPorDescricao() {
        logger.info("Executando situacaoPorDescricao()");
        TypedQuery<Fardamento> query = em.createNamedQuery(
                Fardamento.FARDAMENTOS, Fardamento.class
        );
        List<Fardamento> fardamento = query.getResultList();

        assertEquals(6, fardamento.size());        
    }    
    
    /*########################### CREATEQUERY ############################*/
    
    //Uso do SUM 
    @Test
    public void somatorioFardamentoEntregue() {
        logger.info("Executando somatorioFardamentoEntregue()");
        TypedQuery<Long> query = em.createQuery(
                "SELECT SUM(f.quantidadeEntregue) FROM Fardamento f ", 
                Long.class
        );
        
        long resultado = query.getSingleResult();
        
        assertEquals(6, resultado);
    }
    
    //Uso do AVG 
    @Test
    public void mediaFardamentoEntregue() {
        logger.info("Executando mediaFardamentoEntregue()");
        TypedQuery<Double> query = em.createQuery(
                "SELECT AVG(f.quantidadeEntregue) FROM Fardamento f ", 
                Double.class
        );
        
        double resultado = query.getSingleResult();
  
        assertEquals(1.0, resultado, 0.01); //(expected, actual, delta)
    }
    
}
