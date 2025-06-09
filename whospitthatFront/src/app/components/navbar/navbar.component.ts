import { Component } from '@angular/core';
import {VarstorageService} from '../../services/varstorage.service';
import {NgIf} from '@angular/common';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  imports: [
    NgIf
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  constructor(protected varstorageService: VarstorageService, protected authService:AuthService) {
  }

}
