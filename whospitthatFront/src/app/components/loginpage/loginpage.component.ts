import { Component } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {VarstorageService} from '../../services/varstorage.service';

@Component({
  selector: 'app-loginpage',
  imports: [],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.scss'
})
export class LoginpageComponent {

  constructor(private authService: AuthService) {
  }

  ngOnInit(){
  }
}
