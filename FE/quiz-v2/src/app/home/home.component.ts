import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

import { UserService } from '../services/user.service';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  loggedin: boolean = false;
  quizes!: any[];

  constructor(private userSer: UserService,
    private token: TokenService,
    private router: Router) {    
            
      userSer.getAllQuizResults().subscribe(
        data => {
          this.quizes = data;
          this.quizes.forEach(q => {
            const d = new Date(q.startDate);
            q.startDate = "" + d.getDate() + "/" + d.getMonth() + "/" + d.getFullYear();
          });
          console.log(this.quizes);
        },
        err => {
          console.log("error: " + err.statusText);
        }
      );
  }

  ngOnInit(): void {
    
  }

}
