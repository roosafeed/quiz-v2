import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const BASE = "http://localhost:8888/api/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAllQuizResults(): Observable<any> {
    return this.http.get(BASE + "/quiz/result/all", { responseType: 'json' })
  }

  getQuizResultById(id: number): Observable<any> {
    return this.http.get(BASE + "/quiz/" + id + "/result", { responseType: 'json' })
  }
}
