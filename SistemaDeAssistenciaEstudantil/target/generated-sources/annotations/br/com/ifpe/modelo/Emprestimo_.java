package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Livro;
import br.com.ifpe.modelo.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-08T23:46:57")
@StaticMetamodel(Emprestimo.class)
public class Emprestimo_ { 

    public static volatile SingularAttribute<Emprestimo, Long> idEmprestimo;
    public static volatile SingularAttribute<Emprestimo, Livro> livro;
    public static volatile SingularAttribute<Emprestimo, Date> dataEntrega;
    public static volatile SingularAttribute<Emprestimo, Usuario> usuario;
    public static volatile SingularAttribute<Emprestimo, Date> dataDevolucao;
    public static volatile SingularAttribute<Emprestimo, String> status;

}