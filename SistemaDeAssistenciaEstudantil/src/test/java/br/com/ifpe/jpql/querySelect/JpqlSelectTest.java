//package br.com.ifpe.jpql.querySelect;
//
//import br.com.ifpe.modelo.*;
//import java.util.List;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import org.hamcrest.CoreMatchers;
//import br.com.ifpe.teste.GenericTest;
//import static org.hamcrest.CoreMatchers.startsWith;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import org.junit.Test;
//
//public class JpqlSelectTest extends GenericTest {
//
////    //Uso do MIN e MAX
////    @Test
////    public void valorMaximoEMinimoDeBolsa() {
////        logger.info("Executando valorMaximoEMinimoDeBolsa()");
////        Query query = em.createQuery(
////                "SELECT MAX(b.valor), MIN(b.valor) FROM Bolsa b"
////        );
////        Object[] bolsas = (Object[]) query.getSingleResult();
////
////        assertEquals(600.00, bolsas[0]);
////        assertEquals(0.00, bolsas[1]);
////
////    }
//
////    //Uso do WHERE BETWEEN
////    @Test
////    public void faixaDeValoresDeBolsa() {
////        logger.info("Executando faixaDeValoresDeBolsa()");
////        Query query = em.createQuery(
////                "SELECT b FROM Bolsa b WHERE b.valor BETWEEN ?1 AND ?2",
////                Bolsa.class
////        );
////        query.setParameter(1, 430.00);
////        query.setParameter(2, 700.00);
////        List<Bolsa> bolsas = query.getResultList();
////
////        for (Bolsa bolsa : bolsas) {
////            assertTrue(bolsa.getValor() >= 430.00 &&
////                       bolsa.getValor() <= 700.00
////            );
////        }
////        
////        assertEquals(2, bolsas.size());
////    }
//
//    
////    //Uso do NOT NULL
////    @Test
////    public void volumesNaoNulos() {
////        logger.info("Executando volumesNaoNulos()");
////        Query query = em.createQuery(
////                "SELECT v FROM Volume v WHERE v.descricaoVolume IS NOT NULL",
////                Volume.class
////        );
////        List<Volume> volumes = query.getResultList();
////
////        for (Volume volume : volumes) {
////            assertNotNull(volume.getDescricaoVolume());
////        }
////        
////        assertEquals(5, volumes.size());
////
////    }
//    
////    //Uso do NAMEDQUERY (Checar classe para ler a Query!)
////    @Test
////    public void tamanhosIniciandoComP() {
////        logger.info("Executando tamanhosIniciandoComP()");
////        TypedQuery<Tamanho> query = em.createNamedQuery(
////                "Tamanho.PorLetra", Tamanho.class
////        );
////        query.setParameter("param", "P%");
////        List<Tamanho> tamanhos = query.getResultList();
////
////        for (Tamanho tam : tamanhos) {
////            assertTrue(tam.getDescricaoTamanho().startsWith("P"));
////        }
////
////        //P e PP
////        assertEquals(2, tamanhos.size());
////    }
//
////    //Uso do SETMAXRESULTS
////    @Test
////    public void livrosEmprestados() {
////        logger.info("Executando livrosEmprestados()");
////        TypedQuery<Emprestimo> query;
////        query = em.createQuery(
////                "SELECT e FROM Emprestimo e WHERE e.status like ?1",
////                Emprestimo.class);
////        query.setParameter(1, "Emprestado"); //Setando parâmetro posicional.
////        query.setMaxResults(20); //Determinando quantidade máxima de resultados.
////        List<Emprestimo> emprestimos = query.getResultList();
////
////        for (Emprestimo emprestimo : emprestimos) {
////            assertEquals("Emprestado", emprestimo.getStatus());
////        }
////
////        assertEquals(17, emprestimos.size());
////    }
//
////    //Uso do LIKE
////    @Test
////    public void livrosAtrasoOuRepetido() {
////        logger.info("Executando livrosAtrasoOuRepetido()");
////        TypedQuery<Emprestimo> query;
////        query = em.createQuery(
////                "SELECT e FROM Emprestimo e "
////                + "WHERE e.status = ?1 "
////                + "OR e.status = ?2",
////                Emprestimo.class);
////        query.setParameter(1, "Atrasado"); 
////        query.setParameter(2, "Repetido");        
////        List<Emprestimo> emprestimos = query.getResultList();
////
////        for (Emprestimo emprestimo : emprestimos) {
////            assertTrue("Atrasado".equals(emprestimo.getStatus()) ||
////                       "Repetido".equals(emprestimo.getStatus()));
////        }
////        
////        assertEquals(3, emprestimos.size());
////    }
//
////    //Uso do IN (1,2)
////    @Test
////    public void livrosDePortuguesEMatematica() {
////        logger.info("Executando livrosDePortuguesEMatematica()");
////        TypedQuery<Livro> query;
////        query = em.createQuery(
////                "SELECT l FROM Livro l "
////                + "WHERE l.materia IN ('Portugues', 'Matematica')",
////                Livro.class);
////        List<Livro> livros = query.getResultList();
////
////        for (Livro livro : livros) {
////            assertThat(livro.getMateria(),
////                    CoreMatchers.anyOf(
////                            startsWith("Portugues"),
////                            startsWith("Matematica")));
////        }
////
////        assertEquals(3, livros.size());
////    }
//
////    //Uso do WHERE SIZE
////    @Test
////    public void alunosComBolsas() {
////        logger.info("Executando alunosComBolsas()");
////        TypedQuery<Aluno> query;
////        query = em.createQuery(
////                "SELECT a FROM Aluno a WHERE SIZE(a.bolsas) >= ?1",
////                Aluno.class);
////        query.setParameter(1, 2);
////        List<Aluno> alunos = query.getResultList();
////        
////        assertEquals(4, alunos.size());
////    }
//
////    //Uso de SELECT DISTINCT
////    @Test
////    public void tamanhosDistintos() {
////        logger.info("Executando tamanhosDistintos()");
////        TypedQuery<String> query
////                = em.createQuery("SELECT DISTINCT(t.descricaoTamanho) "
////                        + "FROM Tamanho t "
////                        + "ORDER BY t.descricaoTamanho", String.class
////                );
////        List<String> tamanhos = query.getResultList();
////        
////        for (String tamanho : tamanhos) {
////            assertTrue("PP".equals(tamanho)||
////                       "P".equals(tamanho) ||
////                       "m".equals(tamanho) ||
////                       "g".equals(tamanho) ||
////                       "XG".equals(tamanho)
////            );
////        }
////        
////        assertEquals(5, tamanhos.size());
////    }
//
////    //Uso de ORDER BY DESC
////    @Test
////    public void ordenacaoVolumes() {
////        logger.info("Executando ordenacaoVolumes()");
////        TypedQuery<Volume> query;
////        query = em.createQuery(
////                "SELECT v FROM Volume v ORDER BY v.descricaoVolume DESC",
////                Volume.class);
////        List<Volume> volumes = query.getResultList();
////        assertEquals(5, volumes.size());
////        
////        //O primeiro é Volume 3, e o último é edição rara.
////        assertEquals("Volume 3", volumes.get(0).getDescricaoVolume());        
////        assertEquals("Volume 2", volumes.get(1).getDescricaoVolume());
////        assertEquals("Vol. Unico", volumes.get(2).getDescricaoVolume());
////        assertEquals("Vol. 1", volumes.get(3).getDescricaoVolume());        
////        assertEquals("Edição rara", volumes.get(4).getDescricaoVolume());
////    }
//
////    //Uso de SELECT ANINHADO
////    @Test
////    public void livrosVolUnico() {
////        logger.info("Executando livrosVolUnico()");
////        TypedQuery<Livro> query;
////        query = em.createQuery(
////                "SELECT l FROM Livro l WHERE l.volume IN("
////                        + "SELECT v "
////                        + "FROM Volume v "
////                        + "WHERE v.descricaoVolume = :param "
////                + ")",
////                Livro.class);
////        query.setParameter("param", "Vol. Unico");
////        List<Livro> itens = query.getResultList();
////        assertNotNull(itens);
////
////        for (Livro item : itens) {
////            assertThat(item.getVolume().getDescricaoVolume(),CoreMatchers.anyOf(
////                    startsWith("Vol. Unico")));
////        }
////        
////        assertEquals(4, itens.size());
////    }
//
//    
////    //Uso do COUNT (Alunos maior de idade)
////    @Test
////    public void quantidadeAlunosMaioresIdade() {
////        logger.info("Executando quantidadeAlunosMenoresIdade()");
////        TypedQuery<Long> query = em.createQuery(
////                "SELECT COUNT(a) FROM Aluno a WHERE a.responsavel = :resp",
////                Long.class
////        );
////        query.setParameter("resp", "18+");
////        long resultado = query.getSingleResult();
////        
////        assertEquals(3, resultado);
////    }
////    
////    //Uso do COUNT (Alunos menor de idade)
////    @Test
////    public void quantidadeAlunosMenoresIdade() {
////        logger.info("Executando quantidadeAlunosMenoresIdade()");
////        TypedQuery<Long> query = em.createQuery(
////                "SELECT COUNT(a) FROM Aluno a WHERE a.responsavel <> :resp", 
////                Long.class
////        );
////        query.setParameter("resp", "18+");
////        long resultado = query.getSingleResult();
////        
////        assertEquals(7, resultado);
////    }
//    
//    
//    
//    
//
//    //Uso do JOIN (Tabelas tb_fardamento e tb_situacao)
//    @Test
//    public void quantidadeFardamentoEntregueESituacaoPorId() {
//        logger.info("Executando quantidadeFardamentoEntregueESituacaoPorId()");
//        TypedQuery<Fardamento> query;
//        query = em.createQuery(
//                "SELECT f.quantidadeEntregue, s.descricaoSituacao "
//                + "FROM Fardamento f "
//                + "JOIN f.situacao s "
//                + "WHERE s.idSituacao = ?1 ",
//                Fardamento.class);
//        query.setParameter(1, 2);
//        List<Fardamento> itens = query.getResultList();
//        
//        assertEquals(6, itens.size());
//    }
//    
//    //Uso do JOIN (Tabelas tb_livro e tb_volume)
//    @Test
//    public void livrosTituloComVolume() {
//        logger.info("Executando livrosTituloComVolume()");
//        TypedQuery<Livro> query;
//        query = em.createQuery(
//                "SELECT l.titulo, v.descricaoVolume "
//                + "FROM Livro l "
//                + "JOIN l.volume v "
//                + "ON l.materia = ?1 ",
//                Livro.class);
//        query.setParameter(1, "Matematica");
//        List<Livro> livro = query.getResultList();
//        
//        assertEquals(2, livro.size());
//        
////        assertEquals("Introducao a matematica", livro.get(0).);
////        assertEquals("Introducao a matematica", livro.get(1).getTitulo());
//
//    }
//    
//    //Uso do JOIN (Tabelas tb_aluno e tb_bolsa)
//    @Test
//    public void bolsaDeAlunoPorMatricula() {
//        logger.info("Executando bolsaDeAlunoPorMatricula()");
//        TypedQuery<Aluno> query;
//        query = em.createQuery(
//                "SELECT a.nomeUsuario, b.nomeBolsa, b.tipo, b.valor "
//                + "FROM Aluno a "
//                + "JOIN a.bolsas b "
//                + "WHERE a.matricula= ?1 ",
//                Aluno.class);
//        query.setParameter(1, "1234ABCD");
//        List<Aluno> itens = query.getResultList();
//        
//        assertEquals(2, itens.size());
//        
//    }
//}
