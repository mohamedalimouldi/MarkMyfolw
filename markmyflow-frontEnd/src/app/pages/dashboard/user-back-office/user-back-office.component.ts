import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/core/models/user';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-user-back-office',
  templateUrl: './user-back-office.component.html',
  styleUrls: ['./user-back-office.component.scss']
})
export class UserBackOfficeComponent implements OnInit {
  userList: User[];
  user!: User;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }
  deleteUser(username:string) {
    this.userService.deleteUser(username).subscribe(data=>{
      this.user=data;
      console.log(this.user);
      this.getAllUsers();


    })
  }


  public getAllUsers() {
    this.userService.getAllUsers().subscribe(data => {
      this.userList = data
      console.log(this.userList);
    }
    )
  };

}