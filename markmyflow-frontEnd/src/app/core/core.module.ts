import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MissionService } from './services/mission.service';
import { CompanyService } from './services/company.service';
import {  TestService } from './services/test.service';

import { UserService } from './services/user.service';
import { ApiUrlInjectionInterceptor } from './interceptors/api-url-injection.interceptor';
import { WorkflowService } from './services/workflow.service';
import { BackService } from './services/back.service';
import { DocumentService } from './services/document.service';
import { TaskService } from './services/task.service';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { IntentsService } from './services/intents.service';
import { ChatService } from './services/chat.service';
import { ChatproviderService } from '../features/chatbot/providers/chatprovider.service';
import { SubscriptionService } from './services/subscription.service';
import { ReponseService } from './services/reponse.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  providers:[
    MissionService,
    UserService,
    CompanyService,
    SubscriptionService,
    TestService,
    BackService,
    WorkflowService,
    DocumentService,
    TaskService,
    IntentsService,
    ReponseService,
    { provide: HTTP_INTERCEPTORS, useClass: ApiUrlInjectionInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    ChatService,

  ],

})
export class CoreModule { }
