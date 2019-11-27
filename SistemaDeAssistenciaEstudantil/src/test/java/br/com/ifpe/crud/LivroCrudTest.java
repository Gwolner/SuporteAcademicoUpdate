package br.com.ifpe.crud;


import br.com.ifpe.modelo.Livro;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;


public class LivroCrudTest extends GenericTest{
    
    @Test
    public void persistirLivro() {   
        Livro livro = criarLivro();
        em.persist(livro);
        em.flush();
        assertNotNull(livro.getIdLivro());
    }
    
    @Test
    public void atualizarLivro() {
        
        String novaMateria = "AED";
        String novoTitulo = "C++ Como programar";
        String novoAutor = "Paul Deitel";
        int novaQuantidade = 45;
        Long novoIsbn = 9781254145214L;   
        
        Long id = 6L;
        Livro livro = em.find(Livro.class, id);
        livro.setMateria(novaMateria);
        livro.setTitulo(novoTitulo);
        livro.setAutor(novoAutor);
        livro.setQuantidade(novaQuantidade);
        livro.setIsbn(novoIsbn);
        
        em.flush();
        
        String jpql = "SELECT c FROM Livro c WHERE c.idLivro = ?1";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        livro = query.getSingleResult();
        
        assertEquals(novaMateria, livro.getMateria());
        assertEquals(novoTitulo, livro.getTitulo()); 
        assertEquals(novoAutor, livro.getAutor()); 
        assertEquals(novaQuantidade, livro.getQuantidade()); 
        assertEquals(novoIsbn, livro.getIsbn()); 
    }
    
    @Test
    public void atualizarLivroMerge() {
        String novaMateria = "Fisica";
        String novoTitulo = "A Quantica e a Realidade";
        String novoAutor = "Edmario Sebroso";
        int novaQuantidade = 3;
        Long novoIsbn = 9781478985689L;   
        
        Long id = 5L;
        Livro livro = em.find(Livro.class, id);
        livro.setMateria(novaMateria);
        livro.setTitulo(novoTitulo);
        livro.setAutor(novoAutor);
        livro.setQuantidade(novaQuantidade);
        livro.setIsbn(novoIsbn);
        
        em.clear();
        em.merge(livro);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        livro = em.find(Livro.class, id, properties);
        
        assertEquals(novaMateria, livro.getMateria());
        assertEquals(novoTitulo, livro.getTitulo()); 
        assertEquals(novoAutor, livro.getAutor()); 
        assertEquals(novaQuantidade, livro.getQuantidade()); 
        assertEquals(novoIsbn, livro.getIsbn());
    }
    
    @Test
    public void removerLivro() {        
        Livro livro = em.find(Livro.class, 7L);
        em.remove(livro);
        livro = em.find(Livro.class, 7L);
        assertNull(livro);
    }
    
    
    
}
