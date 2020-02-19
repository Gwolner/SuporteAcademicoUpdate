package br.com.ifpe.modelo;

import br.com.ifpe.validador.ValidaMatricula;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_ALUNO")
@DiscriminatorValue(value="A") //Valor usado no campo descriminador
@PrimaryKeyJoinColumn( //Definindo a PK de Aluno
        name="id_aluno", //Nome da coluna PK de Aluno
        referencedColumnName="id_usuario" //Referencia a PK de Usuario
)
@NamedQueries(
        {
        @NamedQuery(
        name = Aluno.EMAIL_POR_MATRICULA,
        query = "SELECT a.email FROM Aluno a WHERE a.matricula like :mat"
                )
        }
)
public class Aluno extends Usuario implements Serializable{
    
    public static final String EMAIL_POR_MATRICULA = "Email_Por_Matricula";
    
    public Aluno() {
        this.fardamentos = new ArrayList<>();
        this.bolsas = new ArrayList<>();
    }
    
    @NotBlank(message="{Aluno.curso}")
    @Column(name="curso")
    private String curso;
    
    @NotBlank(message="{Aluno.turno}")
    @Column(name="turno")
    private String turno;
    
    @NotBlank(message="{Aluno.matricula}")
    @Column(name="matricula")
    @ValidaMatricula
    private String matricula;
    
    @NotBlank(message="{Aluno.responsavel}")
    @Column(name="responsavel")
    private String responsavel;
    
    @Column(name="contato_responsavel")
    private Long contatoResponsavel;
    
    @Email(message="{Aluno.email}")
    @Column(name="email")
    private String email;
    
    @Transient //Campo que não será persistido, apenas exibido na interface
    private String referencia; 

    
    //Cardinalidade Aluno 1 : N Fardamento
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "aluno",
            fetch = FetchType.LAZY)
    protected List<Fardamento> fardamentos;
    
    //Cardinalidade Aluno N : N Bolsa
    @ManyToMany
    @JoinTable(
        name="TB_ALUNO_x_TB_BOLSA", //Nome da tabela associativa
        joinColumns= //Colaboração desta Entidade
            @JoinColumn(
                name="id_aluno", //Nome da coluna na tabelaa associativa
                referencedColumnName="id_aluno" //Relação com esta coluna 
            ), 
        inverseJoinColumns= //Colaboração da Entidade oposta
            @JoinColumn(
                name="id_bolsa", //Nome da coluna na tabelaa associativa
                referencedColumnName="id_bolsa"
            )//Relação com coluna da Entidade oposta
    )
    protected List<Bolsa> bolsas;

    
    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTurno() {
        return this.turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Long getContatoResponsavel() {
        return this.contatoResponsavel;
    }

    public void setContatoResponsavel(Long contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferencia() {
        //Tratar campo transiente
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        //Tratar campo transiente
        this.referencia = referencia;
    }

    
    
    
    public List<Fardamento> getFardamentos() {
        return this.fardamentos;
    }

    public boolean addFardamento(Fardamento f) {
        if (!fardamentos.contains(f)) {
            f.setAluno(this);
            return fardamentos.add(f);
        } else {
            return false;
        }   
    }
    
    public void addAllFardamentos(List<Fardamento> fardamentos) {
        for (Fardamento fardamento : fardamentos) {
            this.addFardamento(fardamento);
        }
    }
    
    
    
    
    
    public List<Bolsa> getBolsas() {
        return this.bolsas;
    }

     public boolean addBolsa(Bolsa b) {
        if (!bolsas.contains(b)) {
            b.addAluno(this);
            return bolsas.add(b);
        } else {
            return false;
        }   
    }
    
    public void addAllBolsas(List<Bolsa> bolsas) {
        for (Bolsa bolsa : bolsas) {
            this.addBolsa(bolsa);
        }
    }
    
    public boolean removeBolsa(Object o) {
        if(!(o instanceof Bolsa)){
            return false;
        }else{
            return bolsas.remove(o);
        }    
    }
    
    public boolean removeFardamento(Object o) {
        if(!(o instanceof Fardamento)){
            return false;
        }else{
            return fardamentos.remove(o);
        }    
    }
   
    
    
    @Override
    public int hashCode(){
        int hash = 0;
        hash += (super.getIdUsuario() != null ? super.getIdUsuario().hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Aluno)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Aluno other = (Aluno) o;
            return !((super.getIdUsuario() == null && other.getIdUsuario() != null)
                    ||(super.getIdUsuario() != null && 
                    !super.getIdUsuario().equals(other.getIdUsuario()))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
    
    
    
    
  }
