import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CommandeComponent } from './commande/commande.component';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import { LignesCommandeComponent } from './lignes-commande/lignes-commande.component';
import {FormsModule} from "@angular/forms";
import { TransportComponent } from './transport/transport.component';
import { ProduitComponent } from './produit/produit.component';
import { UtilisateursComponent } from './utilisateurs/utilisateurs.component';
import { LoginComponent } from './login/login.component';
import { LivraisonComponent } from './livraison/livraison.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CommandeComponent,
    LignesCommandeComponent,
    TransportComponent,
    ProduitComponent,
    UtilisateursComponent,
    LoginComponent,
    LivraisonComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
