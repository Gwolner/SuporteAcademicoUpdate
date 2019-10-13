/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.validacao;

import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Livro;
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
public class LivroValidation extends ValidationGenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirVolumeInvalido() {
        Livro livro = criarLivroInvalido();
        try {
            em.persist(livro);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(                               
                                startsWith("A materia não foi informada."),
                                startsWith("Titulo não informado."),
                                startsWith("Autor não informado."),
                                startsWith("ISBN Inválido."),                                
                                startsWith("Nome da matéria acima do limite."),
                                startsWith("Nome do título acima do limite."),
                                startsWith("Nome do autor acima do limite.") 
                        )
                );
            }

            assertEquals(4, constraintViolations.size());
            assertNull(livro.getIdLivro());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarLivroInvalido() {
        TypedQuery<Livro> query = em.createQuery(
                "SELECT v FROM Livro v "
                + "WHERE v.idLivro "
                + "LIKE :idLivro",
                Livro.class
        );
        query.setParameter("idLivro", 1);
        Livro livro = query.getSingleResult();
        livro.setAutor(" ");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = 
                    ex.getConstraintViolations().iterator().next();
            assertEquals("Autor não informado.",violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
