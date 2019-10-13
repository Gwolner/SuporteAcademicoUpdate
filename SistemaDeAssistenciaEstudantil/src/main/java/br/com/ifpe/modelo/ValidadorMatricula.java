package br.com.ifpe.modelo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidadorMatricula implements ConstraintValidator<ValidaMatricula, String> {

    private String meuPadrao = "[0-9]{4}[A-Z]{4}";

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor.matches(meuPadrao) ? true : false;
    }

    @Override
    public void initialize(ValidaMatricula a) { }
}
