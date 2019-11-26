package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Livro;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-26T09:16:39")
@StaticMetamodel(Volume.class)
public class Volume_ { 

    public static volatile ListAttribute<Volume, Livro> livros;
    public static volatile SingularAttribute<Volume, String> descricaoVolume;
    public static volatile SingularAttribute<Volume, Long> idVolume;

}