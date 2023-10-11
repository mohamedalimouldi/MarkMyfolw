import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/core/models/user';
import { UserService } from 'src/app/core/services/user.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
//import { PopupComponent } from './popup.component';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  userList: User[];
  username: any;
  password: any;
  ipAddress: string;
  loginForm: any;
  //simpleWord: string = sessionStorage.getItem('username').substring(1, sessionStorage.getItem('username').length - 1);
  //simpleWord:string='';
  infoUser: string = ''
  roles:string[]=[];
  rolesTest:any=JSON.parse(sessionStorage.getItem('roles'));
  


  constructor(private userService: UserService, private router: Router) { this.userList = []; }


  authenticate() {
    //here thge validators might be !!
    this.userService.authenticate(this.username, this.password).subscribe((res: any) => 
    
    {  
                      this.userService.getUser(this.username).subscribe(data => {
                        console.log(data);
                        sessionStorage.setItem('email', data.email)
                        sessionStorage.setItem('company_id', data.company_id); 

                        sessionStorage.setItem('id',JSON.stringify(data.idUser));
                        const listR:any[]= data['roles'];
                        sessionStorage.setItem('roles', JSON.stringify(listR))

                        if (listR.length==1){
                          if ( listR[0]['roleName']=='USER')
                        {
                          this.router.navigate([''],
                          );
                        }
                      else this.router.navigate(['dashboard'])}

                      });

                     
 
     
     
     

    });
     
      
    //this.userService.getIPAddress();
  }
  userInfo() {
  /*  this.userService.getUser(this.simpleWord).subscribe(res => {
      console.log(res);
      sessionStorage.setItem('email', res.email)
      sessionStorage.setItem('role', JSON.stringify(res));
    });*/
  }
  ngOnInit(): void {

    this.loginForm = new FormGroup(
      {
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required)
      });
    //console.log(this.simpleWord);
    // this.userInfo();
// let xx=[]
//     for(let i=0;this.roles.length;i++)
//     {
     
//       xx.push(this.roles[i]['roleName'])
      
//     }
//     console.log(this.rolesTest);
//     console.log(xx);
this.userService.getIp().subscribe(
  data=> {
    localStorage.setItem('ip',data["ip"]);
    localStorage.setItem('country',data["country"]);
    this.ipAddress=data["ip"]}
);

  }
  // this.userService.getIPAddress().subscribe((res:any)=>

  // {this.ipAddress=res;
  //  sessionStorage.setItem('heuhuiiiIP',JSON.stringify(this.ipAddress))});

  //sessionStorage.setItem('ipHeuyyyyyyy', JSON.stringify(this.ipAddress));
  // this.userService.getAllUsers().subscribe(data=>{
  //   this.userList=data
  //   console.log(this.userList);
  // });
  // authenticate(){
  // this.userService.login(this.username, this.password).subscribe();
  //}


  // fromControl(

  //   ){
  //     if (this.loginForm.valid) {
  //       console.log(this.loginForm.value);
  //       // My logic here
  //       this.router.navigate(['/authenticate']);
  //     }
  //     }
}
