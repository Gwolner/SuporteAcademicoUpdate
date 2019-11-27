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
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_TAMANHO")
@NamedQueries(
        {
            @NamedQuery(
                    name = Tamanho.POR_LETRA,
                    query = "SELECT t FROM Tamanho t WHERE t.descricaoTamanho LIKE :param ORDER BY t.descricaoTamanho"
            ),
            @NamedQuery(
                name = Tamanho.TAMANHOS,
                query = "SELECT t FROM Tamanho t ORDER BY t.descricaoTamanho"
            )
        }
)
@Access(AccessType.FIELD)
public class Tamanho implements Serializable {
    
    public static final String POR_LETRA = "PorLetra";
    public static final String TAMANHOS = "Tamanhos";
    
    public Tamanho() {
        this.fardamentos = new ArrayList<>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tamanho")
    private Long idTamanho;
    
    @NotBlank(message="{Tamanho.descricaoTamanho.NotBlank}")
    @Column(name="descricao_tamanho")
    @Pattern(regexp="[A-Z]{0,2}", message="{Tamanho.descricaoTamanho}")
    private String descricaoTamanho;
    
    
    //Cardinalidade Tamanho 1 : N Fardamento
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "tamanho", 
            fetch = FetchType.LAZY)
    protected List<Fardamento> fardamentos;
    
    
    public Long getIdTamanho() {
        return this.idTamanho;
    }

    public void setIdTamanho(Long idTamanho) {
        this.idTamanho = idTamanho;
    }

    public String getDescricaoTamanho() {
        return this.descricaoTamanho;
    }

    public void setDescricaoTamanho(String descricaoTamanho) {
        this.descricaoTamanho = descricaoTamanho;
    }

    public List<Fardamento> getFardamentos() {
        return this.fardamentos;
    }

    public boolean addFardamento(Fardamento f) {
        if (!fardamentos.contains(f)) {
            f.setTamanho(this);
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
        hash += (idTamanho != null ? idTamanho.hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Tamanho)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Tamanho other = (Tamanho) o;
            return !((this.idTamanho == null && other.idTamanho != null)
                    ||(this.idTamanho != null && 
                    !this.idTamanho.equals(other.idTamanho))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
}
