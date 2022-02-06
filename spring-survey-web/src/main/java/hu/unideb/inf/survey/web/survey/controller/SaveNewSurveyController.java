package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.service.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SaveNewSurveyController {
    private static final String REQUEST_MAPPING = "/saveNewSurvey";

    private final SurveyService surveyService;

    @Autowired
    public SaveNewSurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String saveNewSurveyController(@ModelAttribute(name = "surveyTitle") String surveyTitle,
                                          @ModelAttribute(name = "surveyDescription") String surveyDescription,
                                          @ModelAttribute(name = "surveyType") String surveyType,
                                          @CookieValue("currentUserId") Long currentUserId){
        SurveyDomain surveyDomain = new SurveyDomain();
        surveyDomain.setTitle(surveyTitle);
        surveyDomain.setDescription(surveyDescription);
        surveyDomain.setType(surveyType);
        surveyDomain.setOpen(true);
        UserDomain userDomain = new UserDomain();
        userDomain.setId(currentUserId);
        System.out.println("Current user "+ currentUserId);
        surveyDomain.setUser(userDomain);

        surveyService.saveNewSurvey(surveyDomain);

        return "redirect:/mySurveys";
    }
}
