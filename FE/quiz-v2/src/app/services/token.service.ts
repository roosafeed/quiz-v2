import { Injectable } from '@angular/core';

const T_KEY = "QUIZ-auth-token";
const U_KEY = "QUIZ-auth-user";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  signOut(): void {
    window.sessionStorage.clear();
  }

  saveToken(token: string): void {
    window.sessionStorage.removeItem(T_KEY);
    window.sessionStorage.setItem(T_KEY, token);
  }

  getToken(): string | null {
    return window.sessionStorage.getItem(T_KEY);
  }

  saveUser(user: any): void {
    window.sessionStorage.removeItem(U_KEY);
    window.sessionStorage.setItem(U_KEY, JSON.stringify(user));
  }

  getUser(): any | null {
    const user = window.sessionStorage.getItem(U_KEY);
    if(user) {
      return JSON.parse(user);
    }

    return null;
  }

  isLoggedIn(): boolean {
    if(this.getUser() != null) {
      return true;
    }
    return false;
  }

  isAdmin(): boolean {
    if(this.isLoggedIn()) {
      return this.getUser().roles.includes('ROLE_ADMIN');
    }
    return false;
  }
}
