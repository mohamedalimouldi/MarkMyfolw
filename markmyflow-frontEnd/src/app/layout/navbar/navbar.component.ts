import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input("HeaderClass") 
  Header:string='hi';
  u:string='';
  isShown=false;
  roles:any[]=JSON.parse(sessionStorage.getItem('roles'))

  constructor(private router:Router)
  {
    this.isShown=this.roles && this.roles.length>0  &&   this.roles[0]['roleName']=='ADMIN'
    //&& this.roles[0]['roleName']=='USER'
  }

  setHeader(){
    
  }
  ngOnInit()
  {
    this.u=sessionStorage.getItem('username');
    console.log(this.u);
  }

  logout()
  {sessionStorage.clear();
  this.router.navigate(['/authenticate'])};
  
}
