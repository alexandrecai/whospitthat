import { Injectable } from '@angular/core';
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class VarstorageService {

  errorMessage: string = '';

  // Définition du nom du cookie pour le token et la date d'expiration
  private readonly tokenCookieName = 'token';
  private readonly tokenExpirationCookieName = 'token_expiration';

  constructor(private cookieService: CookieService) { }

  gettoken(): string | null {
    return this.cookieService.get('token');
  }

  setToken(newToken: string | null) {
    if (newToken != null) {
      this.cookieService.set(this.tokenCookieName, newToken);

      // Obtenir la date actuelle
      const currentDate = new Date();

      // Ajouter 1 heure à la date actuelle pour obtenir la nouvelle date d'expiration
      const expirationDate = new Date(currentDate.getTime() + (1000 * 60 * 60)); // Ajoute 1 heure en millisecondes

      // Stocker la date d'expiration du token
      this.cookieService.set(this.tokenExpirationCookieName, expirationDate.toISOString());
    } else {
      this.cookieService.delete(this.tokenCookieName);
      this.cookieService.delete(this.tokenExpirationCookieName);
    }
  }

  errorResponse(error: any, errorMessage: string) {
    this.errorMessage = errorMessage;
  }
}
