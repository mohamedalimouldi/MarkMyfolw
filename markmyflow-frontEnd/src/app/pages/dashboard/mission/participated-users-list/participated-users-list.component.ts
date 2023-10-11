import { Component } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ParticipatedUsers } from 'src/app/core/models/participatedUsers';
import { MissionService } from 'src/app/core/services/mission.service';

@Component({
  selector: 'app-participated-users-list',
  templateUrl: './participated-users-list.component.html',
  styleUrls: ['./participated-users-list.component.scss']
})
export class ParticipatedUsersListComponent {
  id:number=0;
  puserlist: ParticipatedUsers[]= [];
  keyval:ParticipatedUsers;
  slackInput:string=""

  constructor(private activatedRoute: ActivatedRoute,private missionService:MissionService){}

  ngOnInit(){
  
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });
    
   this.getData();

  }

  getData() {
    this.missionService.getParticipatedUsersByMission(this.id).subscribe((res:ParticipatedUsers[]) =>{
      this.puserlist=res;
      console.log(res);
    });
  }

  updateParticipation(id:number,status:string)
  {
    this.missionService.updateParticipatedUsersStatus(id,status).subscribe(res =>{
      console.log(res);
      this.getData();
    })
  }

  inviteSlack()
  {
    this.missionService.createSlackChannel("mehrezw@gmail.com",this.slackInput).subscribe(res =>{
      console.log(res);
    })
    
  }

}
