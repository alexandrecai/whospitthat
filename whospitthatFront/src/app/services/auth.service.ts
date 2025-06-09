import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.development';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {VarstorageService} from './varstorage.service';
import {Router, RouterOutlet} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiurl = environment.apiUrl+'/auth';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient, private varstorage: VarstorageService, private router: Router) { }

  login(email: string, password: string): Observable<any> {
    const credentials = {
      email: email,
      password: password,
    };

    return this.http.post(`${this.apiurl}/signin`, credentials, this.httpOptions)
      .pipe(
        tap({
          next: (response:any) => {
            this.varstorage.setToken(response.token);
            this.router.navigate(['/']).then();
          },
          error: (error) => this.varstorage.errorResponse(error, 'Error during signing in, please check your infos')
        })
      );
  }

  logout(){
    this.varstorage.setToken(null);
  }
}
