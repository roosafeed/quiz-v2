import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const BASE = "http://localhost:8888/api/user";

const httpOpts = {
  headers: new HttpHeaders({'content-type': 'application/json'})
};

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

  startNewQuiz(): Observable<any> {
    const p = {
      "startDate": new Date()
    }
    return this.http.post(BASE + "/quiz/new", p, httpOpts);
  }

  getAllQuestionIds(qid: number): Observable<any> {
    return this.http.get(BASE + "/quiz/" + qid + "/question/get/all", { responseType: 'json' });
  }

  getQuestionById(qid: number, quesId: number): Observable<any> {
    return this.http.get(BASE + "/quiz/" + qid + "/question/get/" + quesId, { responseType: 'json' });
  }

  postAnser(qid: number, quesId: number, ansId: number): Observable<any> {
    const p = {
      "qid": quesId,
      "cid": ansId
    }
    return this.http.post(BASE + "/quiz/" + qid + "/answer/add", p, httpOpts);
  }
}
