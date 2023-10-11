import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Mission } from 'src/app/core/models/mission';
import { savedSearches } from 'src/app/core/models/savedSearches';
import { Tags } from 'src/app/core/models/tags';
import { MissionService } from 'src/app/core/services/mission.service';

@Component({
  selector: 'app-mission-list',
  templateUrl: './mission-list.component.html',
  styleUrls: ['./mission-list.component.scss']
})
export class MissionListComponent {
data:Mission[]=[];
tags: Tags[] = [];
selectedTags: Tags[] =[];
tagInput : string;
alert:boolean;
savedTags:string;
savedsearches:savedSearches=new savedSearches();
constructor(private missionService: MissionService,private router: Router){}

ngOnInit()
{
this.getAll();
this.getTags();
this.getSavedSearches();
}
getAll()
{
  this.missionService.getListMissions().subscribe((res:any)=>{

    this.data=res;
    console.log(this.data)
    });
}

getSavedSearches()
{
  this.missionService.getSavedSearchesByUser().subscribe((res:any)=>{
    if (res[0]!=undefined)
    {
      this.savedsearches=res[0]
      this.alert=true;
    }

    
    console.log(this.savedsearches)
  });
}


selectTag(tag: Tags, event: Event){
  const target = event.target as HTMLInputElement;
  if (target.checked && !this.selectedTags.includes(tag)) {
    this.selectedTags.push(tag);
     this.stringifyTags();
  } else if (!target.checked && this.selectedTags.includes(tag)) {
    this.selectedTags = this.selectedTags.filter(t => t !== tag);
    this.stringifyTags();

  }
  if(this.selectedTags.length == 0)
  {
    this.getAll();
  }
  else{
  const tagIds: string[] = this.selectedTags.map((tag: Tags) => tag.id)
  console.log(tagIds);
  this.missionService.getFilteredTags(tagIds).subscribe((res)=>{
    this.data=res;
    console.log(this.data)
  });
}
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
searchLabel(event: Event)
{
  const target = event.target as HTMLInputElement;
  console.log(target.value);
  this.missionService.getMissionBySearch(target.value).subscribe((res:any)=>{
    this.data=res;
    console.log(this.data)
    });
}
emailAlert(){
  if(this.alert){
    console.log(this.savedTags);
    this.savedsearches.tags=this.savedTags;
    this.missionService.addSavedSearches(this.savedsearches).subscribe(res =>{console.log(res)
      this.getSavedSearches();
    })
  }
    else{
      this.savedsearches.tags=this.savedTags;
    this.missionService.deleteSavedSearches(this.savedsearches.idSavedSearches).subscribe(res =>{console.log(res)})
      console.log(this.alert);
    }
  
}
stringifyTags()
{
  this.savedTags= JSON.stringify(this.selectedTags);

}

updateSavedSearches(){
  console.log(this.savedTags);
    this.savedsearches.tags=this.savedTags;
    this.missionService.addSavedSearches(this.savedsearches).subscribe(res =>{console.log(res)
      this.getSavedSearches();
    })
}
}


