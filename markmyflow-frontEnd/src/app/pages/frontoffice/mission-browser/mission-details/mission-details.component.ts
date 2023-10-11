import { Component } from '@angular/core';
import { ActivatedRoute, Params, Route, Router } from '@angular/router';
import { Mission } from 'src/app/core/models/mission';
import { ParticipatedUsers } from 'src/app/core/models/participatedUsers';
import { MissionService } from 'src/app/core/services/mission.service';

@Component({
  selector: 'app-mission-details',
  templateUrl: './mission-details.component.html',
  styleUrls: ['./mission-details.component.scss']
})
export class MissionDetailsComponent {
  id:number=0;
disable=false;
  mission: Mission= new Mission();

  constructor(private activatedRoute: ActivatedRoute,private missionService:MissionService,private router: Router){
    
  }

ngOnInit(){
  
  this.activatedRoute.params.subscribe((params: Params) => {
    this.id = params['id'];
  });
  
  this.missionService.getMissionById(this.id).subscribe((res:any) =>{
    this.mission=res;
    console.log(this.mission);
  });

  this.missionService.getParticipatedUsersByMissionanduser(this.id).subscribe((res:any) =>{
    this.mission=res;
    console.log(this.mission);
    if (res!=null)
    {
      this.disable=true;
    }
  });

console.log(this.mission.descriptionMission)
}

apply()
{
  let temp: ParticipatedUsers= new ParticipatedUsers();
  const today = new Date();
  const dateString = today.toISOString().slice(0, 10);
  temp.status="PENDING";
  this.activatedRoute.params.subscribe((params: Params) => {
    this.id = params['id'];
  });
  
  this.missionService.getMissionById(this.id).subscribe((res:any) =>{
  //temp.user=sessionStorage.getItem('id');
    console.log(this.mission);
  });
  temp.mission=this.mission;
  this.missionService.addParticipation(temp).subscribe(res=>{
    console.log(res);
    this.router.navigate(['/missions/list]',]);
  })

}

}
