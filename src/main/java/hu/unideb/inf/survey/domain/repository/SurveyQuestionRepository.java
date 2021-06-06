package hu.unideb.inf.survey.domain.repository;

import hu.unideb.inf.survey.domain.entity.SurveyQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyQuestionRepository extends CrudRepository<SurveyQuestion, Long> {
}
