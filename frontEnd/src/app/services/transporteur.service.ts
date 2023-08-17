import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {VehiculeDTO} from "../model/Vehicule";

import {TransportDTO} from "../model/Transport";
import {User} from "../model/Transporteur";
@Injectable({
  providedIn: 'root'
})
export class TransporteurService {

  constructor(private http: HttpClient) { }

  public getAllVehicule() {
    return this.http.get<Array<VehiculeDTO>>("http://localhost:8888/SERVICE-LOGISTIQUE/logistique/vehicule");

}

public getAllTransporteur(){
    return this.http.get<Array<User>>("http://localhost:8888/USERS-SERVICE/users/providers")
}
public getAllTransport(){
    return this.http.get<Array<TransportDTO>>("http://localhost:8888/SERVICE-LOGISTIQUE/logistique/transport");
}
public AttributCommandeTransport( idCommande:string,id:string){
    return this.http.get("http://localhost:8888/SERVICE-LOGISTIQUE/logistique/attributTransportExiste?idCommande="+idCommande+"&idTransport="+id);
}
public  ajoutTransport(idCommande : string, type:string,dateSortie : Date,idTransporteur :string,idVehicule :string){
    return this.http.get("http://localhost:8888/SERVICE-LOGISTIQUE/logistique/addTransport?idCommande="+idCommande+"&type="+type+"&dateSortie="+dateSortie+"&idTransporteur="+idTransporteur+"&idVehicule="+idVehicule).subscribe();

}
}
