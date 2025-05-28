package com.quizz.whospitthatapi;

import com.quizz.whospitthatapi.dto.SignUpUser;
import com.quizz.whospitthatapi.entity.Question;
import com.quizz.whospitthatapi.entity.QuestionType;
import com.quizz.whospitthatapi.repository.QuestionRepository;
import com.quizz.whospitthatapi.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class WhospitthatApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhospitthatApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(QuestionRepository questionRepository, AuthService authService){
        return args -> {
            for (int i = 0; i<20; i++){
                questionRepository.save(createQuestion(i));
            }

            authService.signUpUser(new SignUpUser("user","user@email.com","password",""));
        };
    }

    Question createQuestion(int i){
        Question question = new Question();
        question.setQuestionType(QuestionType.BASIC);
        question.setGoodAnswer("Answer1");
        question.setMediaUrl("null");
        question.setAnswers(Arrays.asList("Answer1","Answer2","Answer3"));
        question.setQuestionText("Question n°" + i);
        return question;
    }

}
