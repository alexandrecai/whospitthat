import { Component } from '@angular/core';
import {QuestionService} from '../../services/question.service';

@Component({
  selector: 'app-homepage',
  imports: [],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent {

  constructor(private questionService: QuestionService) {
  }

  ngOnInit(): void {
    this.questionService.getQuestionById("2").subscribe();
  }
}
