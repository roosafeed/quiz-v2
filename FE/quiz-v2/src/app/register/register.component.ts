import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  name!: string; 
  email!: string;
  password!: string;

  message!: string;

  regLoad: string = "";

  status: number = 0;

  constructor(private auth: AuthService, private route: Router) { }

  ngOnInit(): void {
    this.status = 0;
    this.regLoad = "";
  }

  createRegistration() {   
    this.regLoad = "loading";

    this.auth.register(this.name, this.email, this.password).subscribe(
      data => {
        console.log(data);
        this.status = 1;
        this.regLoad = "";
        this.route.navigate(['/login']);
      },
      err => {
        console.log(err);
        this.status = -1;
        this.message = "Error. User not registered. (" + err.statusText + ")";
        this.regLoad = "";
      }
    );

    console.log("done");
  }

}
