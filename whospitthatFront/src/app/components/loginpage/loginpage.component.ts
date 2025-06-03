import { Component } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {VarstorageService} from '../../services/varstorage.service';
import {FormsModule, NgForm} from '@angular/forms';

@Component({
  selector: 'app-loginpage',
  imports: [
    FormsModule
  ],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.scss'
})
export class LoginpageComponent {

  constructor(private authService: AuthService, private varstorageService: VarstorageService) {
  }

  ngOnInit(){
  }

  onSubmit(form: NgForm){
    this.authService.login(form.value.email, form.value.password).subscribe();
    console.log(this.varstorageService.gettoken());
  }
}
