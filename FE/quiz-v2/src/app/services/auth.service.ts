import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_URL = "http://localhost:8888/api/auth/";

const httpOpts = {
  headers: new HttpHeaders({'content-type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_URL + "signin", {
      username, password
    }, httpOpts);
  }

  //name, email, password
  register(name: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_URL + "signup", {
      name, email, password
    }, httpOpts);
  }
}
