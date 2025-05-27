package com.quizz.whospitthatapi.controller;

import com.quizz.whospitthatapi.dto.QuestionDto;
import com.quizz.whospitthatapi.dto.SignUpUser;
import com.quizz.whospitthatapi.entity.Question;
import com.quizz.whospitthatapi.entity.User;
import com.quizz.whospitthatapi.exception.UserAlreadyExistException;
import com.quizz.whospitthatapi.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/create")
    private ResponseEntity<Question> createQuestion(@RequestBody QuestionDto questionDto){
        Question question = questionService.createQuestion(questionDto);
        return ResponseEntity.created(URI.create("/api/question/"+question.getId())).body(question);
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> getAllQuestions(){
        if(questionService.getALlQuestions().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(questionService.getALlQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") Long id){
        Optional<Question> question = questionService.getQuestionById(id);
        return question.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") Long id, @RequestBody Question question){
        if(questionService.getQuestionById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Question updatedQuestion = questionService.updateQuestion(question);
        return ResponseEntity.ok().body(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        Optional<Question> question = questionService.getQuestionById(id);

        if(question.isPresent()){
            questionService.deleteQuestion(question.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/random")
    public ResponseEntity<List<Question>> getRandomQuestions(@RequestParam int number){
        List<Question> questions = questionService.getRandomQuestions(number);
        return ResponseEntity.ok().body(questions);
    }
}
