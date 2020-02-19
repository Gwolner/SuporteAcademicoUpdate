package br.com.ifpe.crud;


import br.com.ifpe.modelo.Professor;
import br.com.ifpe.modelo.Usuario;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;

public class ProfessorCrudTest extends GenericTest{
    @Test
    public void persistirProfessor() {
        Professor professor = criarProfessor();        
        em.persist(professor);
        em.flush();
        assertNotNull(professor.getIdUsuario());
    }
    
    @Test
    public void atualizarProfessor() {
        String novoDepartamento = "DASE";
        Long novoRamal = 21253840L;
        int novoSiape = 3064632;
        Long id = 12L;

        Professor professor = em.find(Professor.class, id);
        professor.setDepartamento(novoDepartamento);
        professor.setRamal(novoRamal);
        professor.setSiape(novoSiape);        
        
        em.flush();
        
        String jpql = "SELECT c FROM Professor c WHERE c.idUsuario = ?1";
        TypedQuery<Professor> query = em.createQuery(jpql, Professor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        professor = query.getSingleResult();

        assertEquals(novoDepartamento, professor.getDepartamento());      
        assertEquals(novoRamal, professor.getRamal());   
        assertEquals(novoSiape, professor.getSiape());  
    }
    
    @Test
    public void atualizarProfessorMerge() {
        String novoDepartamento = "DEN";
        Long novoRamal = 21252530L;
        int novoSiape = 3064632;
        Long id = 16L;

        Professor professor = em.find(Professor.class, id);
        professor.setDepartamento(novoDepartamento);
        professor.setRamal(novoRamal);
        professor.setSiape(novoSiape);  
        
        em.clear();
        em.merge(professor);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        professor = em.find(Professor.class, id, properties);

        assertEquals(novoDepartamento, professor.getDepartamento());      
        assertEquals(novoRamal, professor.getRamal());   
        assertEquals(novoSiape, professor.getSiape()); 
    }
    
    @Test
    public void removerProfessor() {
        Professor professor = em.find(Professor.class, 11L);
        em.remove(professor);
        Usuario usuario = em.find(Usuario.class, 11L);
        assertNull(usuario);
    }
    
    
}
