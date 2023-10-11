import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Projectworkflow } from '../models/projectworkflow';
import { Task } from '../models/task';

@Injectable({
  providedIn: 'root'
})
export class WorkflowService {

  private apiServerUrl: string

  constructor(private http:HttpClient) {
    this.apiServerUrl = '/workflowservice/projectworkflow';
   }

  public getProjectList():Observable<Projectworkflow[]> {
    console.log("here")
    let id=sessionStorage.getItem('company_id')

    return this.http.get<Projectworkflow[]>(`${this.apiServerUrl}/getAll/${id}`)
  }
  public getProjectById(id:number):Observable<Projectworkflow> {
    console.log("here")
    return this.http.get<Projectworkflow>(`${this.apiServerUrl}/get/${id}`)
  }
  public getProjectByTaskId(id:number):Observable<any> {
    console.log("here")
    return this.http.get<any>(`${this.apiServerUrl}/getByTaskId/${id}`)
  }
  public deleteProject(id:number):Observable<Projectworkflow> {
    console.log("here")
    return this.http.delete<Projectworkflow>(`${this.apiServerUrl}/delete/${id}`)
  }
  public addProject(projectworkflow:Projectworkflow):Observable<any> {
    console.log("here")
    return this.http.post<any>(`${this.apiServerUrl}/add`,projectworkflow)
  }
  public editProject(projectworkflow:any,id:number):Observable<any> {
    console.log("here")
    return this.http.put(`${this.apiServerUrl}/update/${id}`,projectworkflow)
  }
  public assignTaskProject(task:Task,project_id:number):Observable<any> {
    console.log("here")
    return this.http.put(`${this.apiServerUrl}/assignTask/${project_id}`,task)
  }
  public exportToPDF(idProject: number): Observable<Blob> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(`${this.apiServerUrl}/export/pdf/${idProject}`, {
      headers,
      responseType: 'blob',
    });
  }


}
