package hu.unideb.inf.survey.domain.repository;

import hu.unideb.inf.survey.domain.entity.QuestionAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends CrudRepository<QuestionAnswer, Long> {
}
