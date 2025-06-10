import { Component } from '@angular/core';
import {QuestionService} from '../../services/question.service';
import {QuestionComponent} from '../question/question.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-homepage',
  imports: [
    QuestionComponent
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  startGame(): void {
    this.router.navigate(['/game']);
  }
}
