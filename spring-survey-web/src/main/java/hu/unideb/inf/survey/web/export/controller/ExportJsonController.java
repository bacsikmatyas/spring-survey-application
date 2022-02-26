package hu.unideb.inf.survey.web.export.controller;

import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.web.export.SurveyExportJsonHelper;
import hu.unideb.inf.survey.web.export.model.SurveyToExport;
import hu.unideb.inf.survey.web.export.transformer.SurveyToExportTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ExportJsonController {
    private static final String REQUEST_MAPPING = "/exportJson";

    private final SurveyService surveyService;

    private final SurveyToExportTransformer surveyToExportTransformer;

    @Autowired
    public ExportJsonController(SurveyService surveyService, SurveyToExportTransformer surveyToExportTransformer) {
        this.surveyService = surveyService;
        this.surveyToExportTransformer = surveyToExportTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public ResponseEntity<byte[]> exportJsonController(@RequestParam("surveyId") long surveyId){
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String timestamp = dateFormatter.format(new Date());

        SurveyToExport surveyToExport = generateSurveyToExport(surveyId);
        SurveyExportJsonHelper surveyExportJsonHelper = new SurveyExportJsonHelper();
        String surveyJson = surveyExportJsonHelper.exportJson(surveyToExport);
        byte[] surveyJsonBytes = surveyJson.getBytes();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=survey" + timestamp + ".json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(surveyJsonBytes.length)
                .body(surveyJsonBytes);
    }

    private SurveyToExport generateSurveyToExport(long surveyId){
        return surveyToExportTransformer.from(surveyService.findSurveyById(surveyId));
    }
}
