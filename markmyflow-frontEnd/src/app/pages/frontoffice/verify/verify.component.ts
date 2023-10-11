import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.scss']
})
export class VerifyComponent implements OnInit{
  verificationtoken:string;
  verifTokenString:string;
  resOfVerifMethod='';
  username:string;
  ipAddress='';
  chika='salim';
  constructor(private route:ActivatedRoute, private userService:UserService , private router:Router,httpClient:HttpClient){
    
  }
  ngOnInit():void {
   // this.activateAccount
  //  console.log(this.username);
  //this.verificationtoken=this.route.snapshot.paramMap.get('verificationtoken');
  console.log(this.verificationtoken);
    // this.userService.getIPAddress().subscribe((res: any) => {
    //  this.ipAddress = res.ip;
    // });
   
  }

  activateAccount(){
    this.verificationtoken=sessionStorage.getItem('verifToken');
    this.verifTokenString=JSON.parse(this.verificationtoken).token;
    const a=JSON.parse(this.verificationtoken).token;
   // console.log(a.token);
    this.userService.activateAccountByTokenInMail(this.verifTokenString).subscribe((data:any)=>
    this.resOfVerifMethod=data);
    this.goToAuthenticate();
   //
    //console.log(this.token);
    //return this.userService.activateAccountByTokenInMail(this.token);
  }

//goToAcceuilMarkMyFlow(hi:here)

goToAuthenticate()
{
  this.router.navigate['authenticate'];
}

}
