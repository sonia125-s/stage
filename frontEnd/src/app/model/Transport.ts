import {VehiculeDTO} from "./Vehicule";
import {User} from "./Transporteur";


export interface TransportDTO{
  id:string;
  type:string;
  dateSortie :Date;
  transporteur :User;
  vehiculeDTO : VehiculeDTO;

}
