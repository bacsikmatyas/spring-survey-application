package hu.unideb.inf.survey.service.exception;

public class UnsavableAnswerException extends RuntimeException{
    public UnsavableAnswerException(String message) {
        super(message);
    }
}
