export class Mission {
    idMission : number;
    titleMission: string;
    descriptionMission: string;
    deadlineMission : string;
    priceMission: number;
    task: number;

    constructor() {
        this.idMission = 0;
        this.titleMission = '';
        this.descriptionMission = '';
        this.deadlineMission = '';
        this.priceMission=0;
        this.task=0;
        // additional properties here
      }

}
