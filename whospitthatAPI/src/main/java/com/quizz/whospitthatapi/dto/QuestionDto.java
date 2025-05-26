package com.quizz.whospitthatapi.dto;

import com.quizz.whospitthatapi.entity.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private String questionText;
    private List<String> answers;
    private String goodAnswer;
    private String mediaUrl;
    private QuestionType questionType;
}
