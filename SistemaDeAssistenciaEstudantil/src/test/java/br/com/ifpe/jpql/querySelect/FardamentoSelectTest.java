package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.crud.GenericTest;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class FardamentoSelectTest extends GenericTest{
    
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
