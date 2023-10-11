import { Component } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Mission } from 'src/app/core/models/mission';
import { Tags } from 'src/app/core/models/tags';
import { MissionService } from 'src/app/core/services/mission.service';

@Component({
  selector: 'app-add-mission',
  templateUrl: './add-mission.component.html',
  styleUrls: ['./add-mission.component.scss']
})
export class AddMissionComponent {
  tags: Tags[] = [];
selectedTags: Tags[] =[];
id:number=0;
titleInput: string;
 descriptionInput : string;
 paymentInput : number;
 dateInput : string;
 tagInput : string;

 constructor(private activatedRoute: ActivatedRoute,private missionService: MissionService,private router: Router){}

 ngOnInit(){
  this.getTags();
  this.activatedRoute.params.subscribe((params: Params) => {
    this.id = params['id'];
  });
 }
getTags(){
  this.missionService.getTags().subscribe((res:Tags[])=>{
    console.log(res)
    this.tags=res;
    console.log(this.tags)
  });
}

addTags()
{
  const target = event.target as HTMLInputElement;
  let tg = new Tags();
  tg.id=this.tagInput;
  if (tg.id!=undefined){
    this.missionService.addTags(tg).subscribe((res)=>{
      console.log(res);
      this.getTags();

    })
  }
  
}
  onSubmit()
  {
    let mission: Mission= new Mission();
    mission.titleMission=this.titleInput;
    mission.descriptionMission=this.descriptionInput;
    mission.deadlineMission=this.dateInput;
    mission.task=this.id;
    mission.priceMission=this.paymentInput;
    console.log( JSON.stringify(this.selectedTags));
    this.missionService.addMission(mission).subscribe((res:Mission)=>{
      console.log(res.idMission);
      this.selectedTags.forEach(item => {
        console.log(item);
        let temp={
          "mission": {
          "idMission": res.idMission
      },
      "tag": {
          "id": item.id
      }
      
    }
    console.log(temp);
    this.missionService.addMissionTags(temp).subscribe((res2)=>{
      console.log(res2);
    })
      }
      
      );
      
      let idArray = this.selectedTags.map(tag => tag.id);
      this.missionService.getMatchingTags(idArray).subscribe((res3)=>{
        console.log(res3)
        res3.forEach(item => {
          let tempreq={
            "url" : `http://localhost:4200/missions/details/${res.idMission}`,
            "recipient" : item
        }
          this.missionService.sendMailToMatching(tempreq).subscribe(res4 => {
            console.log(res4);
            this.router.navigate(['/dashboard/mission/missionlist/]',this.id]);
          })
        });
        
      });
      
    });
  }

  // addTag(){
  //   this.tags.push({"id":this.tagInput});
  // }

  selectTag(tag: Tags, event: Event){
    const target = event.target as HTMLInputElement;
    if (target.checked && !this.selectedTags.includes(tag)) {
      this.selectedTags.push(tag);
      
    } else if (!target.checked && this.selectedTags.includes(tag)) {
      this.selectedTags = this.selectedTags.filter(t => t !== tag);
      
    }
    if(this.selectedTags.length == 0)
    {
      
    }
    else{
    const tagIds: string[] = this.selectedTags.map((tag: Tags) => tag.id)
    console.log(tagIds);
  //   this.missionService.getFilteredTags(tagIds).subscribe((res)=>{
  //     this.data=res;
  //     console.log(this.data)
  //   });
  // }
  }

}
}


