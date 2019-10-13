package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Aluno;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-08T23:46:57")
@StaticMetamodel(Bolsa.class)
public class Bolsa_ { 

    public static volatile SingularAttribute<Bolsa, String> tipo;
    public static volatile SingularAttribute<Bolsa, String> nomeBolsa;
    public static volatile ListAttribute<Bolsa, Aluno> alunos;
    public static volatile SingularAttribute<Bolsa, Double> valor;
    public static volatile SingularAttribute<Bolsa, Long> idBolsa;

}