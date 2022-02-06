package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.repository.UserRepository;
import hu.unideb.inf.survey.service.domain.UserDomain;
import hu.unideb.inf.survey.service.exception.EmptyRegistrationFieldException;
import hu.unideb.inf.survey.service.exception.PasswordComplexityException;
import hu.unideb.inf.survey.service.exception.PasswordConfirmationException;
import hu.unideb.inf.survey.service.exception.UsernameAlreadyTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private static final String USERNAME_TAKEN_MESSAGE = "Username already taken!";
    private static final String PASSWORD_COMPLEXITY_MESSAGE = "Password should consist of minimum 8 characters, at least 1 letter and 1 number.";
    private static final String PASSWORD_CONFIRMATION_MESSAGE = "Passwords do not match!";
    private static final String EMPTY_FIELD_MESSAGE = "All field must be filled!";

    private UserRepository userRepository;

    @Autowired
    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUserData(String name) {
        if (userRepository.existsUserByName(name)) {
            throw new UsernameAlreadyTakenException(USERNAME_TAKEN_MESSAGE);
        }
    }

    public void validatePasswordComplexity(String password) {
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            throw new PasswordComplexityException(PASSWORD_COMPLEXITY_MESSAGE);
        }
    }

    public void validatePasswordConfirmation(String firstPassword, String secondPassword) {
        if (!firstPassword.equals(secondPassword)) {
            throw new PasswordConfirmationException(PASSWORD_CONFIRMATION_MESSAGE);
        }
    }

    public void checkForEmpties(UserDomain userDomain) {
        if (userDomain.getName().isEmpty() || userDomain.getPassword().isEmpty()) {
            throw new EmptyRegistrationFieldException(EMPTY_FIELD_MESSAGE);
        }
    }
}
