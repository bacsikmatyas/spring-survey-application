package hu.unideb.inf.survey.domain.repository;

import hu.unideb.inf.survey.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
