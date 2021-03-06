import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { TokenService } from '../services/token.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
  result!: any;
  score: number = 0;
  total: number = 0;
  startDate!: string;
  loading: boolean = true;

  constructor(private route: ActivatedRoute,
    private token: TokenService,
    private userSer: UserService) { }

  ngOnInit(): void {
    const qid = Number(this.route.snapshot.paramMap.get('id'));
    if (this.token.isLoggedIn()) {
      this.userSer.getQuizResultById(qid).subscribe(
        data => {
          this.result = data;
          const d = new Date(this.result.startDate);
          this.result.startDate = "" + d.getDate() + "/" + d.getMonth() + "/" + d.getFullYear() + " - " + d.getHours() + ":" + d.getMinutes();
          
          this.total = this.result.results.length;
          this.result.results.forEach((q: any) => {
            if (q.correct) {
              this.score += 1;
            }
          });
          this.loading = false;
        }
      );
    }
  }

  getClass(chosen: boolean, correct: boolean): string {
    if (chosen) {
      if (correct) {
        return "green";
      }
      else {
        return "red";
      }
    }

    else {
      return "";
    }
  }

}
