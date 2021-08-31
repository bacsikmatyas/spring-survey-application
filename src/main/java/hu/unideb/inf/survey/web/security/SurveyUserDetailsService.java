package hu.unideb.inf.survey.web.security;

import hu.unideb.inf.survey.domain.entity.User;
import hu.unideb.inf.survey.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SurveyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public SurveyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(s);

        return new SurveyUserDetails(user);
    }
}
