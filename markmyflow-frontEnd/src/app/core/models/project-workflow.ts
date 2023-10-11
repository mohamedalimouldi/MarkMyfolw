export class ProjectWorkflow {
    id: number;
    name: string;
    description: string;
    deadline: Date;
    debute: Date;
    companyId: number;
    constructor(){
        this.id=0;
        this.name="";
        this.description="";
        this.deadline=new Date();
        this.debute=new Date();
        this.companyId= 0;

    }
}
