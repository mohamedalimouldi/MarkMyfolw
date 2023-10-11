import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IntentsService {

  constructor(private _httpClient:HttpClient) { }
  private _msUrl="/advisorservice/intent"

  getAll():Observable<[]>{
    return this._httpClient.get<[]>(`${this._msUrl}/all`)
  }
  getById(id:string):Observable<any>{
    return this._httpClient.get<any>(`${this._msUrl}/getById/${id}`)
  }
  add(obj:any){
    return this._httpClient.post<any>(`${this._msUrl}/add`,obj)
  }
  update(obj:any){
    return this._httpClient.put<any>(`${this._msUrl}/update/`,obj)
  }
  delete(id:string){
    return this._httpClient.delete(`${this._msUrl}/delete/${id}`,{responseType:"text"})
  }
  download(id:string){

    return this._httpClient.get(`${this._msUrl}/get-file/${id}`, { responseType: 'blob' })
  }
  upload(obj:any){
    return this._httpClient.post<any>(`${this._msUrl}/uploadFile`,obj,{responseType:"json"})
  }
}
