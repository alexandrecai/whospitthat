import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.development';
import {Observable, tap} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private apiurl = environment.apiUrl+'/question';

  constructor(private http: HttpClient) { }

  getQuestionById(questionId: string): Observable<any> {
    const apiUrlWithId = `${this.apiurl}/${questionId}`
    return this.http.get(apiUrlWithId)
      .pipe(
        tap({
          next: (response: any) => {
            console.log(response);
          }
        })
      );
  }

  getRandomsQuestions(nbOfQuestions: number): Observable<any> {
    const apiUrlWithId = `${this.apiurl}/random`
    return this.http.get(apiUrlWithId,{
      params: {
        number: nbOfQuestions,
      }
    })
  }
}
