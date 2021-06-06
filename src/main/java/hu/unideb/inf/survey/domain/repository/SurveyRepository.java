package hu.unideb.inf.survey.domain.repository;

import hu.unideb.inf.survey.domain.entity.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findSurveyByUser_Id(Long id);
}
