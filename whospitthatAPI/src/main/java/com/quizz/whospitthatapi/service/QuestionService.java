package com.quizz.whospitthatapi.service;

import com.quizz.whospitthatapi.dto.QuestionDto;
import com.quizz.whospitthatapi.entity.Question;

import java.util.List;
import java.util.Optional;


public interface QuestionService {

    Question createQuestion(QuestionDto questionDto);
    List<Question> getALlQuestions();
    Optional<Question> getQuestionById(Long id);
    Question updateQuestion(Question question);
    void deleteQuestion(Question question);
    List<Question> getRandomQuestions(int nbOfQuestions);
}
