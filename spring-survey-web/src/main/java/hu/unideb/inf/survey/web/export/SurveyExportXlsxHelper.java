package hu.unideb.inf.survey.web.export;

import hu.unideb.inf.survey.web.export.model.AnswerToExport;
import hu.unideb.inf.survey.web.export.model.QuestionToExport;
import hu.unideb.inf.survey.web.export.model.SurveyToExport;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SurveyExportXlsxHelper {

    private final XSSFWorkbook workbook;

    public SurveyExportXlsxHelper(SurveyToExport surveyToExport) {
        this.workbook = generateWorkbookFromSurvey(surveyToExport);
    }

    private XSSFWorkbook generateWorkbookFromSurvey(SurveyToExport surveyToExport){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(surveyToExport.getSurveyTitle());
        int rowCounter = 0;
        Row row = sheet.createRow(rowCounter++);
        row.createCell(0).setCellValue(surveyToExport.getNumberOfSurveyTaken());

        for (QuestionToExport question: surveyToExport.getQuestions()){
            Row questionRow = sheet.createRow(rowCounter++);
            questionRow.createCell(0).setCellValue(question.getQuestionText());
            for (AnswerToExport answer: question.getAnswers()){
                Row answerRow = sheet.createRow(rowCounter++);
                answerRow.createCell(0).setCellValue(answer.getAnswerText());
                answerRow.createCell(1).setCellValue(answer.getNumberOfPicks());
            }
        }
        return workbook;
    }

    public void exportCsv(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
