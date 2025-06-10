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

  constructor(private questionService: QuestionService) {
  }

  ngOnInit(): void {
        this.currentQuestion = new Question("1","What's this rapper's name ?",["Travis Scott","J.Cole","Kendrick Lamar","Drake"],"answer1","https://snworksceo.imgix.net/asp/a500f30d-5d01-4216-830e-8a87ff0be7d3.sized-1000x1000.jpg?w=1000",QuestionType.BASIC);
    }

}
