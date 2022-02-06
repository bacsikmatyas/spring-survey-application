package hu.unideb.inf.survey.web.question.transformer;

import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.question.model.QuestionStatisticDto;
import hu.unideb.inf.survey.web.answer.transformer.AnswerStatisticDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionStatisticDtoTransformer {

    private final AnswerStatisticDtoTransformer answerStatisticDtoTransformer;

    @Autowired
    public QuestionStatisticDtoTransformer(AnswerStatisticDtoTransformer answerStatisticDtoTransformer) {
        this.answerStatisticDtoTransformer = answerStatisticDtoTransformer;
    }

    public List<QuestionStatisticDto> from(List<SurveyQuestionDomain> questions, Long numberOfSurveyTaken){
        List<QuestionStatisticDto> questionStatistics = new ArrayList<>();

        for (SurveyQuestionDomain question: questions){
            QuestionStatisticDto questionStatistic = new QuestionStatisticDto();
            questionStatistic.setQuestionText(question.getQuestionText());
            questionStatistic.setAnswerStatistics(answerStatisticDtoTransformer.from(question.getAnswers(), numberOfSurveyTaken));
            questionStatistics.add(questionStatistic);
        }

        return questionStatistics;
    }
}
