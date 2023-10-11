import { question } from "./question";

export class testvalidation {

    idv: number;
    duree: number ;   
    image: any;
    titre: string;
    pts: number;
    questions:Array<question>;

constructor() {
    this.idv = 0;
    this.duree= 0;    //this.image = '';
    this.titre = '';
    this.pts=0;
    
    // additional properties here
  }

}