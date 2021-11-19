import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  loggedin: boolean = false;
  quizes!: any[];

  constructor(private userSer: UserService,
    private router: Router) {    
            
      userSer.getAllQuizResults().subscribe(
        data => {
          this.quizes = data;
          this.quizes.forEach(q => {
            const d = new Date(q.startDate);
            q.startDate = "" + d.getDate() + "/" + d.getMonth() + "/" + d.getFullYear();
          });
        },
        err => {
          console.log("error: " + err.statusText);
        }
      );
  }

  newQuiz() {
    this.userSer.startNewQuiz().subscribe(
      data => {
        this.router.navigate(['/quiz', data]);
      },
      err => {
        console.log("Error: " + err.statusText);
      }
    )
  }

}
