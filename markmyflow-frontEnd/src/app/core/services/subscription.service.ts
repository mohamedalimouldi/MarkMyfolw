import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../models/company';
import { packet } from '../models/packet';
import { Subscription } from '../models/subscription';

@Injectable({
  providedIn: 'root'
})

export class SubscriptionService {

  list: packet[]
  Packest : packet[]=[
    {
      id: 1,
      prixAbon: 500, // declare prix_abon as a float variable
      title:"Basic Plan ",
      type: "mensuel"
    },
    {
      id: 2,
      prixAbon: 2900, // declare prix_abon as a float variable
      title:"Standard Plan",
      type: "semestriel"

    },
    {
      id: 3,
      prixAbon: 5000, // declare prix_abon as a float variable
      title:"Extended Plan      ",
      type: "annuel"

    }
  ]
  private apiServerUrl: string

  constructor( private http : HttpClient ) {
    this.apiServerUrl = '/companyservice/subscription'; }
    public getSubscription():Observable<Subscription[]>{
      return this.http.get<Subscription[]>(`${this.apiServerUrl}/getAll`);
    }
    public addSubscription(subscription: any):Observable<Subscription>{
      return this.http.post<Subscription>(`${this.apiServerUrl}/add`, subscription)
    }
    public deleteSubscription(id: number): Observable<Subscription>{
      return this.http.delete<Subscription>(`${this.apiServerUrl}/delete/${id}`)
    }
    public updateSubscription(s: Subscription): Observable<Subscription>{
      return this.http.put<Subscription>(`${this.apiServerUrl}/update`, s)
    }
    public getstaticData(dateDebut: Date, dateFin: Date): Observable<any[]>{
      return this.http.get<any[]>(`${this.apiServerUrl}/graph?dateDebut=${dateDebut}&dateFin=${dateFin}`)
    }
    public getrevenuedata(year: string): Observable<any[]>{
      return this.http.get<any[]>(`${this.apiServerUrl}/earnings-by-month?year=${year}`)
    }
    public getPacket(){
      return this.Packest;
    }
    public getPacketById(packetId: number ): packet  {
      return this.Packest.find(packet => packet.id === packetId);
    }
  
    }
    

