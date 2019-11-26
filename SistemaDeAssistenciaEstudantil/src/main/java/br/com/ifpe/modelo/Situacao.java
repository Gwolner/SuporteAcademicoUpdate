package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_SITUACAO")
@Access(AccessType.FIELD)
@NamedQueries(
        {
        @NamedQuery(
        name = Situacao.SITUACAO_POR_DESCRICAO,
        query = "SELECT s FROM Situacao s WHERE s.descricaoSituacao like :descricao"
                )
        }
)
public class Situacao implements Serializable {
    
    public static final String SITUACAO_POR_DESCRICAO= "SituacaoPorDescricao";
    
     public Situacao() {
        this.fardamentos = new ArrayList<>();
    }
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_situacao")
    private Long idSituacao;
    
    @NotBlank(message="{Situacao.descricaoSituacao}")
    @Size(max=15, message="{Situacao.descricaoSituacao.size}")
    @Column(name="descricao_situacao")
    private String descricaoSituacao;
    
    
    //Cardinalidade Situacao 1 : N Fardamento
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "situacao",
            fetch = FetchType.LAZY)
    protected List<Fardamento> fardamentos;
    
    
    public Long getIdSituacao() {
        return this.idSituacao;
    }

    public void setIdSituacao(Long idSituacao) {
        this.idSituacao = idSituacao;
    }

    public String getDescricaoSituacao() {
        return this.descricaoSituacao;
    }

    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }

    public List<Fardamento> getFardamentos() {
        return this.fardamentos;
    }

    public boolean addFardamento(Fardamento f) {
        if (!fardamentos.contains(f)) {
            f.setSituacao(this);
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
    
    public boolean removeFardamento(Object o) {
        if(!(o instanceof Fardamento)){
            return false;
        }else{
            return fardamentos.remove(o);
        }    
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSituacao != null ? idSituacao.hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Situacao)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Situacao other = (Situacao) o;
            return !((this.idSituacao == null && other.idSituacao != null)
                    ||(this.idSituacao != null && 
                    !this.idSituacao.equals(other.idSituacao))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
    
}
