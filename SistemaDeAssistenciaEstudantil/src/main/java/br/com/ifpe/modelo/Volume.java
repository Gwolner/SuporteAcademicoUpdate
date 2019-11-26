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
@Table(name="TB_VOLUME")
@Access(AccessType.FIELD)
@NamedQueries(
        {
        @NamedQuery(
        name = Volume.VOLUME_POR_DESCRICAO,
        query = "SELECT v FROM Volume v WHERE v.descricaoVolume like :descricao"
                )
        }
)
public class Volume implements Serializable {
    
    public static final String VOLUME_POR_DESCRICAO = "VolumePorDescricao";

    
    public Volume() {
        this.livros = new ArrayList<>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_volume")
    private Long idVolume;
    
    @NotBlank(message="{Volume.descricaoVolume}")
    @Column(name="descricao_volume")
    private String descricaoVolume;
    
    
    //Cardinalidade Volume 1 : N Livro
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "volume", 
            fetch = FetchType.LAZY)
    protected List<Livro> livros;
    
    
    public Long getIdVolume() {
        return this.idVolume;
    }

    public void setIdVolume(Long idVolume) {
        this.idVolume = idVolume;
    }

    public String getDescricaoVolume() {
        return this.descricaoVolume;
    }

    public void setDescricaoVolume(String descricaoVolume) {
        this.descricaoVolume = descricaoVolume;
    }

    public List<Livro> getLivros() {
        return this.livros;
    }

    public boolean addLivro(Livro l) {
        if (!livros.contains(l)) {
            l.setVolume(this);
            return livros.add(l);
        } else {
            return false;
        }    
    }
    
    public void addAllLivros(List<Livro> livros) {
        for (Livro livro : livros) {
            this.addLivro(livro);
        }
    }
    
    public boolean removeLivro(Object o) {
        if(!(o instanceof Livro)){
            return false;
        }else{
            return livros.remove(o);
        }    
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVolume != null ? idVolume.hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Volume)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Volume other = (Volume) o;
            return !((this.idVolume == null && other.idVolume != null)
                    ||(this.idVolume != null && 
                    !this.idVolume.equals(other.idVolume))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
}
