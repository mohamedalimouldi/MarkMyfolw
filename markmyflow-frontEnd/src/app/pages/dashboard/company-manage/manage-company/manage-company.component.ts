import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Company } from 'src/app/core/models/company';
import { CompanyService } from 'src/app/core/services/company.service';

@Component({
  selector: 'app-manage-company',
  templateUrl: './manage-company.component.html',
  styleUrls: ['./manage-company.component.scss']
})
export class ManageCompanyComponent implements OnInit{
  @ViewChild("closeModal")
  closeButton:ElementRef
  @ViewChild("OpenModal")
  openmodal:ElementRef
  searchTerm : string="";
  filterData: any[]=[];
  companys: Company[]; 
  listCompany: Company[];
  company: Company=new Company();
  editcompany: Company= new Company();
  edit=-1;
  DocForm = new FormGroup({
    name: new FormControl('',Validators.required),
    email: new FormControl('',Validators.required),
    codefiscal: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    number: new FormControl('', [Validators.required]),
    nameuser: new FormControl('', [Validators.required]),
    lastnuser: new FormControl('', [Validators.required]),
    emailUser: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private companService: CompanyService, private _santizer:DomSanitizer){}
  
  ngOnInit(): void {
      this.getCompanys();
      
  }
  filterDataFunction(searchTerm: string) {
    if (searchTerm) {
      this.filterData = this.companys.filter(item =>
        item.name.toLowerCase().includes(searchTerm.toLowerCase())
      );
    } else {
      this.filterData = this.companys;
    }
  }
  onSearch() {
    this.filterDataFunction(this.searchTerm);
  }
  getCompanyName(value: string){
    this.companService.getByusername(value).subscribe(data =>{
      this.listCompany=data
      console.log(this.listCompany)
    })
  }
  

  getCompanys(){
    this.companService.getAllCompany().subscribe(data => {
      this.companys=data;
      this.filterData=data
    });
  }

  deleteCompany(id: number){
    this.companService.deleteCompany(id).subscribe( data => {
        console.log(data);
      this.getCompanys();
    })
  }
  public addcompany(): void{
    this.companService.addEntreprise(this.DocForm.value).subscribe(
      (response: Company)=>{
        console.log(response);
        this.getCompanys()
        this.closeButton.nativeElement.click();
      }
    )
  }

  async onSubmit(){
    if (this.edit<0){
      console.log(this.company);
      this.addcompany();
    }
    else{
      let body={
        name:this.DocForm.value.name,
        id:this.companys[this.edit].id,
        email:this.DocForm.value.email,
        address:this.DocForm.value.address,
        codefiscal:this.DocForm.value.codefiscal,
        nameuser:this.DocForm.value.email,
        number:this.DocForm.value.number,
        lastnuser:this.DocForm.value.lastnuser,
        emailUser:this.DocForm.value.emailUser,
        password:this.DocForm.value.password,
      }
      let updated=await this.onUpdateCompany(body);
      
       //this.companys[this.edit]=updated
    }
    this.closeButton.nativeElement.click();


  }
  openEdit(obj,id){
    console.log(obj)
      this.DocForm.setValue({
        
        name:obj.name,
        email:obj.email,
        address:obj.address,
        codefiscal:obj.codefiscal,
        emailUser:obj.emailUser,
        password:obj.password,
        nameuser:obj.nameuser,
        number:obj.number,
        lastnuser:obj.lastnuser
       
      })
      this.edit=id;
      this.openmodal.nativeElement.click();

  }
   onUpdateCompany(company: Company): void {
    this.companService.updateCompany(company).subscribe(
      (response: Company) => {
        this.getCompanys();
        this.edit=-1
        this.DocForm.reset()
      }
    );
}
}
