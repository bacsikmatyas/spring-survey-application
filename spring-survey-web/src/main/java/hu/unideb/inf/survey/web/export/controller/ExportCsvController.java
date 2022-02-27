package hu.unideb.inf.survey.web.export.controller;

import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.web.export.model.AnswerToExport;
import hu.unideb.inf.survey.web.export.model.QuestionToExport;
import hu.unideb.inf.survey.web.export.model.SurveyToExport;
import hu.unideb.inf.survey.web.export.transformer.SurveyToExportTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ExportCsvController {
    private static final String REQUEST_MAPPING = "/exportCsv";

    private final SurveyService surveyService;

    private final SurveyToExportTransformer surveyToExportTransformer;

    @Autowired
    public ExportCsvController(SurveyService surveyService, SurveyToExportTransformer surveyToExportTransformer) {
        this.surveyService = surveyService;
        this.surveyToExportTransformer = surveyToExportTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public void exportCsvController(HttpServletResponse response,
                                    @RequestParam("surveyId") long surveyId) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String timestamp = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=survey" + timestamp + ".csv";
        response.setHeader(headerKey, headerValue);

        SurveyToExport surveyToExport = generateSurveyToExport(surveyId);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] firstRow = {surveyToExport.getSurveyTitle(), surveyToExport.getNumberOfSurveyTaken()+""};
        csvWriter.writeHeader(firstRow);

        String[] questionMapping = {"questionText"};
        String[] answerMapping = {"answerText", "numberOfPicks"};
        for (QuestionToExport question: surveyToExport.getQuestions()){
            csvWriter.write(question, questionMapping);
            for (AnswerToExport answer: question.getAnswers()){
                csvWriter.write(answer, answerMapping);
            }
        }
        csvWriter.close();
    }

    private SurveyToExport generateSurveyToExport(long surveyId){
        return surveyToExportTransformer.from(surveyService.findSurveyById(surveyId));
    }
}
