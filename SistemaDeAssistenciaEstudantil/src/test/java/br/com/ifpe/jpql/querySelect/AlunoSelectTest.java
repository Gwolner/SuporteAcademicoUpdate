package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.crud.GenericTest;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class AlunoSelectTest extends GenericTest{
    
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
    
}
