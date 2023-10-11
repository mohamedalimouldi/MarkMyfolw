import { Component, OnInit } from '@angular/core';
import { testvalidation } from '../../../../core/models/testvalidation';
import { question } from '../../../../core/models/question';

import { TestService } from '../../../../core/services/test.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ReponseService } from 'src/app/core/services/reponse.service';
import { passage } from 'src/app/core/models/passage';





@Component({
  selector: 'app-testview',
  templateUrl: './testview.component.html',
  styleUrls: ['./testview.component.scss']
})
export class TestviewComponent implements OnInit  {

  //questions: testvalidation[];
  responses=[]
  id:number=0;
  a:any;
  testvv: testvalidation= new testvalidation();
  p: passage = new passage;
 

  

  constructor(private activatedRoute: ActivatedRoute,private x:TestService,private _responseService:ReponseService,private router: Router){
    //this.questions=[];
  }
  

  ngOnInit(){
    console.log("here")

    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
   console.log("here")
    this.x.gettestbyid(this.id).subscribe((response: any) => {
      console.log(response);
      this.testvv = response; 
      this.testvv.questions.forEach(element => {
        this.responses.push('')
      });
    });  
  }
  );
  }
  saveReponse(body){
    return new Promise((resolve)=>{
      this._responseService.saverep(body).subscribe((res:any)=>{
        resolve(res)
      })
    })
  }
  addReponses(){
    this.responses.forEach(async (element,i)=>{

      let body={
        iduserr:Number(sessionStorage.getItem('id')),
        prop:element,
        idq:{
          idq:this.testvv.questions[i].idq
        }
      }
      console.log(body)
      console.log(this.testvv.questions[i])
      await this.saveReponse(body)
    
    })
     this._responseService.addPassage(Number(sessionStorage.getItem('id')),this.id).subscribe((res:any)=>{
      console.log(res)  
      this.p=res
      this.router.navigate(["/test/quiz/",this.p.idp])

    })
    
  }
 
  


  }



