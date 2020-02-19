package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.crud.GenericTest;
import br.com.ifpe.modelo.Professor;
import br.com.ifpe.modelo.Usuario;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ProfessorSelectTest extends GenericTest{
    
    /*############################ NAMEDQUERY ############################*/
    
    //Uso do NAMEDQUERY Professor.RAMAL_POR_DEPARTAMENTO
    @Test
    public void situacaoPorDescricao() {
        logger.info("Executando situacaoPorDescricao()");
        TypedQuery<Professor> query = em.createNamedQuery(
                Professor.RAMAL_POR_DEPARTAMENTO, Professor.class
        );
        query.setParameter("dept", "DGP");
        List<Professor> volumes = query.getResultList();

        assertEquals(2, volumes.size());
    }
    
    /*########################### CREATEQUERY ############################*/
    
    //Uso do COUNT
    @Test
    public void quantidadeProfessorDepartamento() {
        logger.info("Executando quantidadeProfessorDepartamento()");
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) FROM Professor p WHERE p.departamento = :dept", 
                Long.class
        );
        query.setParameter("dept", "DASE");
        long resultado = query.getSingleResult();
        
        assertEquals(2, resultado);
    }
}
