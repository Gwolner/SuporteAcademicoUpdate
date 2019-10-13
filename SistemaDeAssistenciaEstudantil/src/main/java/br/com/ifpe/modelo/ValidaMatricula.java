package br.com.ifpe.modelo;

import br.com.ifpe.modelo.ValidadorMatricula;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorMatricula.class)
@Documented

public @interface ValidaMatricula {

    String message() default "{Aluno.matricula}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
