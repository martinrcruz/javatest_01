package cl.test.javauser.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super("El correo/email -> " + email + " no esta disponible. Por favor, intentelo nuevamente con una dirección que no esté en uso.");
    }
}
