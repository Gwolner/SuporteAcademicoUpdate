package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Aluno;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class AlunoSelectTest extends GenericTest{
    
    /*############################ NAMEDQUERY ############################*/
    
    //Uso do NAMEDQUERY Aluno.EMAIL_POR_MATRICULA
    @Test
    public void situacaoPorDescricao() {
        logger.info("Executando situacaoPorDescricao()");
        TypedQuery<Aluno> query = em.createNamedQuery(
                Aluno.EMAIL_POR_MATRICULA, Aluno.class
        );
        query.setParameter("mat", "4321OPLK");
        List<Aluno> alunos = query.getResultList();

        assertEquals(1, alunos.size());
        assertEquals("creed.play@gmail.com", alunos.get(0));
    }
    
    /*########################### CREATEQUERY ############################*/
    
    //Uso do COUNT (Alunos maior de idade)
    @Test
    public void quantidadeAlunosMaioresIdade() {
        logger.info("Executando quantidadeAlunosMenoresIdade()");
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Aluno a WHERE a.responsavel = :resp",
                Long.class
        );
        query.setParameter("resp", "18+");
        long resultado = query.getSingleResult();
        
        assertEquals(3, resultado);
    }
    
    //Uso do COUNT (Alunos menor de idade)
    @Test
    public void quantidadeAlunosMenoresIdade() {
        logger.info("Executando quantidadeAlunosMenoresIdade()");
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Aluno a WHERE a.responsavel <> :resp", 
                Long.class
        );
        query.setParameter("resp", "18+");
        long resultado = query.getSingleResult();
        
        assertEquals(7, resultado);
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
        
        assertEquals(4, alunos.size());
    }
    
    //Uso do JOIN (Tabelas tb_aluno e tb_bolsa)
    @Test
    public void bolsaDeAlunoPorMatricula() {
        logger.info("Executando bolsaDeAlunoPorMatricula()");
        TypedQuery<Aluno> query;
        query = em.createQuery(
                "SELECT a.nomeUsuario, b.nomeBolsa, b.tipo, b.valor "
                + "FROM Aluno a "
                + "JOIN a.bolsas b "
                + "WHERE a.matricula= ?1 ",
                Aluno.class);
        query.setParameter(1, "1234ABCD");
        List<Aluno> itens = query.getResultList();
        
        assertEquals(2, itens.size());
    }
    
}
