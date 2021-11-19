import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from '../app/login/login.component';
import { HomeComponent } from '../app/home/home.component';
import { RegisterComponent } from '../app/register/register.component';
import { ResultComponent } from '../app/result/result.component';
import { AddComponent } from '../app/admin/add/add.component';
import { QuizComponent } from '../app/quiz/quiz.component';

import { LoggedInGuard } from '../app/services/logged-in.guard';
import { AdminGuard } from '../app/services/admin.guard';

const routes: Routes = [
  {path: 'home', component: HomeComponent, canActivate: [LoggedInGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'result/:id', component: ResultComponent, canActivate: [LoggedInGuard]},
  {path: 'quiz/:id', component: QuizComponent, canActivate: [LoggedInGuard]},
  {path: 'admin/add', component: AddComponent, canActivate: [AdminGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
