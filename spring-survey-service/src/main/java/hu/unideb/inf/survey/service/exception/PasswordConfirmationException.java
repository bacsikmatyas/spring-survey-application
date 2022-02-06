package hu.unideb.inf.survey.service.exception;

public class PasswordConfirmationException extends UserRegistrationException{
    public PasswordConfirmationException(String message) {
        super(message);
    }
}
