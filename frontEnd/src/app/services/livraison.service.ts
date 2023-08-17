import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {Commande} from "../model/Commande";

@Injectable({
  providedIn: 'root'
})
export class LivraisonService {
etat:string='PREPARATION';
  constructor(private http :HttpClient,private loginService :AuthService) { }
  public getAllCommande():Observable<Array<Commande>>{
    return this.http.get<Array<Commande>>("http://localhost:8091/logistique/livraisonCommande?etat="+this.etat+"&&id="+this.loginService.id)
  }
  public mettreEnLivraison(id :string){
    return this.http.get("http://localhost:8091/logistique/mettreLivraison/"+id)
  }
}
