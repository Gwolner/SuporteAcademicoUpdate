package br.com.ifpe.teste;


import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Usuario;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;


public class AlunoCrudTest extends GenericTest {
    
    @Test
    public void persistirAluno() {
        Aluno aluno = criarAluno();        
        em.persist(aluno);
        em.flush();
        assertNotNull(aluno.getIdUsuario());
    }
    
    @Test
    public void atualizarAluno() {
        String novoNome = "Guilhermee Wolner Dias Monte";
        String novoCurso = "TADS";
        Long novoContato = 81911112222L;        
        String novoTurno = "Noite";
        String novaMatricula = "2017OPOP";
        String novoCpf = "921.006.400-38";
        String novoEmail = "gwolner27@gmail.com";
        Long id = 1L;

        Aluno aluno = em.find(Aluno.class, id);
        aluno.setNomeUsuario(novoNome);
        aluno.setCurso(novoCurso);
        aluno.setContatoResponsavel(novoContato);
        aluno.setTurno(novoTurno);
        aluno.setMatricula(novaMatricula);
        aluno.setCpf(novoCpf);
        aluno.setEmail(novoEmail);

        em.flush();
        
        String jpql = "SELECT c FROM Aluno c WHERE c.idUsuario = ?1";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        aluno = query.getSingleResult();

        assertEquals(novoNome, aluno.getNomeUsuario());      
        assertEquals(novoCurso, aluno.getCurso());   
        assertEquals(novoContato, aluno.getContatoResponsavel());   
        assertEquals(novoTurno, aluno.getTurno());   
        assertEquals(novaMatricula, aluno.getMatricula());   
        assertEquals(novoCpf, aluno.getCpf());   
        assertEquals(novoEmail, aluno.getEmail());  
    }
    
    @Test
    public void atualizarAlunoMerge() {
        String novoNome = "Alfredo Gothan";
        String novoCurso = "TADS";
        Long novoContato = 81911112222L;        
        String novoTurno = "Noite";
        String novaMatricula = "2017OPOP";
        String novoCpf = "148.215.630-02";
        String novoEmail = "alfredinho@gmail.com";
        Long id = 5L;       
        
        Aluno aluno = em.find(Aluno.class, id);
        aluno.setNomeUsuario(novoNome);
        aluno.setCurso(novoCurso);
        aluno.setContatoResponsavel(novoContato);
        aluno.setTurno(novoTurno);
        aluno.setMatricula(novaMatricula);
        aluno.setEmail(novoEmail);
        aluno.setCpf(novoCpf);
        
        em.clear();
        em.merge(aluno);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        aluno = em.find(Aluno.class, id, properties);

        assertEquals(novoNome, aluno.getNomeUsuario());      
        assertEquals(novoCurso, aluno.getCurso());   
        assertEquals(novoContato, aluno.getContatoResponsavel());   
        assertEquals(novoTurno, aluno.getTurno());   
        assertEquals(novaMatricula, aluno.getMatricula());   
        assertEquals(novoCpf, aluno.getCpf());   
        assertEquals(novoEmail, aluno.getEmail());    
    }
    
    @Test
    public void removerAluno() {
        Aluno aluno = em.find(Aluno.class, 10L);
        em.remove(aluno);
        Usuario usuario = em.find(Usuario.class, 10L);
        assertNull(usuario);
    }
    
    
    
}
