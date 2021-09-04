package hu.unideb.inf.survey.service.transformer;

import hu.unideb.inf.survey.domain.entity.User;
import hu.unideb.inf.survey.service.domain.UserDomain;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {
    public UserDomain from(User user){
        UserDomain userDomain = new UserDomain();

        userDomain.setId(user.getId());
        userDomain.setName(user.getName());
        userDomain.setPassword(user.getPassword());
        userDomain.setRole(user.getRole());

        return userDomain;
    }
}
