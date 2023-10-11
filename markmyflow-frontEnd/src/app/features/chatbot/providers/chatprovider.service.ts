import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Observable } from 'rxjs';
import { ChatService } from 'src/app/core/services/chat.service';

@Injectable({
  providedIn: 'root'
})
export class ChatproviderService {

  private chats: BehaviorSubject<any[]>;
  Chats:Observable<any[]>
  constructor(private ChatSerivce:ChatService) { 
    console.log("here")
    this.chats=new BehaviorSubject<[]>([]);
    this.Chats=this.chats.asObservable();

  }
  addChat(Chat:any){
    let data=this.chats.value;
    data.push(Chat);
    this.chats.next(data)
  }
  setChats(Chats:[]){
    /*
    this.chats=new BehaviorSubject<[]>(Chats);
    this.Chats=this.chats.asObservable();
    */
   this.chats.next(Chats)
  }
}
