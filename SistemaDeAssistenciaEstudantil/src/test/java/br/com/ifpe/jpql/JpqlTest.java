package br.com.ifpe.jpql;

import br.com.ifpe.modelo.*;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hamcrest.CoreMatchers;
import br.com.ifpe.teste.GenericTest;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class JpqlTest extends GenericTest {

    //Uso do MIN e MAX
    @Test
    public void valorMaximoEMinimoDeBolsa() {
        logger.info("Executando valorMaximoEMinimoDeBolsa()");
        Query query = em.createQuery(
                "SELECT MAX(b.valor), MIN(b.valor) FROM Bolsa b"
        );
        Object[] bolsas = (Object[]) query.getSingleResult();

        assertEquals(600.00, bolsas[0]);
        assertEquals(0.00, bolsas[1]);

    }

    //Uso do WHERE BETWEEN
    @Test
    public void faixaDeValoresDeBolsa() {
        logger.info("Executando faixaDeValoresDeBolsa()");
        Query query = em.createQuery(
                "SELECT b FROM Bolsa b WHERE b.valor BETWEEN ?1 AND ?2",
                Bolsa.class
        );
        query.setParameter(1, 430.00);
        query.setParameter(2, 700.00);
        List<Bolsa> bolsas = query.getResultList();

        for (Bolsa bolsa : bolsas) {
            assertTrue(bolsa.getValor() >= 430.00 && bolsa.getValor() <= 700.00);
        }

    }

    
    //Uso do NOT NULL
    @Test
    public void volumesNaoNulos() {
        logger.info("Executando volumesNaoNulos()");
        Query query = em.createQuery(
                "SELECT v FROM Volume v WHERE v.descricaoVolume IS NOT NULL",
                Volume.class
        );
        List<Volume> volumes = query.getResultList();

        for (Volume volume : volumes) {
            assertNotNull(volume);
        }

    }
    //Uso do NAMEDQUERY (Checar classe para ler a Query!)
    @Test
    public void tamanhosIniciandoComP() {
        logger.info("Executando tamanhosIniciandoComP()");
        TypedQuery<Tamanho> query = em.createNamedQuery("Tamanho.PorLetra", Tamanho.class);
        query.setParameter("param", "P%");
        List<Tamanho> tamanhos = query.getResultList();

        for (Tamanho tam : tamanhos) {
            assertTrue(tam.getDescricaoTamanho().startsWith("P"));
        }

        //P e PP
        assertEquals(2, tamanhos.size());
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

        //assertEquals(17, emprestimos.size());
    }

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
        query.setParameter(1, "Atrasado"); //Setando parâmetro posicional.
        query.setParameter(2, "Repetido"); //Setando parâmetro posicional.        
        List<Emprestimo> emprestimos = query.getResultList();

        assertTrue(emprestimos != null);
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

    //Uso do WHERE SIZE
    @Test
    public void alunosComBolsas() {
        logger.info("Executando alunosComBolsas()");
        TypedQuery<Aluno> query;
        query = em.createQuery(
                "SELECT a FROM Aluno a WHERE SIZE(a.bolsas) >= ?1",
                Aluno.class);
        query.setParameter(1, 2);
        List<Aluno> alunos = query.getResultList();
        assertTrue(alunos.size() > 0);
    }

    //Uso de SELECT DISTINCT
    @Test
    public void tamanhosDistintos() {
        logger.info("Executando tamanhosDistintos()");
        TypedQuery<String> query
                = em.createQuery("SELECT DISTINCT(t.descricaoTamanho) FROM Tamanho t ORDER BY t.descricaoTamanho", String.class);
        List<String> tamanhos = query.getResultList();
        //pp, p, m, g, xg
        assertEquals(5, tamanhos.size());
    }

    //Uso de ORDER BY DESC
    @Test
    public void ordenacaoVolumes() {
        logger.info("Executando ordenacaoVolumes()");
        TypedQuery<Volume> query;
        query = em.createQuery(
                "SELECT v FROM Volume v ORDER BY v.descricaoVolume DESC",
                Volume.class);
        List<Volume> volumes = query.getResultList();
        assertEquals(5, volumes.size());
        //O primeiro é Volume 3, e o último é edição rara.
        assertEquals("Volume 3", volumes.get(0).getDescricaoVolume());
        assertEquals("Edição rara", volumes.get(volumes.size() - 1).getDescricaoVolume());
    }

    //Uso de selects aninhados
    @Test
    public void livrosRaros() {
        logger.info("Executando livrosRaros()");
        TypedQuery<Livro> query;
        query = em.createQuery(
                "SELECT l FROM Livro l WHERE l.volume IN (SELECT v FROM Volume v WHERE v.descricaoVolume = :param )",
                Livro.class);
        query.setParameter("param", "Edição rara");
        List<Livro> itens = query.getResultList();
        assertNotNull(itens);

        for (Livro item : itens) {
            assertThat(item.getVolume().getDescricaoVolume(), CoreMatchers.anyOf(
                    startsWith("Edição ")));
        }
    }

    //Uso do COUNT
    @Test
    public void quantidadeAlunosMenoresIdade() {
        logger.info("Executando quantidadeAlunosMenoresIdade()");
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Aluno a WHERE a.responsavel <> :resp", Long.class);
        query.setParameter("resp", "18+");
        long resultado = query.getSingleResult();
        assertEquals(5, resultado);
    }

}
