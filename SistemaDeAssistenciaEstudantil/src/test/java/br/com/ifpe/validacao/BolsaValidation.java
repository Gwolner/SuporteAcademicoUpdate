/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.validacao;

import br.com.ifpe.modelo.Bolsa;
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
public class BolsaValidation extends ValidationGenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirBolsaInvalido() {
        Bolsa bolsa = criarBolsaInvalida();
        try {
            em.persist(bolsa);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(                               
                                startsWith("O nome da bolsa não foi informado."),
                                startsWith("O tipo da bolsa não foi informado.")
                        )
                );
            }

            assertEquals(2, constraintViolations.size());
            assertNull(bolsa.getIdBolsa());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarBolsaInvalido() {
        TypedQuery<Bolsa> query = em.createQuery(
                "SELECT v FROM Bolsa v WHERE v.idBolsa like :idBolsa",
                Bolsa.class
        );
        query.setParameter("idBolsa", 1);
        Bolsa bolsa = query.getSingleResult();
        bolsa.setNomeBolsa(" ");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = 
                    ex.getConstraintViolations().iterator().next();
            assertEquals("O nome da bolsa não foi informado.", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
