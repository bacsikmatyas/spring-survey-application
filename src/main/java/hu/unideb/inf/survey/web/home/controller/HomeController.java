package hu.unideb.inf.survey.web.home.controller;

import hu.unideb.inf.survey.domain.entity.User;
import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.web.home.model.HomeModel;
import hu.unideb.inf.survey.web.home.model.SurveyDto;
import hu.unideb.inf.survey.web.home.transformer.SurveyDomainTransformer;
import hu.unideb.inf.survey.web.security.SurveyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private static final String REQUEST_MAPPING = "/";

    private final SurveyService surveyService;

    private final SurveyDomainTransformer surveyDomainTransformer;

    @Autowired
    public HomeController(SurveyService surveyService, SurveyDomainTransformer surveyDomainTransformer) {
        this.surveyService = surveyService;
        this.surveyDomainTransformer = surveyDomainTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String homeController(
            @ModelAttribute("homeModel")HomeModel homeModel,
            HttpServletResponse response){

        User currentUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof SurveyUserDetails){
            SurveyUserDetails userDetails = (SurveyUserDetails) principal;
            currentUser = userDetails.getUser();
        }
        if (currentUser != null){
            homeModel.setName(currentUser.getName());
            Cookie cookie = new Cookie("currentUserId",currentUser.getId()+"");
            response.addCookie(cookie);
        }

        return "home";
    }

    @ModelAttribute("surveys")
    public List<SurveyDto> surveys(){
        List<SurveyDomain> allOpenSurveys = surveyService.findAllOpenSurveys();

        List<SurveyDto> surveyDtos = new ArrayList<>();

        for (SurveyDomain surveyDomain: allOpenSurveys){
            SurveyDto tmp = surveyDomainTransformer.from(surveyDomain);
            surveyDtos.add(tmp);
        }

        return surveyDtos;
    }
}
