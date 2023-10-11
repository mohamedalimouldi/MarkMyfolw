import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { DocumentService } from 'src/app/core/services/document.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-document-list',
  templateUrl: './document-list.component.html',
  styleUrls: ['./document-list.component.scss']
})
export class DocumentListComponent implements OnInit{
  // AHAYA
  @ViewChild("closeModal")
  closeButton:ElementRef
  //TEKEF HNE
  @ViewChild("Modal")
  modal:ElementRef

  @ViewChild("OpenModal")
  openmodal:ElementRef

  @ViewChild("RequestModalButton")
  requestmodal:ElementRef
  docs=[];
  loader=false
  urlDownload=environment.ApiUrl+"/advisorservice/document/get-file/"
  fd = new FormData();
  
  DocForm = new FormGroup({
    text: new FormControl('',Validators.required),
    fields: new FormControl('',Validators.required),
    file: new FormControl('', [Validators.required]),
    path: new FormControl(null, [Validators.required]),
  });

  requestedDoc

  fieldsMap={}
  edit=-1;
  constructor(private _docService:DocumentService,private _santizer:DomSanitizer){}
  
  openEdit(obj,id){
    console.log(obj)
      this.DocForm.setValue({
        text:obj.text,
        fields:obj.fields,
        file:null,
        path:"",
      })
      this.edit=id;
      this.openmodal.nativeElement.click();

  }
  ngOnInit(){
    this._docService.getAll().subscribe((res)=>{
      this.docs=res
      this.loader=true
    },err=>{
      console.log(err)
    })
  }
  public onSelect(item) {
    console.log('tag selected: value is ' + item);
}

onFileChange(event) {
  
  if (event.target.files.length > 0) {
    const file = <File>event.target.files[0];
    this.DocForm.patchValue({
      path: file
    });
  }
}

create(body){
  return new Promise((resolve,reject)=>{
    this._docService.add(body).subscribe((res)=>{
      resolve(res)
    },err=>{
      reject(err)
    })
  })
}
prepareDocument(Doc){
  this.requestmodal.nativeElement.click()
  //this.RequestDoc.value.fields=Doc.fields
  this.fieldsMap=Doc.fields.reduce((a, v) => ({ ...a, [v]: v}), {}) 
  this.requestedDoc=Doc;
  //console.log(fields)
}
doneRequest(){
  console.log(this.fieldsMap)

  let body={
    document:this.requestedDoc,
    fieldsToMap:this.fieldsMap,
    text:this.requestedDoc.text+" "+new Date(),
    idUser: sessionStorage.getItem('id')
  }
  console.log(body)
  this._docService.requestDoc(body,this.requestedDoc.idDocument).subscribe((res:any)=>{
    console.log(res)
    window.open(this.urlDownload+res.path,"_blank")
    
  })
}
update(body){
  return new Promise((resolve,reject)=>{
    this._docService.update(body).subscribe((res)=>{
      resolve(res)
    },err=>{
      reject(err)
    })
  })
}
upload(body){
  return new Promise((resolve,reject)=>{
    this._docService.upload(body).subscribe((res:any)=>{
      resolve(res.file)
    },err=>{
      reject(err)
    })
  })
}
  async submit(){
  console.log(this.DocForm.value)
  this.fd= new FormData();
  this.fd.append('file', this.DocForm.value.path, this.DocForm.value.path.name);
  let res=await this.upload(this.fd)
  let body={
      text:this.DocForm.value.text,
      fields:this.DocForm.value.fields,
      path:res
  }
  if(this.edit<0){
    let added=await this.create(body);
      //AHAYA YA DALI
    this.docs.push(added)
  }
  else{
    let body={
      text:this.DocForm.value.text,
      idDocument:this.docs[this.edit].idDocument,
      fields:this.DocForm.value.fields,
      path:res
    }
    let updated=await this.update(body);
    this.docs[this.edit]=updated
  }
  this.closeButton.nativeElement.click();
    //TE9EF HNE
  this.edit=-1
  this.loader=true
 
}

  delete(id,index){
    console.log(index);
    this._docService.delete(id).subscribe((res:any)=>{
      this.docs.splice(index,1);
    })
  }
}
