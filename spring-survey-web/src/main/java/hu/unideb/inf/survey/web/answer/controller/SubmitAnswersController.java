package hu.unideb.inf.survey.web.answer.controller;

import hu.unideb.inf.survey.service.SelectedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@Controller
public class SubmitAnswersController {
    private static final String REQUEST_MAPPING = "/submitAnswers";

    private static final String HOMEPAGE_WITH_SUCCESS = "/?successfulSubmit";

    private final SelectedAnswerService selectedAnswerService;

    @Autowired
    public SubmitAnswersController(SelectedAnswerService selectedAnswerService) {
        this.selectedAnswerService = selectedAnswerService;
    }

    @PostMapping(REQUEST_MAPPING)
    public String submitAnswersController(@CookieValue("currentUserId") String currentUserIdString,
                                          HttpServletRequest servletRequest) {

        List<Long> selectedIds = getSelectedIds(servletRequest);
        long currentUserId = Long.parseLong(currentUserIdString);
        for (long answerId : selectedIds) {
            String[] freeTexts = servletRequest.getParameterValues(answerId + "");
            String freeText = freeTexts == null ? "" : freeTexts[0];
            selectedAnswerService.saveNewSelectedAnswer(answerId, currentUserId, freeText);
        }
        return "redirect:" + HOMEPAGE_WITH_SUCCESS;
    }

    private List<Long> getSelectedIds(HttpServletRequest servletRequest) {
        List<String> questionParameterNames = getQuestionParameterNames(servletRequest);
        List<String> selectedAnswerIdsAsStringList = getIdsAsStringList(servletRequest, questionParameterNames);
        return getIdsAsLongs(selectedAnswerIdsAsStringList);
    }

    private List<Long> getIdsAsLongs(List<String> selectedAnswerIdsAsStringList) {
        List<Long> selectedAnswerIds = new ArrayList<>();

        for (String stringId : selectedAnswerIdsAsStringList) {
            long id = Long.parseLong(stringId);
            selectedAnswerIds.add(id);
        }
        return selectedAnswerIds;
    }

    private List<String> getIdsAsStringList(HttpServletRequest servletRequest, List<String> questionParameterNames) {
        List<String> selectedAnswerIds = new ArrayList<>();
        for (String parameterName : questionParameterNames) {
            String[] answerIds = servletRequest.getParameterValues(parameterName);
            selectedAnswerIds.addAll(List.of(answerIds));
        }
        return selectedAnswerIds;
    }

    private List<String> getQuestionParameterNames(HttpServletRequest servletRequest) {
        List<String> questionAttributeNames = new ArrayList<>();
        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        for (Iterator<String> it = parameterNames.asIterator(); it.hasNext(); ) {
            String element = it.next();
            if (element.contains("question")) {
                questionAttributeNames.add(element);
            }
        }
        return questionAttributeNames;
    }

}
