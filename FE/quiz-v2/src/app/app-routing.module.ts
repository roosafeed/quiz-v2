import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from '../app/login/login.component';
import { HomeComponent } from '../app/home/home.component';
import { RegisterComponent } from '../app/register/register.component';
import { ResultComponent } from '../app/result/result.component';

import { LoggedInGuard } from '../app/services/logged-in.guard';

const routes: Routes = [
  {path: 'home', component: HomeComponent, canActivate: [LoggedInGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'result/:id', component: ResultComponent, canActivate: [LoggedInGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
