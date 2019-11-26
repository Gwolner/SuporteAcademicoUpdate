package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-26T15:22:56")
@StaticMetamodel(Fardamento.class)
public class Fardamento_ { 

    public static volatile SingularAttribute<Fardamento, Aluno> aluno;
    public static volatile SingularAttribute<Fardamento, Situacao> situacao;
    public static volatile SingularAttribute<Fardamento, Tamanho> tamanho;
    public static volatile SingularAttribute<Fardamento, Long> idFardamento;
    public static volatile SingularAttribute<Fardamento, Integer> quantidadeEntregue;

}