package com.quizz.whospitthatapi.service.implementation;

import com.quizz.whospitthatapi.dto.QuestionDto;
import com.quizz.whospitthatapi.entity.Question;
import com.quizz.whospitthatapi.entity.QuestionType;
import com.quizz.whospitthatapi.repository.QuestionRepository;
import com.quizz.whospitthatapi.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(QuestionDto questionDto) {
        Question question = new Question();

        question.setQuestionText(questionDto.getQuestionText());
        question.setAnswers(questionDto.getAnswers());
        question.setGoodAnswer(questionDto.getGoodAnswer());
        question.setMediaUrl(questionDto.getMediaUrl());
        question.setQuestionType(QuestionType.BASIC);

        return questionRepository.save(question);
    }

    @Override
    public List<Question> getALlQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }

    @Override
    public List<Question> getRandomQuestions(int nbOfQuestions){
        List<Question> questions = new ArrayList<>();

        Random rand = new Random();
        List<Question> allQuestions = questionRepository.findAll();

        for (int i = 0; i < nbOfQuestions; i++) {
            int randomIndex = rand.nextInt(allQuestions.size());
            Question randomQuestion = allQuestions.get(randomIndex);
            questions.add(randomQuestion);
            allQuestions.remove(randomIndex);
        }

        return questions;
    }
}
