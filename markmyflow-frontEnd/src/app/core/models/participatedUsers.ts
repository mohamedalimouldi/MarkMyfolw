import { Mission } from "./mission";

export class ParticipatedUsers {
    idPart: number;
    status: string;
    datePArticipation: Date;
    user: number;
    mission:Mission;
    constructor(){
        this.idPart=0;
        this.status="PENDING";
        this.datePArticipation=new Date();
        this.mission=new Mission();
        this.user= 0;

    }
}
