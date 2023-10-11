import { Component } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Mission } from 'src/app/core/models/mission';
import { MissionService } from 'src/app/core/services/mission.service';

@Component({
  selector: 'app-mission-list',
  templateUrl: './mission-list.component.html',
  styleUrls: ['./mission-list.component.scss']
})
export class MissionListComponent {
  data:Mission[]=[];

  id:number=0;
  constructor(private activatedRoute: ActivatedRoute,private missionService: MissionService,private router: Router){}
  
  ngOnInit()
  {

    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });
  this.missionService.getMissionByTask(this.id).subscribe((res:any)=>{
  
    this.data=res;
    console.log(this.data)
    });
  }

  deleteMission(missionid:number )
  {
    this.missionService.deleteMission(missionid).subscribe(res => console.log("mission deleted"));
  }
}
