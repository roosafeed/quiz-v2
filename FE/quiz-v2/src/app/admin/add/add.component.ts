import { Component, OnInit } from '@angular/core';

import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  message: string = "";  
  que: string = "";
  ans: Choice[] = [];

  constructor(private adminSer: AdminService) {
   }

  ngOnInit(): void {
  }

  addQuestion() {   
    var flag: number = 0;
    this.ans.forEach(c => {
      if(c.isAnswer == "y") {
        flag++;
      }
    });

    if(flag != 1) {
      this.message = "Select ONE choice";
    }
    else {
      const question: Question = new Question(this.que, this.ans);
      this.adminSer.addQuestion(JSON.stringify(question)).subscribe(
        data => {
          this.message = data.message;
          if(data.message == "Question added") {
            this.que = "";
            this.ans = [];
          }
          
        },
        err => {
          this.message = "Error adding question";
        }
      )
    }
  }

  addChoice() {   
    this.ans.push(new Choice('', 'n'));
  }

  delChoice(i: number) {
    this.ans = this.ans.slice(0, i).concat(this.ans.slice(i+1));
  }

  radioSelect(i: number) {
    this.ans.forEach(c => {
      c.isAnswer = 'n';
    });

    this.ans[i].isAnswer = 'y';
  }

}

class Choice {
  name: string;
  isAnswer: string;

  constructor(n: string, ans: string) {
    this.name = n;
    this.isAnswer = (ans == "y") ? "y" : "n";
  }
}

class Question {
  question: string;
  choices: Choice[];

  constructor(q: string, cs: Choice[]) {
    this.question = q;
    this.choices = cs;
  }

  addChoice(c: Choice) {
    this.choices.push(c);
  }
}
