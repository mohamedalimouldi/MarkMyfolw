import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { IntentsService } from 'src/app/core/services/intents.service';

@Component({
  selector: 'app-intent-editor',
  templateUrl: './intent-editor.component.html',
  styleUrls: ['./intent-editor.component.scss']
})
export class IntentEditorComponent {
  DocForm = new FormGroup({
    tag: new FormControl('',Validators.required),
    patterns: new FormControl([],Validators.required),
    context: new FormControl('', [Validators.required]),
    responses: new FormControl([], [Validators.required]),
    idIntent: new FormControl(null),

  });
  id=[]
  constructor(private _intentService:IntentsService,private router:Router,private activatedRoute:ActivatedRoute){
  
  }
  getById(id){
    return new Promise<any>((resolve,reject)=>{
        this._intentService.getById(id).subscribe((res:any)=>{
          resolve(res)
        },err=>{
          reject(err)
        })
    })
  }
  ngOnInit(){
    this.activatedRoute.params.subscribe(async (params: Params) => {
      this.id = params['id'];
      if (this.id){
        let data = await this.getById(this.id)
        console.log(data)
        this.DocForm.setValue({
          idIntent:this.id,
          tag: data.tag,
          patterns: data.patterns,
          context: data.context,
          responses:  data.responses,
        })
      }

    });
  }

  submit(){
    console.log(this.DocForm.value)
  if (this.id!=null){
    this._intentService.add(this.DocForm.value).subscribe((res:any)=>{
      console.log(res)
      this.router.navigate(['/dashboard/intent/list'])
  })
  }
  else{
    let data=this.DocForm.value;
    delete data.idIntent
    this._intentService.add(data).subscribe((res:any)=>{
        console.log(res)
        this.router.navigate(['/dashboard/intent/list'])
    })
  }
    
  }
}
