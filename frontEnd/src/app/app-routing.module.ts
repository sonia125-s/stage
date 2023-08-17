import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CommandeComponent} from "./commande/commande.component";
import {LignesCommandeComponent} from "./lignes-commande/lignes-commande.component";
import {TransportComponent} from "./transport/transport.component";
import {ProduitComponent} from "./produit/produit.component";
import {UtilisateursComponent} from "./utilisateurs/utilisateurs.component";
import {LoginComponent} from "./login/login.component";
import {LivraisonComponent} from "./livraison/livraison.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {path : "login" , component : LoginComponent},
  {path : "" , redirectTo : "/login",pathMatch : "full"},
  {path :"home", component : HomeComponent, children:[
      {path :"commandes", component : CommandeComponent},
      {path :"lignesCommande/:id" , component :LignesCommandeComponent},
      {path : "transport/:id" ,component: TransportComponent},
      {path : "produit" , component : ProduitComponent},
      {path : "utilisateurs" , component : UtilisateursComponent},

      {path : "livraison" , component : LivraisonComponent},
    ]},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
