import { Routes } from '@angular/router';
import {HomepageComponent} from './components/homepage/homepage.component';
import {LoginpageComponent} from './components/loginpage/loginpage.component';
import {QuestionComponent} from './components/question/question.component';

export const routes: Routes = [
  {path: 'login', component: LoginpageComponent},
  {path: 'game', component: QuestionComponent},
  {path: '', component: HomepageComponent}
];
