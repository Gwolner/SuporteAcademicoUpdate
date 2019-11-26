package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Volume;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-26T15:22:56")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile SingularAttribute<Livro, Volume> volume;
    public static volatile SingularAttribute<Livro, Long> isbn;
    public static volatile SingularAttribute<Livro, Long> idLivro;
    public static volatile ListAttribute<Livro, Emprestimo> emprestimos;
    public static volatile SingularAttribute<Livro, String> titulo;
    public static volatile SingularAttribute<Livro, String> materia;
    public static volatile SingularAttribute<Livro, Integer> quantidade;
    public static volatile SingularAttribute<Livro, String> autor;

}