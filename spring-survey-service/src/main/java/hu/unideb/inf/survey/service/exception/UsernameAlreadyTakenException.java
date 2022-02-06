package hu.unideb.inf.survey.service.exception;

public class UsernameAlreadyTakenException extends UserRegistrationException{
    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
