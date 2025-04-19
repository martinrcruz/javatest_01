package cl.test.javauser.annotation;

import cl.test.javauser.annotation.validator.EmailValidator;
import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(java.lang.annotation.ElementType.FIELD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidMail {
    String message() default "El correo electrónico no cumple con los requisitos.";
    Class<?>[] groups() default {};
    Class<? extends java.lang.annotation.ElementType>[] payload() default {};
}
