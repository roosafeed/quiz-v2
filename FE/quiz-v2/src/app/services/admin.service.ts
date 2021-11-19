import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const BASE = "http://localhost:8888/api/admin";

const httpOpts = {
  headers: new HttpHeaders({'content-type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addQuestion(jsonString: string): Observable<any> {
    return this.http.post(BASE + "/question/add", JSON.parse(jsonString), httpOpts);
  }

  getAllQuestions(): Observable<any> {
    return this.http.get(BASE + "/question/all", { responseType: 'json' });
  }
}
