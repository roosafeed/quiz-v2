import { Injectable } from '@angular/core';

import { HTTP_INTERCEPTORS, HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { TokenService } from '../services/token.service';
import { Observable } from 'rxjs';

const TOKEN_HDR_KEY = "Authorization";

@Injectable()
export class AuthInterceptService implements HttpInterceptor {

  constructor(private token: TokenService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getToken();
    if(token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HDR_KEY, 'Bearer ' + token) });
    }
    return next.handle(authReq);
  }
}

export const AuthInterceptProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptService, multi: true }
];