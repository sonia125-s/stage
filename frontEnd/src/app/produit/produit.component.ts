import { Component, OnInit } from '@angular/core';
import {ProduitDTO} from "../model/DetailleCommande";
import {ProduitsService} from "../services/produits.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Commande} from "../model/Commande";
import {CommandeService} from "../services/commande.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit {
  produits!: Array<ProduitDTO>;
  filteredProduits!:Array<ProduitDTO>;
  searchControl ! : FormControl;
  idProduit!:string;
  showEditForm: boolean = false; // Variable pour contrôler l'affichage du formulaire de modification
  selectedProduit!: ProduitDTO; // Variable pour stocker le produit sélectionné pour la modification
   modifierProduit!:FormGroup;
   produit!:ProduitDTO;
  ajoutProduit!:FormGroup;
  showaddForm :boolean = false;
  produitTST!:ProduitDTO;
  constructor(private produitService : ProduitsService,private fb: FormBuilder,private f: FormBuilder,private fbb: FormBuilder, private router:Router, private  route:ActivatedRoute) {
    this.searchControl= this.fb.control('');

  }

  ngOnInit(): void {

    this.modifierProduit=this.f.group({
      id : this.fb.control(""),
      prix : this.fb.control(""),
      description : this.fb.control("")
    });
    this.ajoutProduit=this.fbb.group({
      id : this.fb.control(""),
      prix : this.fb.control(""),
      description : this.fb.control("")
    })


      this.getAllCommandes();
      this.searchControl.valueChanges.subscribe(() => {
        this.filterProduit();



    });
  }
  getAllCommandes(): void {
    this.produitService.getAllProduit()
      .subscribe((data: ProduitDTO[]) => {
        this.produits = data;
        this.filteredProduits = data;
      });
  }

  filterProduit(): void {
    const searchId = this.searchControl.value;
    if (!searchId) {
      this.filteredProduits = this.produits;
    } else {
      this.filteredProduits = this.produits.filter(commande => commande.id === searchId);
    }
  }

  updateProduit(produit: ProduitDTO) {
    this.selectedProduit = produit;
    this.showEditForm = true;
    this.modifierProduit.patchValue({
      id:produit.id,
      prix:produit.prix,
      description:produit.description
    })

  }

  deteteProduit(id: string) {
    this.produitService.deleteProduit(id).subscribe(
      () => {
        // Suppression du produit réussie, mettez à jour la liste des produits
        this.produitService.getAllProduits().subscribe((data: ProduitDTO[]) => {
          this.filteredProduits = data;
        });
      },
      (error) => {
        // Gérer les erreurs de la requête de suppression ici si nécessaire
        console.error('Erreur lors de la suppression du produit:', error);
      }
    );
  }

  ajouterProduit() {
    this.showaddForm=true;
    this.showEditForm = false;
  }
  finishEdit() {
    this.showEditForm = false;
  }


  enregistrerModification() {
    // Mettre à jour le produit sélectionné avec les valeurs du formulaire
    this.selectedProduit.id = this.modifierProduit.value.id;
    this.selectedProduit.prix = this.modifierProduit.value.prix;
    this.selectedProduit.description = this.modifierProduit.value.description;

    // Appeler le service pour mettre à jour le produit
    this.produitService.updateProduit(this.selectedProduit.id, this.selectedProduit).subscribe(
      (produitUpdated) => {
        // Mise à jour du produit réussie, mettez à jour la liste des produits
        this.produitService.getAllProduits().subscribe((data: ProduitDTO[]) => {
          this.filteredProduits = data;
        });
        console.log('Produit mis à jour:', produitUpdated);
      },
      (error) => {
        // Gérer les erreurs de la requête de mise à jour ici si nécessaire
        console.error('Erreur lors de la mise à jour du produit:', error);
      }
    );

    this.finishEdit();
  }

  finishajout() {
    this.showaddForm=false;
  }

  ajouterProd() {


    const nouveauProduit: ProduitDTO = {
      id: this.ajoutProduit.value.id,
      prix: this.ajoutProduit.value.prix,
      description: this.ajoutProduit.value.description
    };
console.log("***produit");
console.log(nouveauProduit);
    this.produitService.saveProduit(nouveauProduit).subscribe(
      (produit)=>{
        this.produitService.getAllProduits().subscribe((data:ProduitDTO[])=>{
          this.filteredProduits=data;
        });

      },
      (error)=>{
        console.error('erreur:',error);
      }
      );
    this.showaddForm=false;
  }
}
