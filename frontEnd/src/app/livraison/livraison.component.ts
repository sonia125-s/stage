import { Component, OnInit } from '@angular/core';
import {Commande} from "../model/Commande";
import {FormBuilder, FormControl} from "@angular/forms";
import {LivraisonService} from "../services/livraison.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-livraison',
  templateUrl: './livraison.component.html',
  styleUrls: ['./livraison.component.css']
})
export class LivraisonComponent implements OnInit {
  commandes!: Array<Commande>;
  filteredCommandes!:Array<Commande>;
  searchControl ! : FormControl;
  idCommande!:string;
  id:string='';
  valeurPar :number=0;
  idCommandePourProgTransport!:string;
  constructor(private livraisonService : LivraisonService,private fb: FormBuilder,private router:Router, private  route:ActivatedRoute,private fbl: FormBuilder) {
    this.searchControl= this.fb.control('');
  }

  ngOnInit(): void {

    this.getAllCommandes();
    this.searchControl.valueChanges.subscribe(() => {
      this.filterProduit();



    });
  }

  handleVoirDetaille(id: string) {

  }

  private getAllCommandes() {
    this.livraisonService.getAllCommande()
      .subscribe((data: Commande[]) => {
        this.commandes = data;
        this.filteredCommandes = data;
      });
  }

  private filterProduit() {
    const searchId = this.searchControl.value;
    if (!searchId) {
      this.filteredCommandes = this.commandes;
    } else {
      this.filteredCommandes = this.commandes.filter(commande => commande.id === searchId);
    }
  }

  metreEnLivraison(id: string) {
this.livraisonService.mettreEnLivraison(id).subscribe(
()=>{
  this.livraisonService.getAllCommande().subscribe((data: Commande[]) => {
    this.filteredCommandes = data;
  });
    }
);

  }
}
