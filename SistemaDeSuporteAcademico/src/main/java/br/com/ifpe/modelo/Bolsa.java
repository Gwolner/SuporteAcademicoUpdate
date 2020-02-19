package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_BOLSA")
@Access(AccessType.FIELD)
@NamedQueries(
        {
            @NamedQuery(
                name = Bolsa.CONTAR_BOLSAS_POR_VALOR,
                query = "SELECT COUNT(b.nomeBolsa) FROM Bolsa b WHERE b.valor like :valor"
            )
        }
)
public class Bolsa implements Serializable {
    
        public static final String CONTAR_BOLSAS_POR_VALOR 
                = "Contar_Bolsa_Por_Valor";

    
    public Bolsa() {
        this.alunos = new ArrayList<>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_bolsa")
    private Long idBolsa;
    
    @NotBlank(message="{Bolsa.nomeBolsa}")
    @Size(max=45)
    @Column(name="nome_bolsa")
    private String nomeBolsa;
    
    @NotBlank(message="{Bolsa.tipo}")
    @Size(max=45) //Message default!
    @Column(name="tipo")
    private String tipo;
    
    //Sem @ para tipo Double
    @Column(name="valor")
    private Double valor;
    
    
    //Cardinalidade Aluno N : N Bolsa
    @ManyToMany(mappedBy = "bolsas") //Subordinado ao relacionamento ManyToMany
    protected List<Aluno> alunos;

    
    public Long getIdBolsa() {
        return  this.idBolsa;
    }

    public void setIdBolsa(Long idBolsa) {
        this.idBolsa = idBolsa;
    }

    public String getNomeBolsa() {
        return  this.nomeBolsa;
    }

    public void setNomeBolsa(String nomeBolsa) {
        this.nomeBolsa = nomeBolsa;
    }

    public String getTipo() {
        return  this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return  this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Aluno> getAlunos() {
        return  this.alunos;
    }

    
    public boolean addAluno(Aluno a) {
        if (!alunos.contains(a)) {
            a.addBolsa(this);
            return alunos.add(a);
        } else {
            return false;
        }
        
    }
    
    public void addAllAlunos(List<Aluno> alunos) {
        for (Aluno aluno : alunos) {
            this.addAluno(aluno);
        }
    }
    
    
    public boolean removeAluno(Object o) {
        if(!(o instanceof Aluno)){
            return false;
        }else{
            return alunos.remove(o);
        }    
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBolsa != null ? idBolsa.hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Bolsa)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Bolsa other = (Bolsa) o;
            return !((this.idBolsa == null && other.idBolsa != null)
                    ||(this.idBolsa != null && 
                    !this.idBolsa.equals(other.idBolsa))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
}
