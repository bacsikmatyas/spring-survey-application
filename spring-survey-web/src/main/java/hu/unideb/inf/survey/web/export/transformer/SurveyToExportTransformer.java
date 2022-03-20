package hu.unideb.inf.survey.web.export.transformer;

import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.export.model.QuestionToExport;
import hu.unideb.inf.survey.web.export.model.SurveyToExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyToExportTransformer {

    private final QuestionToExportTransformer questionToExportTransformer;


    @Autowired
    public SurveyToExportTransformer(QuestionToExportTransformer questionToExportTransformer) {
        this.questionToExportTransformer = questionToExportTransformer;
    }

    public SurveyToExport from(SurveyDomain surveyDomain){
        SurveyToExport surveyToExport = new SurveyToExport();
        surveyToExport.setSurveyTitle(surveyDomain.getTitle());
        surveyToExport.setNumberOfSurveyTaken(surveyDomain.getSurveyTaken());

        List<QuestionToExport> questions = new ArrayList<>();
        for (SurveyQuestionDomain questionDomain: surveyDomain.getQuestions()){
            questions.add(questionToExportTransformer.from(questionDomain));
        }
        surveyToExport.setQuestions(questions);

        return surveyToExport;
    }
}
