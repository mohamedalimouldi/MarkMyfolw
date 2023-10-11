import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FrontofficeRoutingModule } from './frontoffice-routing.module';
import { HomeComponent } from './home/home.component';
import { FrontofficeComponent } from './frontoffice.component';
import { LayoutModule } from '../../layout/layout.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CoreModule } from 'src/app/core/core.module';
import { ChatbotModule } from 'src/app/features/chatbot/chatbot.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VerifyComponent } from './verify/verify.component';
import { ResetComponent } from './reset/reset.component';
import { HttpClientModule } from '@angular/common/http';




@NgModule({
  declarations: [
    HomeComponent,
    FrontofficeComponent,
    LoginComponent,
    RegisterComponent,
    VerifyComponent,
    ResetComponent,
    
  ],
  imports: [
    CommonModule,
    FrontofficeRoutingModule,
    LayoutModule,
    CoreModule,
    ChatbotModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    
    

  ]
})
export class FrontofficeModule { }
