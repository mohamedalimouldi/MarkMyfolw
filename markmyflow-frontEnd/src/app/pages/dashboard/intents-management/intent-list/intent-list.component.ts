import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalDataSource } from 'ng2-smart-table';
import { IntentsService } from 'src/app/core/services/intents.service';

@Component({
  selector: 'app-intent-list',
  templateUrl: './intent-list.component.html',
  styleUrls: ['./intent-list.component.scss']
})
export class IntentListComponent {
  intents=[]
  constructor(private _intentService:IntentsService,private router:Router){}
  settings = {
    mode:"external",
    columns: {
      tag: {
        title: 'Title',
        filter: true
      },
      context: {
        title: 'Context',
        filter: true
      },
      delete: {
        confirmDelete: true,
      },
     
    }
  };
  source: LocalDataSource;
  onCreate(event){
    this.router.navigate(['/dashboard/intent/add'])
  }
  onEdit(event){
    console.log(event)
    this.router.navigate(['/dashboard/intent/edit/'+event.data.idIntent])
  }
  onDelete(event){
    this._intentService.delete(event.data.idIntent).subscribe((res)=>{
      console.log(res)
      this.source.remove(event.data);
      this.source.refresh();

    },err=>{
      console.log(err)
    })
  }
  ngOnInit(){
    this._intentService.getAll().subscribe((res)=>{
      this.intents=res
      this.source = new LocalDataSource(this.intents);

    },err=>{
      console.log(err)
    })
  }

}
