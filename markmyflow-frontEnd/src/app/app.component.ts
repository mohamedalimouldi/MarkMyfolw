import { Component } from '@angular/core';
import { UserService } from './core/services/user.service'; 
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'MarkMyFlow';
  constructor (private userService :UserService){
  }
}