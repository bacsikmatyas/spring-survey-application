package hu.unideb.inf.survey.service.exception;

public class PasswordComplexityException extends UserRegistrationException{
    public PasswordComplexityException(String message) {
        super(message);
    }
}
