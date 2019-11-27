package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Bolsa;
import br.com.ifpe.crud.GenericTest;
import java.util.List;
import javax.persistence.Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class BolsaSelectTest extends GenericTest{
    
    
    //Uso do MIN e MAX
    @Test
    public void valorMaximoEMinimoDeBolsa() {
        logger.info("Executando valorMaximoEMinimoDeBolsa()");
        Query query = em.createQuery(
                "SELECT MAX(b.valor), MIN(b.valor) FROM Bolsa b"
        );
        Object[] bolsas = (Object[]) query.getSingleResult();

        assertEquals(600.00, bolsas[0]);
        assertEquals(0.00, bolsas[1]);

    }    
    
    //Uso do WHERE BETWEEN
    @Test
    public void faixaDeValoresDeBolsa() {
        logger.info("Executando faixaDeValoresDeBolsa()");
        Query query = em.createQuery(
                "SELECT b FROM Bolsa b WHERE b.valor BETWEEN ?1 AND ?2",
                Bolsa.class
        );
        query.setParameter(1, 430.00);
        query.setParameter(2, 700.00);
        List<Bolsa> bolsas = query.getResultList();

        for (Bolsa bolsa : bolsas) {
            assertTrue(bolsa.getValor() >= 430.00 &&
                       bolsa.getValor() <= 700.00
            );
        }
        
        assertEquals(2, bolsas.size());
    }
}
