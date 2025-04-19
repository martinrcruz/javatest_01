package cl.test.javauser.exception;

public class MissingUserDataException extends RuntimeException{
    public MissingUserDataException(String field) {
        super("El campo " + field + " es requerido");
    }
}
