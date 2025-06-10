import { Component } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {VarstorageService} from '../../services/varstorage.service';
import {FormsModule, NgForm} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-loginpage',
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.scss'
})
export class LoginpageComponent {

  isLogin = true;

  constructor(private authService: AuthService, private varstorageService: VarstorageService) {
  }

  ngOnInit(){
  }

  onSubmitSignin(form: NgForm){
    this.authService.login(form.value.email, form.value.password).subscribe();
    console.log(this.varstorageService.gettoken());
  }

  switchAuthMode(){
    this.isLogin = !this.isLogin;
  }

  onSubmitSignup(form: NgForm){
    this.authService.register(form.value.name,form.value.email, form.value.password,"").subscribe();
  }
}
