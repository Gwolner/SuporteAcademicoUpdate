package br.com.ifpe.validacao;

import br.com.ifpe.modelo.Aluno;
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

public class AlunoValidationTest extends ValidationGenericTest {
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirAlunoInvalido() {
        Aluno aluno = criarAlunoInvalido();
        try {
            em.persist(aluno);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(                               
                                startsWith("CPF com formato inválido. Deve ser XXX.XXX.XXX-XX."),
                                startsWith("Nome do usuario não pode estar vazio."),                                
                                startsWith("A data deve ser de tempo passado."),                                
                                startsWith("Curso não pode estar vazio."),                                
                                startsWith("Matricula inválida."),
                                startsWith("Responsável não pode estar vazio."),
                                startsWith("Turno não pode estar vazio."),
                                startsWith("O email informado não está correto.")
                        )
                );
            }

            assertEquals(8, constraintViolations.size());
            assertNull(aluno.getIdUsuario());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarAlunoInvalido() {
        TypedQuery<Aluno> query = em.createQuery(
                "SELECT v FROM Aluno v WHERE v.matricula like :matricula",
                Aluno.class
        );
        query.setParameter("matricula", "1234ABCD");
        Aluno aluno = query.getSingleResult();
        aluno.setCpf("00011100022");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = 
                    ex.getConstraintViolations().iterator().next();
            assertEquals(
                    "CPF com formato inválido. Deve ser XXX.XXX.XXX-XX.",
                    violation.getMessage()
            );
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
