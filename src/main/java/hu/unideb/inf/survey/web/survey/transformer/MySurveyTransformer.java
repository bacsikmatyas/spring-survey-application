package hu.unideb.inf.survey.web.survey.transformer;

import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.web.survey.model.MySurveyDto;
import org.springframework.stereotype.Component;

@Component
public class MySurveyTransformer {

    public MySurveyDto from(SurveyDomain surveyDomain){
        MySurveyDto mySurveyDto = new MySurveyDto();

        mySurveyDto.setId(surveyDomain.getId());
        mySurveyDto.setTitle(surveyDomain.getTitle());
        mySurveyDto.setDescription(surveyDomain.getDescription());
        mySurveyDto.setType(surveyDomain.getType());

        return mySurveyDto;
    }
}
