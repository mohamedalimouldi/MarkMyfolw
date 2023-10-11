import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { question } from '../models/question';
@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private ENDPOINT_QUEST = "/showallvalidation"

  constructor(private httpclient:HttpClient) { 

  }
 // getQuest() : Observable<[]>
   //{
   // return this.httpclient.get<question[]>(this.API_URL+this.ENDPOINT_QUEST)
   //}
}
