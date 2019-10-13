package br.com.ifpe.validacao;

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

public class VolumeValidation extends ValidationGenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirVolumeInvalido() {
        Volume volume = criarVolumeInvalido();
        try {
            em.persist(volume);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(                               
                                startsWith("A descrição do volume não pode ser vazia.")
                        )
                );
            }

            assertEquals(1, constraintViolations.size());
            assertNull(volume.getIdVolume());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarVolumeInvalido() {
        TypedQuery<Volume> query = em.createQuery(
                "SELECT v FROM Volume v "
                + "WHERE v.idVolume "
                + "LIKE :idVolume",
                Volume.class
        );
        query.setParameter("idVolume", 1);
        Volume volume = query.getSingleResult();
        volume.setDescricaoVolume(" ");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = 
                    ex.getConstraintViolations().iterator().next();
            assertEquals(
                    "A descrição do volume não pode ser vazia.",
                    violation.getMessage()
            );
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
