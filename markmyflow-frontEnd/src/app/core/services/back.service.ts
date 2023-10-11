import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { testvalidation } from '../models/testvalidation';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class BackService {
  private ENDPOINT_QUEST = "/showallvalidation"
  constructor(private httpclient:HttpClient) { 

  }

  gettestt() : Observable<testvalidation[]>
  {
   return this.httpclient.get<testvalidation[]>('/showallvalidation')
  }
}
