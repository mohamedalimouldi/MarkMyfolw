import { Injectable } from '@angular/core';
import { HttpClient, HttpEventType, HttpHeaders, HttpParams } from '@angular/common/http'
import { Observable, of } from 'rxjs';
import { testvalidation } from '../models/testvalidation';
import { question } from '../models/question';

import { map } from 'rxjs/operators'
import { passage } from '../models/passage';


@Injectable({
  providedIn: 'root'
})
export class TestService {
  private ENDPOINT_QUEST = "/showallvalidation"

  constructor(private httpclient:HttpClient) { 

  }
  gettestt() : Observable<testvalidation[]>
   {
    return this.httpclient.get<testvalidation[]>('/testdevalidation/showallvalidation')
   }
   gettestbyid(id:number ) {
    console.log("getbyid :",id);
    return this.httpclient.get<testvalidation[]>(`/testdevalidation/showvalidation/${id}`)
  }
  getpassagebyid(id:number ) {
    console.log("getbyid :",id);
    return this.httpclient.get<passage[]>(`/testdevalidation/getpassagebyid/${id}`)
  }


  savequiz(quiz: testvalidation): Observable<testvalidation>
  {
    console.log(quiz)
    return this.httpclient.post<testvalidation>('/testdevalidation/addvalidation',quiz );
  }

  //updatequiz(id: number): Observable<testvalidation>{

    //return this.httpclient.get<testvalidation>(`/updatevalidation/${id}`).pipe(map(response => response));
    //return this.httpclient.put<testvalidation>(`/updatevalidation/${id}`);

  
     //}
     updatequiz(data: testvalidation): Observable<testvalidation> {
  return this.httpclient.put<testvalidation>('/testdevalidation/updatevalidation', data);
     }


     delete(id:string){
      return this.httpclient.delete(`/testdevalidation/deletevalidation/${id}`,{responseType:"text"})
    }

    deletequest(id:string){
      return this.httpclient.delete(`/testdevalidation/deletequestion/${id}`,{responseType:"text"})
    }


    getquest() : Observable<question[]>
   {
    return this.httpclient.get<question[]>('/testdevalidation/showallquestion')
   }
   savequestion(qu: question): Observable<question>
   {
     return this.httpclient.post<question>('/testdevalidation/addquestion',qu );
   }


   savequesttotest(qu: question, id:number): Observable<question>
  {
    return this.httpclient.post<question>(`/testdevalidation/addquestion/${id}`,qu);
  }
  updatequest(data: question): Observable<question> {
    return this.httpclient.put<question>('/testdevalidation//updatequestion', data);
       }

       getquestbyid(id:number ) {
        console.log("getbyid :",id);
        return this.httpclient.get<question[]>(`/testdevalidation/showquestion/${id}`)
      }

      getpassage() : Observable<passage[]>
   {
    return this.httpclient.get<passage[]>('/testdevalidation/allpassages')
   }
   gettoppassage() : Observable<passage[]>
   {
    return this.httpclient.get<passage[]>('/testdevalidation/toppassage/2')
   }
   getPassagesByScore(a: number, b: number): Observable<passage[]> {
    const params = new HttpParams().set('a', a.toString()).set('b', b.toString());
    return this.httpclient.get<passage[]>(`/testdevalidation/scorebetween`, { params });
  }
 



  generateqrcode(id: string): Observable<Blob> {
    const headers = new HttpHeaders().append('responseType', 'blob');
    return this.httpclient.get(`/testdevalidation/Qrtotest/4`, { headers, responseType: 'blob' });
  }

  addBlog(duree : any,image : any,pts : any ,titre : any): Observable<any> {
    return this.httpclient.post<any>('/testdevalidation/addvalidation',  {duree,
      image,
      pts,
      titre 
       
  },); 
  }
 
  
  

  
  }
  
  
  
  
  
   //gettestbyidd(id:number ) {
    //console.log("getbyid :",id);
    //return this.httpclient.get<testvalidation[]>(`/testdevalidation/showvalidation/${id}`)
  //}


  
      



