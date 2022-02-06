package hu.unideb.inf.survey.service.exception;

public class EmptyRegistrationFieldException extends UserRegistrationException{
    public EmptyRegistrationFieldException(String message) {
        super(message);
    }
}
