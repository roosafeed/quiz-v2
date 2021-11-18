import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

import { TokenService } from '../app/services/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'quiz-v2';
  loggedin!: boolean;
  uname!: string;

  constructor(private token: TokenService, private router: Router){
    router.events.subscribe((e) => {
      if (e instanceof NavigationEnd) {
        this.loggedin = this.token.isLoggedIn();
        this.uname = (this.token.isLoggedIn()) ? this.token.getUser().name : "";
        console.log("nav end");
      }
    });

    // if(!this.token.isLoggedIn()){
    //   this.router.navigate(['login']);
    // }    
  }

  logout() {
    this.token.signOut();
    this.router.navigate(['login']);
  }
}
