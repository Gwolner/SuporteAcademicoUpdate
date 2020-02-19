package br.com.ifpe.crud;


import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Bolsa;
import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Livro;
import br.com.ifpe.modelo.Professor;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.modelo.Volume;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;


public class GenericTest {

    protected static EntityManagerFactory emf;
    protected static Logger logger;
    protected EntityManager em;
    protected EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        //logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("ssa");
        //emf.createEntityManager();
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        beginTransaction();
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();      
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            fail(ex.getMessage());
        }
    }

    protected Date getData(Integer dia, Integer mes, Integer ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        return c.getTime();
    }
    
    protected Volume criarVolume(){
        Volume volume = new Volume();
        volume.setDescricaoVolume("Vol. 4");
        
        return volume;
    }
    
    protected Aluno criarAluno(){        
        Aluno aluno = new Aluno();
        aluno.setNomeUsuario("Thomas Edson");
        aluno.setCpf("989.200.730-15");
        aluno.setRg(457253472);
        aluno.setCurso("Quimica");
        aluno.setResponsavel("Abilbo");
        aluno.setContatoResponsavel(911112222L);
        aluno.setTurno("Manha");
        aluno.setMatricula("0915POOP");
//        aluno.setCpf(90685302083L);
        aluno.setEmail("tedson@bol.com");
        
        return aluno;
    }
        
    protected Bolsa criarBolsa(){
        Bolsa bolsa = new Bolsa();
        bolsa.setNomeBolsa("PIBIC");
        bolsa.setTipo("Pesquisa");
        bolsa.setValor(0.00);
        
        return bolsa;
    }
    
    protected Emprestimo criarEmprestimo(){        
        Emprestimo emprestimo = new Emprestimo();
        String statusEmprestimo = "Teste";
        emprestimo.setStatus(statusEmprestimo);
        
        return emprestimo;
    }
    
    protected Fardamento criarFardamento(){
        Fardamento fardamento = new Fardamento();
        fardamento.setQuantidadeEntregue(1);
        fardamento.setAluno(criarAluno());
        fardamento.setSituacao(criarSituacao());
        fardamento.setTamanho(criarTamanho());
        return fardamento;
    }
    
    protected Livro criarLivro(){
        Livro livro = new Livro();
        livro.setTitulo("Java: Como Programar");
        livro.setAutor("Harvey Deitel");
        livro.setMateria("LPOO");
        livro.setIsbn(9781666123123L);
        livro.setQuantidade(10);
        livro.setVolume(criarVolume());
        
        return livro;
    }
    
    protected Professor criarProfessor(){
        Professor professor = new Professor();
        professor.setNomeUsuario("Murilo Murilison");
        professor.setCpf("822.732.580-70");
        professor.setRg(438713588);
        professor.setDepartamento("DADT");
        professor.setRamal(2530L);
        professor.setSiape(3064587);
        
        return professor;
    }
    
    protected Situacao criarSituacao(){        
        Situacao situacao = new Situacao();       
        situacao.setDescricaoSituacao("Entr. c/ atraso");
        
        return situacao;
    }
    
    protected Tamanho criarTamanho(){
        Tamanho tamanho = new Tamanho();      
        tamanho.setDescricaoTamanho("GG");
        
        return tamanho;
    }
    
}
