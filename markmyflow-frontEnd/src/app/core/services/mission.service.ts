import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Mission } from '../models/mission';
import { Tags } from '../models/tags';
import { User } from '../models/user';
import { savedSearches } from '../models/savedSearches';
import { ParticipatedUsers } from '../models/participatedUsers';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class MissionService {
user:User;
username:string;
a:any= sessionStorage.getItem('username');
  constructor(private http: HttpClient , private userservice:UserService) { 
   
    
    // this.user={
    //   "idUser" : 5,
    //   "firstname": "test",
    //   "lastname": "test",
    //   "username" : "string",
    //   "password": "string",
    //   "email": "walid.mehrez@esprit.tn",
    //   "phone": "12345678",
    //   "code": "string",
    //   "isVerified": 1
    // }
  }
  getLoggedUser()
  {
   return this.user={
      "idUser" :Number(sessionStorage.getItem('id')) ,
      "firstname": "test",
      "lastname": "test",
      "username" : "string",
      "password": "string",
      "email": sessionStorage.getItem('email'),
      "phone": "12345678",
      "code": "string",
      "isVerified": 1
    }
  }



  getListMissions() {
    console.log("getting Mission list");
    return this.http.get<Mission[]>('/missionservice/mission')
  }

  getMissionById(id:number ) {
    console.log("getting Mission id :",id);
    return this.http.get<Mission[]>(`/missionservice/mission/${id}`)
  }

  deleteMission(id:number ) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    console.log("deleting Mission id :",id);
    return this.http.delete<any[]>(`/missionservice/mission/delete/${id}` , { headers: headers })
  }

  addMission(m:Mission ) {
    console.log("adding Mission :",m);
    return this.http.post<Mission>(`/missionservice/mission`,m)
  }

  getMissionByTask(id:number ) {
    console.log("getting Mission By task id :",id);
    return this.http.get<Mission[]>(`/missionservice/mission/task/${id}`)
  }

  getMissionBySearch(search:string ) {
    console.log("getting Mission By search :",search);
    return this.http.post<Mission[]>(`/missionservice/mission/search/${search}`,{})
  }

  getTags( ) {
    console.log("getting tags ");
    return this.http.get<Tags[]>(`/missionservice/tags`)
  }

  addTags(t:Tags ) {
    console.log("adding Tags :",t);
    return this.http.post<Tags>(`/missionservice/tags`,t)
  }

  getFilteredTags(ids:string[] ) {
    console.log("getting tags ",ids);
    return this.http.post<Mission[]>(`/missionservice/mission/tags`,ids)
  }

  addMissionTags(mt:any ) {
    console.log("adding MissionTags :",mt);
    return this.http.post<any[]>(`/missionservice/missiontags`,mt)
  }

  getMatchingTags(keywords:String[] ) {
    console.log("finding mails for matchingMissionTags :",keywords);
    return this.http.post<String[]>(`/missionservice/savedsearches/matching`,keywords)
  }

  sendMailToMatching(req:any){

    return this.http.post<String[]>(`/companyservice/subscription/testmail`,req)
  }

  getSavedSearchesByUser() {
    console.log("finding SavedSearchesByUser :",this.getLoggedUser().idUser);
    return this.http.get<savedSearches>(`/missionservice/savedsearches/user/${this.getLoggedUser().idUser}`)
  }

  addSavedSearches(s:savedSearches ) {
    s.idUser=this.getLoggedUser().idUser;
    s.userEmail=this.getLoggedUser().email;
    console.log("adding savedSearches :",s);
    return this.http.post<savedSearches>(`/missionservice/savedsearches`,s)
  }

  deleteSavedSearches(id:number ) {
    console.log("deleting savedSearches id :",id);
    return this.http.delete<any[]>(`/missionservice/savedsearches/${id}`)
  }

  getParticipatedUsersByMission(id:number){
    console.log("getting participations for mission id :",id);
    return this.http.get<ParticipatedUsers[]>(`/missionservice/participatedusers/mission/${id}`)

  }

  updateParticipatedUsersStatus(id:number,status:string){
    console.log("update participation for id :",id);
    return this.http.put<ParticipatedUsers>(`/missionservice/participatedusers/updatestatus/${id}/${status}`,{})

  }

  createSlackChannel(email: string, chanelName: string): Observable<any> {
    let accessToken= "xoxp-5118638391365-5121455386723-5121991629411-079416d88dcae13059c41ecd7e58e892";
    let userId2="";
    const body = { userId2, email ,accessToken , chanelName};
    let params = new HttpParams();
    params = params.append('userId2', "U053YPRUEKB");
    params = params.append('email', email);
    params = params.append('accessToken', accessToken);
    params = params.append('chanelName', chanelName);
    return this.http.post<string>(`/missionservice/create-conversation`, {}, { params });
  }
  
  addParticipation(p:ParticipatedUsers ) {
    p.user=this.getLoggedUser().idUser;
    console.log(this.getLoggedUser().idUser)
    console.log("adding Participation :",p);
    return this.http.post<ParticipatedUsers>(`/missionservice/participatedusers`,p)
  }

  getParticipatedUsersByMissionanduser(missionid : number){
    console.log("getting participations for user id :");
    let temp={
       "idMission" : missionid
    }
    return this.http.get<ParticipatedUsers[]>(`/missionservice/participatedusers/user/${this.getLoggedUser().idUser}/${missionid}`)

  }
}
