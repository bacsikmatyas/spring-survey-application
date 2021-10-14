package hu.unideb.inf.survey;

import hu.unideb.inf.survey.domain.entity.Survey;
import hu.unideb.inf.survey.domain.entity.User;
import hu.unideb.inf.survey.domain.repository.QuestionAnswerRepository;
import hu.unideb.inf.survey.domain.repository.SelectedAnswerRepository;
import hu.unideb.inf.survey.domain.repository.SurveyRepository;
import hu.unideb.inf.survey.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringSurveyApplication implements CommandLineRunner{

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(SpringSurveyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Test");

		UserRepository userRepository = context.getBean(UserRepository.class);
		SurveyRepository surveyRepository = context.getBean(SurveyRepository.class);
		SelectedAnswerRepository selectedAnswerRepository = context.getBean(SelectedAnswerRepository.class);

		Iterable<User> users = userRepository.findAll();
		System.out.println(users);

		Iterable<Survey> surveys = surveyRepository.findAll();
		System.out.println(surveys);

		User user = userRepository.findById(1L).get();

		System.out.println("User surveys: "+user.getSurveys());

		Survey survey = surveyRepository.findById(1L).get();

		System.out.println("Survey questions: "+survey.getQuestions());

		User test_john = userRepository.findUserByName("Test John");
		System.out.println(test_john);

		System.out.println(selectedAnswerRepository.findSelectedAnswersByAnswer_Question_Survey_Id(1L));
		System.out.println(selectedAnswerRepository.countSelectedAnswersByAnswer_Question_Survey_Id(1L));
		System.out.println(selectedAnswerRepository.countSelectedAnswersByAnswer_Id(1L));

	}
}
