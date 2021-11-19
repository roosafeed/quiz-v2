import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AuthInterceptProviders } from './services/auth-intercept.service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { ResultComponent } from './result/result.component';
import { AddComponent } from './admin/add/add.component';
import { QuizComponent } from './quiz/quiz.component';
import { AllComponent } from './admin/all/all.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    ResultComponent,
    AddComponent,
    QuizComponent,
    AllComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    AuthInterceptProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
