package cl.test.javauser.annotation.validator;

import cl.test.javauser.annotation.ValidMail;
import org.springframework.beans.factory.annotation.Value;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidMail, String> {

    @Value("${validation.regex.email}")
    private String regex;
    private Pattern pattern;


    @Override
    public void initialize(ValidMail ann) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && pattern.matcher(value).matches();
    }
}
