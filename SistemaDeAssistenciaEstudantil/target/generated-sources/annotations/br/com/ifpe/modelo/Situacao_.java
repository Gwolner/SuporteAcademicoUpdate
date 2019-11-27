package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Fardamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-26T22:09:48")
@StaticMetamodel(Situacao.class)
public class Situacao_ { 

    public static volatile SingularAttribute<Situacao, String> descricaoSituacao;
    public static volatile ListAttribute<Situacao, Fardamento> fardamentos;
    public static volatile SingularAttribute<Situacao, Long> idSituacao;

}