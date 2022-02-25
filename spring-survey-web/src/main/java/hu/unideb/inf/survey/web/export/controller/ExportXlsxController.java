package hu.unideb.inf.survey.web.export.controller;

import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.web.export.SurveyExportXlsxHelper;
import hu.unideb.inf.survey.web.export.model.SurveyToExport;
import hu.unideb.inf.survey.web.export.transformer.SurveyToExportTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ExportXlsxController {
    private static final String REQUEST_MAPPING = "/exportXlsx";

    private final SurveyService surveyService;

    private final SurveyToExportTransformer surveyToExportTransformer;

    @Autowired
    public ExportXlsxController(SurveyService surveyService, SurveyToExportTransformer surveyToExportTransformer) {
        this.surveyService = surveyService;
        this.surveyToExportTransformer = surveyToExportTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public void exportXlsxController(HttpServletResponse response,
                                     @RequestParam("surveyId") long surveyId) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String timestamp = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=survey" + timestamp + ".xlsx";
        response.setHeader(headerKey, headerValue);

        SurveyToExport surveyToExport = generateSurveyToExport(surveyId);
        SurveyExportXlsxHelper surveyExportXlsxHelper = new SurveyExportXlsxHelper(surveyToExport);
        surveyExportXlsxHelper.exportCsv(response);
    }

    private SurveyToExport generateSurveyToExport(long surveyId){
        return surveyToExportTransformer.from(surveyService.findSurveyById(surveyId));
    }
}
