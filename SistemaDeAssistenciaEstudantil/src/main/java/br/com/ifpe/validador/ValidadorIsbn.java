package br.com.ifpe.validador;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidadorIsbn implements ConstraintValidator<ValidaIsbn, Long>{
private final String meuPadrao = "[0-9]{11,13}";
@Override
public boolean isValid(Long valor, ConstraintValidatorContext context){
	return valor.toString().matches(meuPadrao)? true: false;
}

    @Override
    public void initialize(ValidaIsbn a) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}