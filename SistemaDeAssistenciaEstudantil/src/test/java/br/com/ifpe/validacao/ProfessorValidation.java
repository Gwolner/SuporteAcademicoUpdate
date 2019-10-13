package br.com.ifpe.validacao;

import br.com.ifpe.modelo.Livro;
import br.com.ifpe.modelo.Professor;
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


public class ProfessorValidation extends ValidationGenericTest {
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirProfessorInvalido() {
        Professor professor = criarProfessorInvalido();
        try {
            em.persist(professor);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(                               
                                startsWith("CPF com formato inválido. Deve ser XXX.XXX.XXX-XX."),
                                startsWith("Nome do usuario não pode estar vazio."),                                
                                startsWith("A data deve ser de tempo passado."),                                
                                startsWith("Departamento não pode estar vazio.")
                        )
                );
            }

            assertEquals(4, constraintViolations.size());
            assertNull(professor.getIdUsuario());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarLivroInvalido() {
        TypedQuery<Professor> query = em.createQuery(
                "SELECT v FROM Professor v "
                + "WHERE v.siape "
                + "LIKE :siape",
                Professor.class
        );
        query.setParameter("siape", 9530198);
        Professor professor = query.getSingleResult();
        professor.setDepartamento(" ");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = 
                    ex.getConstraintViolations().iterator().next();
            assertEquals("Departamento não pode estar vazio.",violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
