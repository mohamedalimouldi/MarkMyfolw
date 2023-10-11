import { Injectable } from '@angular/core';
import { HttpClient } from'@angular/common/http';
import { Observable } from 'rxjs';  
import { Company } from '../models/company';
import { ProjectWorkflow } from '../models/project-workflow';
@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private apiServerUrl: string
  

  constructor( private http : HttpClient ) {
    this.apiServerUrl = '/companyservice/entreprise'; }
  public getAllCompany():Observable<[]>{
    return this.http.get<[]>(`${this.apiServerUrl}/getAll`);
  }
  public getByid(id: any):Observable<Company>{
    console.log("getting Mission id :",id);
    return this.http.get<Company>(`${this.apiServerUrl}/findbyId/${id}`)
  }
  public getByusername(username: string):Observable<Company[]>{
    return this.http.get<Company[]>(`${this.apiServerUrl}/companyName?name=${username}`)  }
  public getProject(id: any):Observable<any[]>{
    console.log("get project :", id)
    return this.http.get<any[]>(`${this.apiServerUrl}/company/${id}`)
  }
  public addEntreprise(entreprise: any):Observable<Company>{
    return this.http.post<Company>(`${this.apiServerUrl}/add`, entreprise)
  }
  public updateCompany(company: Company): Observable<Company>{
    return this.http.put<Company>(`${this.apiServerUrl}/update`, company)
  }
  public deleteCompany(id: number): Observable<Company>{
    return this.http.delete<Company>(`${this.apiServerUrl}/delete/${id}`)
  }

  }