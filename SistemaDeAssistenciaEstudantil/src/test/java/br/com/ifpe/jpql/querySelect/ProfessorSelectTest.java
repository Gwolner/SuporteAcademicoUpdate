package br.com.ifpe.jpql.querySelect;

import br.com.ifpe.crud.GenericTest;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ProfessorSelectTest extends GenericTest{
    
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
