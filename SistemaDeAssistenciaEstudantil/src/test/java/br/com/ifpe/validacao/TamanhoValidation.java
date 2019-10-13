package br.com.ifpe.validacao;

import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.modelo.Volume;
import java.util.Calendar;
import java.util.GregorianCalendar;
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


public class TamanhoValidation extends ValidationGenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirTamanhoInvalido() {
        Tamanho tamanho = criarTamanhoInvalido();
        try {
            em.persist(tamanho);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(                               
                                startsWith("Descrição de tamanho fora do formato da Regex."),
                                startsWith("O tamanho não pode ser vazio.")
                        )
                );
            }

            assertEquals(1, constraintViolations.size());
            assertNull(tamanho.getIdTamanho());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarTamanhoInvalido() {
        TypedQuery<Tamanho> query = em.createQuery(
                "SELECT v FROM Tamanho v "
                + "WHERE v.idTamanho "
                + "LIKE :idTamanho",
                Tamanho.class
        );
        query.setParameter("idTamanho", 1);
        Tamanho tamanho = query.getSingleResult();
        tamanho.setDescricaoTamanho("XXGG");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = 
                    ex.getConstraintViolations().iterator().next();
            assertEquals(
                    "Descrição de tamanho fora do formato da Regex.",
                    violation.getMessage()
            );
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
