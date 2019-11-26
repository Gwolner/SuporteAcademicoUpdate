package br.com.ifpe.validador;


import br.com.ifpe.validador.ValidadorIsbn;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;



@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorIsbn.class)
@Documented

public @interface ValidaIsbn{
String message() default "{Livro.isbn}";

Class<?>[] groups() default {};

    /**
     *
     */
Class<? extends Payload>[] payload() default {};
}
