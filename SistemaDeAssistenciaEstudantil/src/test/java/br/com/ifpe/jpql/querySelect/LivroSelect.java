package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Livro;
import br.com.ifpe.teste.GenericTest;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class LivroSelect extends GenericTest{
 
    //Uso de SELECT ANINHADO
    @Test
    public void livrosVolUnico() {
        logger.info("Executando livrosVolUnico()");
        TypedQuery<Livro> query;
        query = em.createQuery(
                "SELECT l FROM Livro l WHERE l.volume IN("
                        + "SELECT v "
                        + "FROM Volume v "
                        + "WHERE v.descricaoVolume = :param "
                + ")",
                Livro.class);
        query.setParameter("param", "Vol. Unico");
        List<Livro> itens = query.getResultList();
        assertNotNull(itens);

        for (Livro item : itens) {
            assertThat(item.getVolume().getDescricaoVolume(),CoreMatchers.anyOf(
                    startsWith("Vol. Unico")));
        }
        
        assertEquals(4, itens.size());
    }
    
    //Uso do IN (1,2)
    @Test
    public void livrosDePortuguesEMatematica() {
        logger.info("Executando livrosDePortuguesEMatematica()");
        TypedQuery<Livro> query;
        query = em.createQuery(
                "SELECT l FROM Livro l "
                + "WHERE l.materia IN ('Portugues', 'Matematica')",
                Livro.class);
        List<Livro> livros = query.getResultList();

        for (Livro livro : livros) {
            assertThat(livro.getMateria(),
                    CoreMatchers.anyOf(
                            startsWith("Portugues"),
                            startsWith("Matematica")));
        }

        assertEquals(3, livros.size());
    }
    
    //Uso do SETMAXRESULTS
    @Test
    public void livrosEmprestados() {
        logger.info("Executando livrosEmprestados()");
        TypedQuery<Emprestimo> query;
        query = em.createQuery(
                "SELECT e FROM Emprestimo e WHERE e.status like ?1",
                Emprestimo.class);
        query.setParameter(1, "Emprestado"); //Setando parâmetro posicional.
        query.setMaxResults(20); //Determinando quantidade máxima de resultados.
        List<Emprestimo> emprestimos = query.getResultList();

        for (Emprestimo emprestimo : emprestimos) {
            assertEquals("Emprestado", emprestimo.getStatus());
        }

        assertEquals(17, emprestimos.size());
    }
}
