package hu.unideb.inf.survey.domain.repository;

import hu.unideb.inf.survey.domain.entity.SelectedAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedAnswerRepository extends CrudRepository<SelectedAnswer, Long> {
}
