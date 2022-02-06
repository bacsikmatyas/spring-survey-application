package hu.unideb.inf.survey.web.registration.transformer;

import hu.unideb.inf.survey.service.domain.UserDomain;
import hu.unideb.inf.survey.web.registration.model.UserRegistrationDto;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDtoTransformer {

    public UserDomain from(UserRegistrationDto userRegistrationDto) {
        UserDomain userDomain = new UserDomain();
        userDomain.setName(userRegistrationDto.getName());
        userDomain.setPassword(userRegistrationDto.getPassword());
        userDomain.setRole("USER");
        return userDomain;
    }
}
