import { Task } from "./task";

export interface Projectworkflow {
  id : number;
  company_id : number;
  deadline : Date;
  debute : Date;
  description : string;
  name : string;
  status : string;
  tasks : Task[]
}
