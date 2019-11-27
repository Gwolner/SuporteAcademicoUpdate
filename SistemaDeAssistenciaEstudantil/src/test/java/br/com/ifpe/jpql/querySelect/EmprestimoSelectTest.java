package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Emprestimo;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class EmprestimoSelectTest extends GenericTest{
    
    /*############################ NAMEDQUERY ############################*/
    
    //Uso do NAMEDQUERY Emprestimo.STATUS_POR_ID
    @Test
    public void quantidadeEntreguePorId() {
        logger.info("Executando quantidadeEntreguePorId()");
        TypedQuery<Emprestimo> query = em.createNamedQuery(
                Emprestimo.STATUS_POR_ID, Emprestimo.class
        );
        query.setParameter(1, 1);
        List<Emprestimo> emprestimo = query.getResultList();

        assertEquals("Atrasado", emprestimo.get(0).getStatus());      
    }
    
    //Uso do NAMEDQUERY Emprestimo.STATUS_DOS_EMPRESTIMOS
    @Test
    public void situacaoPorDescricao() {
        logger.info("Executando situacaoPorDescricao()");
        TypedQuery<Emprestimo> query = em.createNamedQuery(
                Emprestimo.STATUS_DOS_EMPRESTIMOS, Emprestimo.class
        );
        List<Emprestimo> emprestimo = query.getResultList();

        assertEquals(20, emprestimo.size());        
    }

    /*########################### CREATEQUERY ############################*/
    
    //Uso do LIKE
    @Test
    public void livrosAtrasoOuRepetido() {
        logger.info("Executando livrosAtrasoOuRepetido()");
        TypedQuery<Emprestimo> query;
        query = em.createQuery(
                "SELECT e FROM Emprestimo e "
                + "WHERE e.status = ?1 "
                + "OR e.status = ?2",
                Emprestimo.class);
        query.setParameter(1, "Atrasado"); 
        query.setParameter(2, "Repetido");        
        List<Emprestimo> emprestimos = query.getResultList();

        for (Emprestimo emprestimo : emprestimos) {
            assertTrue("Atrasado".equals(emprestimo.getStatus()) ||
                       "Repetido".equals(emprestimo.getStatus()));
        }
        
        assertEquals(3, emprestimos.size());
    }
}
