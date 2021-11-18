import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../services/auth.service';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  uname!: string;
  password!: string;

  message!: string;
  status: number = 0;
  load: string = "";

  constructor(
    private auth: AuthService,
    private token: TokenService,
    private route: Router
  ) { }

  ngOnInit(): void {
    this.status = 0;
    this.load = "";
  }

  onLogin() {
    this.load = "loading";
    this.auth.login(this.uname, this.password).subscribe(
      data => {
        //save token and user
        this.token.saveToken(data.accessToken);
        this.token.saveUser(data);
        this.route.navigate(['home']);
      },
      err => {
        this.message = (err.error.message == "Error: Unauthorized") ? "Incorrect Username/Password" : err.error.message;
        this.status = -1;
        this.load = "";
      }
    );
  }

}
