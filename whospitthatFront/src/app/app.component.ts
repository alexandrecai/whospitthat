import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavbarComponent} from './components/navbar/navbar.component';
import {CookieService} from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent],
  providers: [CookieService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'whospitthatFront';
}
