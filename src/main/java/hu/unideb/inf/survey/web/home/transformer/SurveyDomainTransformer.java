package hu.unideb.inf.survey.web.home.transformer;

import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.service.domain.UserDomain;
import hu.unideb.inf.survey.web.home.model.SurveyDto;
import org.springframework.stereotype.Component;

@Component
public class SurveyDomainTransformer {
    public SurveyDto from(SurveyDomain surveyDomain){
        SurveyDto surveyDto = new SurveyDto();

        surveyDto.setId(surveyDomain.getId());
        surveyDto.setTitle(surveyDomain.getTitle());
        surveyDto.setDescription(surveyDomain.getDescription());
        surveyDto.setType(surveyDomain.getType());

        UserDomain user = surveyDomain.getUser();
        surveyDto.setAuthorName(user.getName());

        return surveyDto;
    }
}
