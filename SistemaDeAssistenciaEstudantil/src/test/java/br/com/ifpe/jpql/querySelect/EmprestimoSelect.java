/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.teste.GenericTest;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author wolner
 */
public class EmprestimoSelect extends GenericTest{
    
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
