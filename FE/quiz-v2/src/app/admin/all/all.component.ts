import { Component, OnInit } from '@angular/core';

import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-all',
  templateUrl: './all.component.html',
  styleUrls: ['./all.component.css']
})
export class AllComponent implements OnInit {
  ques: any[] = [];
  loading: boolean = true;

  constructor(private  adminSer: AdminService) { }

  ngOnInit(): void {
    this.adminSer.getAllQuestions().subscribe(
      data => {
        this.ques = data;
        this.loading = false;
      },
      err => {
        console.log("Error: " + err.statusText);
      }
    )
  }

}
