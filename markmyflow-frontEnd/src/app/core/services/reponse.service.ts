import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { reponse } from '../models/reponse';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ReponseService {

  constructor(private httpclient:HttpClient) { }

 // getrep() : Observable<reponse[]>
   //{
    //return this.httpclient.get<reponse[]>('/testdevalidation/reponses')
   //}
   saverep(quiz: any): Observable<reponse>
  {
    return this.httpclient.post<reponse>('/testdevalidation/addreponse',quiz );
  }
  addPassage(iduser,idv): Observable<reponse>
  {
    return this.httpclient.post<reponse>('/testdevalidation/addpassage/'+iduser+'/'+idv,{} );
  }
}
