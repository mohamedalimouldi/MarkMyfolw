import { testvalidation } from "./testvalidation";
export class passage{

    idp: number;
    score: number;
    test: testvalidation;
    iduser:number;
    resultat:string;
    

constructor() {
    this.idp = 0;
    this.score = 0;
    this.test=null;
    this.iduser=0;
    this.resultat='';
    
  }
}