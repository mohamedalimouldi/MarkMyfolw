import { Company } from "./company";
import { TypeSubscription } from "./type-subscription";

export class Subscription {
    id: number;
    prixAbon: number;
    dateDebut: Date;
    dateFin: Date;
    typeSubscription: TypeSubscription;
    entreprise?: Company;
  
    constructor() {
      this.id = 0;
      this.prixAbon = 0.0; // declare prix_abon as a float variable
      this.dateDebut = new Date();
      this.dateFin = new Date();
      this.typeSubscription = TypeSubscription.annuel;
      this.entreprise = new Company();
    }
  }
