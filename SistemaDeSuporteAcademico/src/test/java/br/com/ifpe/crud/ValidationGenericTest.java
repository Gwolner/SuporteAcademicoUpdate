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
import br.com.ifpe.crud.DbUnitUtil;
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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;


public class ValidationGenericTest {
    
    
    private static EntityManagerFactory emf;
    private static Logger logger;
    EntityManager em;
    private EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.OFF);
        emf = Persistence.createEntityManagerFactory("tarefas");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());

            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
            em = null;
            et = null;
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
    
    protected Volume criarVolumeInvalido(){
        Volume volume = new Volume();
        volume.setDescricaoVolume(" "); 
        
        return volume;
    }
    
    protected Aluno criarAlunoInvalido(){        
        Aluno aluno = new Aluno();
        aluno.setNomeUsuario(" "); //Nome inválido
        aluno.setCpf("920073015"); //Cpf inválido
        aluno.setDataNascimento(getData(21,12,2023)); //Data inválida
        aluno.setRg(457253472); 
        aluno.setCurso(" "); //Curso inválido
        aluno.setResponsavel(" "); //Responsável inválido
        aluno.setContatoResponsavel(911112222L);
        aluno.setTurno(" "); //Turno inválido
        aluno.setMatricula("20172y6-rc0000"); //Matricula inválido
        aluno.setEmail("tedson@"); //Email inválido
        
        return aluno;
    }
    
    protected Bolsa criarBolsaInvalida(){
        Bolsa bolsa = new Bolsa();
        bolsa.setNomeBolsa(" "
//                "PIBICpesquisaintegradoraornamentalsuperioetecnico"
        ); //Nome de bolsa invalido
        bolsa.setTipo(" ");
        bolsa.setValor(1000000.00); //Valor de bolsa invalido
        
        return bolsa;
    }
    
    protected Emprestimo criarEmprestimoInvalido(){        
        Emprestimo emprestimo = new Emprestimo();
        String statusEmprestimo = " "; //Status invalido
        emprestimo.setStatus(statusEmprestimo);
        emprestimo.setDataDevolucao(getData(25,12,2012)); //Data inválida
        
        return emprestimo;
    }
    
    protected Fardamento criarFardamentoInvalido(){
        Fardamento fardamento = new Fardamento();
        fardamento.setQuantidadeEntregue(2); //Quantidade invalida
        fardamento.setAluno(criarAluno());
        fardamento.setSituacao(criarSituacao());
        fardamento.setTamanho(criarTamanho());
        
        return fardamento;
    }
    
    protected Livro criarLivroInvalido(){
        Livro livro = new Livro();
        livro.setTitulo(" "); //Titulo invalido
        livro.setAutor(" "); //Autor invalido
        livro.setMateria(" "); //Materia invalido
        livro.setIsbn(123L); //Isbn invalido
        livro.setQuantidade(10);
        
        return livro;
    }
    
    protected Professor criarProfessorInvalido(){
        Professor professor = new Professor();
        professor.setNomeUsuario(" "); //Usuario inválido
        professor.setCpf("822732580-70"); //Cpf invalido
        professor.setDataNascimento(getData(21,12,2023)); //Nascimento inválido
        professor.setRg(438713588);
        professor.setDepartamento(" "); //Depart. invalido
        professor.setRamal(2530L);
        professor.setSiape(306564587);
        
        return professor;
    }
    
    protected Situacao criarSituacaoInvalida(){        
        Situacao situacao = new Situacao();       
        situacao.setDescricaoSituacao(
                "Entrrega com atraso pelo DAE"
        ); //Descricao invalida
        
        return situacao;
    }
    
    protected Tamanho criarTamanhoInvalido(){
        Tamanho tamanho = new Tamanho();      
        tamanho.setDescricaoTamanho("XGG"); //Tamanho inválido
        
        return tamanho;
    }
}
