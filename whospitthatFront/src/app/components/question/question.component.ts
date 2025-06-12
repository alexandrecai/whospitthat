import {Component, OnInit} from '@angular/core';
import {QuestionService} from '../../services/question.service';
import {Question} from '../../models/question';
import {NgIf, NgOptimizedImage} from '@angular/common';
import {QuestionType} from '../../models/questionType';

@Component({
  selector: 'app-question',
  imports: [
    NgIf,
    NgOptimizedImage
  ],
  templateUrl: './question.component.html',
  styleUrl: './question.component.scss'
})
export class QuestionComponent implements OnInit {

  currentQuestion!: Question;
  nextQuestions!: Question[];
  previousQuestions!: Question[];
  currentScore: number = 0;

  constructor(private questionService: QuestionService) {
  }

  ngOnInit(): void {
    this.nextQuestions = [];
    this.currentQuestion = new Question("","",[],"","",QuestionType.BASIC);
    this.questionService.getRandomsQuestions(10).subscribe(
      (response) => {
        let answers;
        for (let question of response) {
          answers = [];
          for (let answer of question["answers"]) {
            answers.push(answer);
          }
          this.nextQuestions.push(new Question(question["id"],question["questionText"],answers,question["goodAnswer"],question["mediaUrl"],question["questionType"]));

        }
        this.currentQuestion = this.nextQuestions.pop()!;
      }
    );
  }
}
