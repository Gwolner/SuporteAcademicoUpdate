package br.com.ifpe.modelo;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TB_FARDAMENTO")
@NamedQueries(
    {
        @NamedQuery(
            name = Fardamento.QUANTIDADE_ENTREGUE_POR_ID,
            query = "SELECT f.quantidadeEntregue FROM Fardamento f WHERE f.idFardamento = ?1"
        ),
        @NamedQuery(
            name = Fardamento.FARDAMENTOS,
            query = "SELECT f.idFardamento FROM Fardamento f ORDER BY f.idFardamento"
        )
    }
)
@Access(AccessType.FIELD)
public class Fardamento implements Serializable {
    
    public static final String QUANTIDADE_ENTREGUE_POR_ID = "Quantidade_Entregue_Por_Id";
    public static final String FARDAMENTOS = "Fardamentos";    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fardamento")
    private Long idFardamento;

    //Não usa anotação por ser tipo Integer
    @Column(name = "quantidade_entregue")
    private int quantidadeEntregue;

    //Cardinalidade Aluno 1 : N Fardamento
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno")
    protected Aluno aluno;

    //Cardinalidade Situacao 1 : N Fardamento
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_situacao", referencedColumnName = "id_situacao")
    protected Situacao situacao;

    //Cardinalidade Tamanho 1 : N Fardamento
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tamanho", referencedColumnName = "id_tamanho")
    protected Tamanho tamanho;

    public Long getIdFardamento() {
        return idFardamento;
    }

    public void setIdFardamento(Long idFardamento) {
        this.idFardamento = idFardamento;
    }

    public int getQuantidadeEntregue() {
        return quantidadeEntregue;
    }

    public void setQuantidadeEntregue(int quantidadeEntregue) {
        this.quantidadeEntregue = quantidadeEntregue;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFardamento != null ? idFardamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Fardamento)) {
            return false; //Se não for a instância desejada, retorna false;
        } else {
            Fardamento other = (Fardamento) o;
            return !((this.idFardamento == null && other.idFardamento != null)
                    || (this.idFardamento != null
                    && !this.idFardamento.equals(other.idFardamento)));
            /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
             */
        }
    }
}
