import { empty } from "rxjs";
import { testvalidation } from "./testvalidation";

export class question {

    idq: number;
    question: string ;
    propvrai: string;
    reponses:any;
    prop1:string;
    prop2:string;
    prop3:string;
    validation:testvalidation;

constructor() {
    this.idq = 0;
    this.question = '';
    this.propvrai = '';
    this.prop1='';
    this.prop2='';
    this.prop3='';
    this.validation ;
         
  }
}