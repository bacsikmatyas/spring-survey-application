package hu.unideb.inf.survey.domain.repository;

import hu.unideb.inf.survey.domain.entity.Survey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findSurveyByUser_Id(Long id);

    List<Survey> findSurveysByOpen(boolean open);

    List<Survey> findSurveysByUser_Id(Long id);

    Survey findSurveyById(Long id);

    @Modifying
    @Query(value = "UPDATE SURVEY SET SURVEY_TAKEN = SURVEY_TAKEN + 1 WHERE ID = ?1", nativeQuery = true)
    void increaseTakenCount(Long surveyId);
}
