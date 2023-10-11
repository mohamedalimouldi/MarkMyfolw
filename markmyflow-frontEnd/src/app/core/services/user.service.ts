import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { map } from 'rxjs/operators';
import { ipModel } from '../models/ipModel';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly baseUrl='/userservice'
  ipAddress :string='';
  // const httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json'
  //   }),
  //   responseType: 'text' // Set the response type to text
  // };
 
 
  constructor(public httpClient:HttpClient, private router:Router) { 
  //  this.apiServerUrl=''

  }

  public deleteUser(username:string):Observable<User>{
    return this.httpClient.delete<User>(`${this.baseUrl}/delete/${username}`);
  }
  
  public getAllUsers():Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseUrl}/AllUsers`);
  }
  public getUser(username:string)
  { 
    return this.httpClient.get<any>(`${this.baseUrl}/getUser/${username}`);
  }

  authenticate(username: string, password: string) {   
    const url = `${this.baseUrl}/auth/authenticate`;
    return this.httpClient.post<any>(url, {username, password},{
      headers:new HttpHeaders()
      .set('Content-Type','application/json')
      })
      .pipe(map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.token ) {
          sessionStorage.clear();
          // store user details and jwt token in session storage to keep user logged in between page refreshes
          sessionStorage.setItem('username',JSON.stringify(username));
          sessionStorage.setItem('Token', JSON.stringify(user.token)); 

          }  
        return user;
      }));
  }
  sendCode(phone:String,message:String)
  {
    const SMSRequest={
      phone,
      message
    };
       return this.httpClient.put(`${this.baseUrl}/SMS/sendsms`,SMSRequest,
       {responseType:'text'
      }).subscribe(data=>console.log('========> SMS',data));
  }

  resetBySms(code:any,newPassword:any ,phone:any) {
    const sendRequest={
      phone:phone,
      message:'hi'
    }
    return this.httpClient.put(`${this.baseUrl}/resetbysms/${code}/${newPassword}`,sendRequest,
     { responseType:'text',headers:new HttpHeaders()
      .set('Content-Type','application/json')
      }).subscribe(
        data=>
      {
        console.log('========> updated',data);
        this.router.navigate(['/authenticate']);
      }
        )
      ;
  }
  
  getIp()
  {
    return this.httpClient.get<any>('https://jsonip.com',{responseType: 'json'}); 
  }

 
  register(user: any)  {
    return this.httpClient.post(`${this.baseUrl}/auth/register`, user , 
    {headers: new HttpHeaders()
    .set('Content-type','application/json')})
    .pipe(map(user=>
      sessionStorage.setItem('verifToken',JSON.stringify(user))));
  }

  registerAdmin(user: any)  {
    return this.httpClient.post(`${this.baseUrl}/auth/registerAdmin`, user , 
    {headers: new HttpHeaders()
    .set('Content-type','application/json')})
    .pipe(map(user=>
      sessionStorage.setItem('verifToken',JSON.stringify(user))));
  }
  //registerAdmin
  activateAccountByTokenInMail(verificationToken:string){
    localStorage.setItem('chika',"hiactivation");
    return this.httpClient.put(`${this.baseUrl}/verify/${verificationToken}`,verificationToken);
    //return this.httpClient.put(`${this.baseUrl}/verify/${verificationToken}`,{});
  }

// to call when need it it's all ready here ! 
  logout() {
    // remove user from local storage to log user out
    sessionStorage.removeItem('Token');
    sessionStorage.removeItem('username');
    
  }
  isUserSignedin() {
		return sessionStorage.getItem('Token') !== null;
	}

	getSignedinUser() {
		return sessionStorage.getItem('username') as string;
	}

	getToken() {
		let token = sessionStorage.getItem('Token') as string;
		return token;
	}





 

  
}
