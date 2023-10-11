import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../models/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiServerUrl: string

  constructor(private http:HttpClient) {
    this.apiServerUrl = '/workflowservice/task';
   }

  public getTaskList():Observable<Task[]> {
    console.log("here")
    return this.http.get<Task[]>(`${this.apiServerUrl}/getAll`)
  }
  public getTaskById(id:number):Observable<Task> {
    console.log("here")
    return this.http.get<Task>(`${this.apiServerUrl}/get/${id}`)
  }
  public getTasksByProject(id:number):Observable<Task[]> {
    console.log("here")
    return this.http.get<Task[]>(`${this.apiServerUrl}/getByProject/${id}`)
  }
  public deleteTask(id:number):Observable<Task> {
    console.log("here")
    return this.http.delete<Task>(`${this.apiServerUrl}/delete/${id}`)
  }
  public addTask(task:Task):Observable<any> {
    console.log("here")
    return this.http.post<any>(`${this.apiServerUrl}/add`,task)
  }
  public editTask(task:any,id:number):Observable<any> {
    console.log("here")
    return this.http.put(`${this.apiServerUrl}/update/${id}`,task)
  }
}
