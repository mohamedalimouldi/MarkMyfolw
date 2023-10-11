import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup,FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/core/services/user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,BehaviorSubject } from 'rxjs';
import { ipModel } from 'src/app/core/models/ipModel';



@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.scss']
})
export class ResetComponent implements OnInit{
  lastNumbersFromUserPhone :string="+2*******";
  timeMovingFast : string="00:25 secs";
  
  newPassword:string;

   title = 'Ip_geolocation';
  IpAddressInReset : any;
  resetForm:any;
  phone='+21658275435';
  username:any;
  password:any;
  ip:any;

  constructor(private userService:UserService,private router:Router ,private formBuilder:FormBuilder ,  private httpClient:HttpClient) {
    // this.userService.getIp().subscribe((data:ipModel)=>{
    //   console.log(data)
    // }); 
  }
  ngOnInit() {
    // Here is the send Auto code to your phone to reset your password 
    this.userService.sendCode(this.phone,'hi');
   this.IpAddressInReset=this.userService.getIp();
    this.resetForm = new FormGroup(
      {
      code: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });     

  }
    
  fromControl(){
    if(this.resetForm.valid)
      {
      this.userService.resetBySms(this.resetForm.value.code,this.resetForm.value.password,this.phone);
    }
      
    
    }
    goToAuthenticate()
    {this.router.navigate(['/authenticate'])}



}
