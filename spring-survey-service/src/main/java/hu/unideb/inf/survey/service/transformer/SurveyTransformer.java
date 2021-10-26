package hu.unideb.inf.survey.service.transformer;

import hu.unideb.inf.survey.domain.entity.Survey;
import hu.unideb.inf.survey.domain.entity.SurveyQuestion;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.service.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyTransformer {

    private final UserTransformer userTransformer;

    private final SurveyQuestionTransformer surveyQuestionTransformer;

    @Autowired
    public SurveyTransformer(UserTransformer userTransformer, SurveyQuestionTransformer surveyQuestionTransformer) {
        this.userTransformer = userTransformer;
        this.surveyQuestionTransformer = surveyQuestionTransformer;
    }

    public SurveyDomain from(Survey survey) {
        SurveyDomain surveyDomain = new SurveyDomain();

        UserDomain userDomain = userTransformer.from(survey.getUser());

        surveyDomain.setUser(userDomain);
        surveyDomain.setId(survey.getId());
        surveyDomain.setTitle(survey.getTitle());
        surveyDomain.setDescription(survey.getDescription());
        surveyDomain.setType(survey.getType());
        surveyDomain.setOpen(survey.getOpen());

        List<SurveyQuestionDomain> surveyQuestionDomains = new ArrayList<>();
        for (SurveyQuestion question: survey.getQuestions()) {
            SurveyQuestionDomain tmp = surveyQuestionTransformer.from(question);
            tmp.setSurvey(surveyDomain);
            surveyQuestionDomains.add(tmp);
        }
        surveyDomain.setQuestions(surveyQuestionDomains);

        return surveyDomain;
    }
}
