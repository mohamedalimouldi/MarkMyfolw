import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChatbubbleComponent } from './chatbubble/chatbubble.component';
import { CoreModule } from 'src/app/core/core.module';
import { ChatproviderService } from './providers/chatprovider.service';
import { FormsModule } from '@angular/forms';
import { ChatService } from 'src/app/core/services/chat.service';



@NgModule({
  declarations: [
    ChatbubbleComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    CoreModule,
  ],
  exports:[
    ChatbubbleComponent,
  ],
  providers:[
   // ChatService,
    
  ]
})
export class ChatbotModule { }
