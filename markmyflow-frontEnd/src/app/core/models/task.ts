export interface Task {
  id: number;
  deadline : Date;
  debute : Date;
  name: string;
  payed : boolean;
  status : string;
  userId : number;
  description : string;
  price:number;
}
