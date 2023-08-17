import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ClientPhysique} from "../model/ClientPhysique";
import {ClientMorale} from "../model/ClientMorale";
import {Commande} from "../model/Commande";
import {DetailleCommandeDTO} from "../model/DetailleCommande";
@Injectable({
  providedIn: 'root'
})
export class CommandeService {

  constructor(private http:HttpClient) { }

  public searchClientPhysique(keyword :string):Observable<Array<ClientPhysique>>{
    return this.http.get<Array<ClientPhysique>>("http://localhost:8091/logistique/clientPhysique/search?keyword="+keyword);

  }
  public searchClientMorale(keyword :string):Observable<Array<ClientMorale>>{
    return this.http.get<Array<ClientMorale>>("http://localhost:8091/logistique/clientMorale/search?keyword="+keyword);

  }

public affichCommandesClient(idClient: string):Observable<Array<Commande>>{
    return this.http.get<Array<Commande>>("http://localhost:8091/logistique/commandesClient/"+idClient);
}
public  deleteCommande(id: string){
    return this.http.delete("http://localhost:8091/logistique/commandeClient/"+id);
}

public ajoutCommande(date_envoi : Date , lieu : string, idClient : string){

    return this.http.get("http://localhost:8091/logistique/savecommandeclient?dateEnvoi="+date_envoi+"&lieu="+lieu+"&idClient="+idClient);
}

  public  getCommadeDetaille(commandeId: string) {
    return this.http.get<DetailleCommandeDTO>("http://localhost:8091/logistique/detailleCommande/"+commandeId);
  }
  public saveLigneCommande(idCommande : string,idProsuit : string,quantite : number){
    return this.http.get("http://localhost:8091/logistique/ligneCommande?idCommande="+idCommande+"&idProduit="+idProsuit+"&quantite="+quantite);
  }
  public getAllCommandes(): Observable<Array<Commande>>{
    return  this.http.get<Array<Commande>>("http://localhost:8091/logistique/allCommande");

  }
  public getCommandesById(id: number):Observable<Array<Commande>>{
    return this.http.get<Array<Commande>>("http://localhost:8091/logistique/commandeById/"+id);
  }
}
