package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SaveSurveyController {
    private static final String REQUEST_MAPPING = "/saveSurvey";

    private final SurveyService surveyService;

    @Autowired
    public SaveSurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String saveSurveyController(HttpServletRequest request,
                                       @ModelAttribute(name = "surveyId") Long surveyId,
                                       @ModelAttribute(name = "surveyTitle") String surveyTitle,
                                       @ModelAttribute(name = "surveyDescription") String surveyDescription){
        surveyService.editSurveyTexts(surveyId,surveyTitle,surveyDescription);
        String referer = request.getHeader("Referer");
        String successParam = "&successfulSubmit";
        if (referer.contains(successParam)){
            return "redirect:"+referer;
        }
        else {
            return "redirect:"+referer+successParam;
        }
    }
}
