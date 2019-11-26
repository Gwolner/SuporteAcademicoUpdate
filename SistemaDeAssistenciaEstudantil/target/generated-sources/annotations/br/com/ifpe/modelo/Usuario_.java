package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Emprestimo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-26T09:16:39")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> nomeUsuario;
    public static volatile SingularAttribute<Usuario, Integer> rg;
    public static volatile SingularAttribute<Usuario, Long> idUsuario;
    public static volatile SingularAttribute<Usuario, String> cpf;
    public static volatile ListAttribute<Usuario, Emprestimo> emprestimos;
    public static volatile SingularAttribute<Usuario, Date> dataNascimento;

}