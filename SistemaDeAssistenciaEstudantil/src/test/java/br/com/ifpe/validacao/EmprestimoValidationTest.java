/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.validacao;

import br.com.ifpe.modelo.Bolsa;
import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Volume;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Wolf
 */
public class EmprestimoValidationTest extends ValidationGenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirEmprestimoInvalido() {
        Emprestimo emprestimo = criarEmprestimoInvalido();
        try {
            em.persist(emprestimo);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("A data deve ser de tempo futuro."),
                                startsWith("Status não pode ser vazio.")
                        )
                );
            }

            assertEquals(2, constraintViolations.size());
            assertNull(emprestimo.getIdEmprestimo());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarEmprestimoInvalido() {
        TypedQuery<Emprestimo> query = em.createQuery(
                "SELECT v FROM Emprestimo v "
                + "WHERE v.idEmprestimo "
                + "LIKE :idEmprestimo",
                Emprestimo.class
        );
        query.setParameter("idEmprestimo", 1);
        Emprestimo emprestimo = query.getSingleResult();
        emprestimo.setStatus(" ");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = 
                    ex.getConstraintViolations().iterator().next();
            assertEquals("Status não pode ser vazio.",violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
    
}
