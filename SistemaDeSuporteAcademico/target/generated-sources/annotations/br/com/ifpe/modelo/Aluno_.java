package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Bolsa;
import br.com.ifpe.modelo.Fardamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-26T22:09:48")
@StaticMetamodel(Aluno.class)
public class Aluno_ extends Usuario_ {

    public static volatile SingularAttribute<Aluno, String> curso;
    public static volatile SingularAttribute<Aluno, Long> contatoResponsavel;
    public static volatile ListAttribute<Aluno, Fardamento> fardamentos;
    public static volatile SingularAttribute<Aluno, String> matricula;
    public static volatile SingularAttribute<Aluno, String> turno;
    public static volatile SingularAttribute<Aluno, String> responsavel;
    public static volatile SingularAttribute<Aluno, String> email;
    public static volatile ListAttribute<Aluno, Bolsa> bolsas;

}