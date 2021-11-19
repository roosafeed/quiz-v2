import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { UserService } from '../services/user.service';

const KEY = "QUIZ-QID";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  total: number = 0;
  quizId: number = -1;
  cid: number = -1;
  qids: any = {
    currIndex: -1,
    qid: []
  }
  quiz: any;
  loading: boolean = false;
  nextBtn!: string;

  constructor(private route: ActivatedRoute,
    private userSer: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.quizId = Number(this.route.snapshot.paramMap.get('id'));
    this.userSer.getAllQuestionIds(this.quizId).subscribe(
      data => {
        this.total = data.length;
        this.qids.qid = data;
        console.log(this.qids);
      },
      err => {
        console.log("Error: " + err.statusText);
      }
    );
  }

  startQuiz() {
    if(this.quizId != -1) {
      this.loadQuestion(0);
    }
  }

  next() {
    if(this.cid != -1) {
      this.userSer.postAnser(this.quizId, this.quiz.id, this.cid).subscribe(
        data => {
          if(this.qids.currIndex + 1 < this.total) {
            this.loadQuestion(this.qids.currIndex + 1);        
          }          
          else {
            this.router.navigate(['/result', this.quizId]);
          }          
        },
        err => {
          console.log("Error: " + err.statusText);
        }
      );      
    }
  }

  prev() {
    if(this.cid != -1) {
      this.userSer.postAnser(this.quizId, this.quiz.id, this.cid).subscribe(
        data => {
          if(this.qids.currIndex - 1 >= 0) {
            this.loadQuestion(this.qids.currIndex - 1);        
          } 
        },
        err => {
          console.log("Error: " + err.statusText);
        }
      );      
    }
  }


  loadQuestion(i: number) {
    this.loading = true;
    this.userSer.getQuestionById(this.quizId, this.qids.qid[i]).subscribe(
      data => {
        this.quiz = data;
        this.loading = false;
        this.qids.currIndex = i;
        this.cid = -1;
        if(this.qids.currIndex + 1 == this.total) {
            this.nextBtn = "Finish";
        }
        else {
          this.nextBtn = "Next >";
        }
      }
    );
  }

}
