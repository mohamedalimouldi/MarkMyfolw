import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { UserService } from './user.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminGuardService implements CanActivate{

  roles:any[]=JSON.parse(sessionStorage.getItem('roles'))


  constructor(private router:Router,userservice:UserService) { }
  canActivate( next:ActivatedRouteSnapshot,state:RouterStateSnapshot):Observable<boolean |UrlTree> | Promise<boolean  |
  UrlTree> |boolean | UrlTree {
    
 
    if (this.roles && this.roles.length==1){
      if ( this.roles[0]['roleName']=='USER')
    {
      alert(' FORBIDDEN ADMIN ACCESS  !!');
      this.router.navigate(['/']);
          return false;
     }
     else{
      return true;
     }

      
    }
       return false;

}
}