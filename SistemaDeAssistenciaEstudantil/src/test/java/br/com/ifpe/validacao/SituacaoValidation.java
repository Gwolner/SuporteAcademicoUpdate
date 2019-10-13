/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.validacao;

import br.com.ifpe.modelo.Professor;
import br.com.ifpe.modelo.Situacao;
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
public class SituacaoValidation extends ValidationGenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirSituacaoInvalida() {
        Situacao situacao = criarSituacaoInvalida();
        try {
            em.persist(situacao);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(                               
                                startsWith("A descricao está fora do tamanho suportado."),
                                startsWith("A descricao não pode ser vazia.")
                        )
                );
            }

            assertEquals(1, constraintViolations.size());
            assertNull(situacao.getIdSituacao());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarSituacaoInvalido() {
        TypedQuery<Situacao> query = em.createQuery(
                "SELECT v FROM Situacao v "
                + "WHERE v.idSituacao "
                + "LIKE :idSituacao",
                Situacao.class
        );
        query.setParameter("idSituacao", 1);
        Situacao situacao = query.getSingleResult();
        situacao.setDescricaoSituacao("Meu nome é Cristtovão");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = 
                    ex.getConstraintViolations().iterator().next();
            assertEquals(
                    "A descricao está fora do tamanho suportado.",
                    violation.getMessage()
            );
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
