import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProduitDTO} from "../model/DetailleCommande";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProduitsService {

  constructor(private http:HttpClient) { }


  public getAllProduits (){
    return this.http.get<Array<ProduitDTO>>("http://localhost:8091/logistique/produits");
  }
  public getAllProduit ():Observable<Array<ProduitDTO>>{
    return this.http.get<Array<ProduitDTO>>("http://localhost:8091/logistique/produits");
  }
  public getProduitById(id : number):Observable<ProduitDTO>{
    return this.http.get<ProduitDTO>("http://localhost:8091/logistique/produit/"+id);
  }
  public updateProduit(id : string, produitDTO :ProduitDTO): Observable<ProduitDTO>{
    return this.http.put<ProduitDTO>("http://localhost:8091/logistique/produitt/"+id ,produitDTO);
  }
  public  deleteProduit(id:string){
    return this.http.delete("http://localhost:8091/logistique/produits/"+id);
  }
  public saveProduit(produitsDTO : ProduitDTO):Observable<ProduitDTO>{
    return this.http.post<ProduitDTO>("http://localhost:8091/logistique/produits",produitsDTO);
  }
}
