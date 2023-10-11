import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private _msUrl="/advisorservice/message"

  constructor(private _httpClient : HttpClient ) { }
  getAll():Observable<[]>{
    let id=sessionStorage.getItem('id')
    return this._httpClient.get<[]>(`${this._msUrl}/all/${id}`)
  }
  addMessage(obj){
    return this._httpClient.post<any>(`${this._msUrl}/add`,obj)
  }
}
