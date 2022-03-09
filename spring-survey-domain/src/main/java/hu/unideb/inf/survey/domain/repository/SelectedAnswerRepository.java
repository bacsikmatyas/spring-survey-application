package hu.unideb.inf.survey.domain.repository;

import hu.unideb.inf.survey.domain.entity.SelectedAnswer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectedAnswerRepository extends CrudRepository<SelectedAnswer, Long> {
    List<SelectedAnswer> findSelectedAnswersByAnswer_Question_Survey_Id(Long id);

    Long countSelectedAnswersByAnswer_Id(Long id);

    List<SelectedAnswer> findSelectedAnswersByAnswer_Id(Long id);

    Long countSelectedAnswersByAnswer_Question_Survey_Id(Long id);

    Double countSelectedAnswerByAnswer_Id(Long id);

    Double countSelectedAnswerByFreetext(String freeText);
}
