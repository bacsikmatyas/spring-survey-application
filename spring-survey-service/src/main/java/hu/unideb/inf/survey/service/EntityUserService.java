package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.User;
import hu.unideb.inf.survey.domain.repository.UserRepository;
import hu.unideb.inf.survey.service.domain.UserDomain;
import hu.unideb.inf.survey.service.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityUserService implements UserService{

    private final UserRepository userRepository;

    private final UserTransformer userTransformer;

    @Autowired
    public EntityUserService(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    @Override
    public void registerUser(UserDomain userDomain) {
        User user = userTransformer.from(userDomain);
        userRepository.save(user);
    }
}
