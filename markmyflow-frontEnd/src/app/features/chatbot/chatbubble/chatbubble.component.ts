import { Component } from '@angular/core';
import { ChatproviderService } from '../providers/chatprovider.service';
import { ChatService } from 'src/app/core/services/chat.service';

@Component({
  selector: 'app-chatbubble',
  templateUrl: './chatbubble.component.html',
  styleUrls: ['./chatbubble.component.scss']
})
export class ChatbubbleComponent {
    Chats=[]
    msg=""
    isShown=false;
    roles:any[]=JSON.parse(sessionStorage.getItem('roles'))
    constructor(private _chatProvider:ChatproviderService,private _chatService:ChatService){
      this.isShown=this.roles && this.roles.length>0  && this.roles[0]['roleName']=='ADMIN'

      this._chatService.getAll().subscribe((res:any)=>{
        console.log(res)
        this._chatProvider.setChats(res)
      })
     
    }
    ngOnInit(){
      this._chatProvider.Chats.subscribe((res:any)=>{
        this.Chats=res;
        console.log(this.Chats)
      })
    }
    addChat(){
      let body={
        text:this.msg,
        idUser:1,
      }
      console.log(body)
      this.msg=""
      this._chatService.addMessage(body).subscribe((res:any)=>{
        console.log(res)
        this._chatProvider.addChat(res)

      })
    }

}
