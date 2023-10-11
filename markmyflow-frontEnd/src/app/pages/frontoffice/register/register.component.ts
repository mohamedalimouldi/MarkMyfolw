import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, first } from 'rxjs';
import { User } from 'src/app/core/models/user';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit{
user:User=new User();
sendVerifTokenToVerify='';
ipAddress:String='';
showIPAddress:any;

ip=localStorage.getItem('ip');
// user:User;
constructor(private userService:UserService , private router:Router ){
}
  ngOnInit(): void {
    localStorage.clear();
    
    
  }

//   getIpFromURL()
//   {
//     this.userService.getIPAddress().subscribe(ip => this.ipAddress = ip);
//     console.log(this.ipAddress);
// // this.showIPAddress=
// //     this.userService.getIPAddress().subscribe(res=>console.log(res));
// //     console.log(this.showIPAddress,'chika');
                    
//   }

register()
{
  this.userService.register(this.user).subscribe(data=>{this.goToVerify()});
  // go To Verify With The Token In Path And Stored in verifToken in sessionStorage 
  //Verify Works with magic
}
goToVerify(){
  this.sendVerifTokenToVerify=JSON.parse(sessionStorage.getItem('verifToken')).token;
  this.router.navigate(['/verify',"CheckYourMailForVerificationToken"]);
}

}
