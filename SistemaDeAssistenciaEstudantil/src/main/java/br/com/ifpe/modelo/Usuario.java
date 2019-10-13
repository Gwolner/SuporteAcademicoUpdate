package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)//Estrategia adotada na herença
@DiscriminatorColumn(
        name = "disc_usuario", //Coluna que distingue a subclasse
        discriminatorType = DiscriminatorType.STRING, //Tipo de dado descriminador
        length = 1 //Tamanho do dado descriminador
)
@Access(AccessType.FIELD)
public class Usuario implements Serializable { //Classe Pai na herança
    
    public Usuario() {
        this.emprestimos = new ArrayList<>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @NotBlank(message="{Usuario.nomeUsuario}")
    @Column(name="nome_usuario")
    private String nomeUsuario;
    
    @CPF(message="{Usuario.cpf}")
    @Column(name="cpf")
    private String cpf;
    
    @Column(name="rg")
    private int rg;
    
    @Past(message="{Usuario.dataNascimento}")
    @Column(name="data_nascimento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    //Cardinalidade Aluno 1 : N Emprestimo
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario",
            fetch = FetchType.LAZY)
    protected List<Emprestimo> emprestimos;
    
    
    public Long getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNomeUsuario() {
        return this.nomeUsuario;
    }
    
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public int getRg() {
        return this.rg;
    }
    
    public void setRg(int rg) {
        this.rg = rg;
    }
    
    public Date getDataNascimento() {
        return this.dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
    
    public boolean addEmprestimo(Emprestimo e) {
        if (!emprestimos.contains(e)) {
            e.setUsuario(this);
            return emprestimos.add(e);
        } else {
            return false;
        }
        
    }
    
    public void addAllEmprestimos(List<Emprestimo> emprestimos) {
        for (Emprestimo emprestimo : emprestimos) {
            this.addEmprestimo(emprestimo);
        }
    }
    
    public boolean removeEmprestimo(Object o) {
        if(!(o instanceof Emprestimo)){
            return false;
        }else{
            return emprestimos.remove(o);
        }    
    }   
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Usuario)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Usuario other = (Usuario) o;
            return !((this.idUsuario == null && other.idUsuario != null)
                    ||(this.idUsuario != null && 
                    !this.idUsuario.equals(other.idUsuario))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
}
