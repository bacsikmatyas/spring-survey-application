package hu.unideb.inf.survey.web.export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hu.unideb.inf.survey.web.export.model.SurveyToExport;

public class SurveyExportJsonHelper {

    public String exportJson(SurveyToExport surveyToExport){
        Gson gson_pretty = new GsonBuilder().setPrettyPrinting()
                .create();
        return gson_pretty.toJson(surveyToExport);
    }
}
