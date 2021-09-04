package hu.unideb.inf.survey.domain.repository;

import hu.unideb.inf.survey.domain.entity.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findSurveyByUser_Id(Long id);

    List<Survey> findSurveysByOpen(boolean open);
}
