import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommandeService} from "../services/commande.service";
import {catchError, Observable, throwError} from "rxjs";
import {DetailleCommandeDTO, ProduitDTO} from "../model/DetailleCommande";
import {ProduitsService} from "../services/produits.service";

@Component({
  selector: 'app-lignes-commande',
  templateUrl: './lignes-commande.component.html',
  styleUrls: ['./lignes-commande.component.css']
})
export class LignesCommandeComponent implements OnInit {
  idCommande !:string;
  commandeFormGroupe ! : FormGroup;
  errorMessage ! :string;
  commandeDetail !: DetailleCommandeDTO;




  produits ! : Array<ProduitDTO>;
  ajoutLigneForm! : FormGroup;
  constructor(private  fb : FormBuilder,private commandeService:CommandeService, private  route: ActivatedRoute, private  router :Router, private f :FormBuilder, private produitService : ProduitsService) {

  }

  ngOnInit(): void {
    this.route.params.subscribe(params =>{
      this.idCommande=params['id'];


    });
      if(this.idCommande!='0'){
        this.commandeService.getCommadeDetaille(this.idCommande).subscribe(
          (detailleCommade)=>{
            this.commandeDetail=detailleCommade;
          },
          (error)=>{
            console.error(error);
          }
        );

      }
    this.commandeFormGroupe=this.fb.group({
     commandeId: this.fb.control('')
    });


      this.ajoutLigneForm=this.f.group({
        produit: [null, Validators.required],
        quantite: [null, [Validators.required, Validators.min(1)]]
      });

     this.produitService.getAllProduits().subscribe(
       (produits)=>{
         this.produits=produits;
       },
       (error)=>{
         console.error("erreur lors  de la recuperation des produits");
       }
     );



  }

  handlRechercheCommande() {
   let commandeId :string =this.commandeFormGroupe.value.commandeId;
   this.idCommande=commandeId;
  this.commandeService.getCommadeDetaille(commandeId).subscribe(
    (detailleCommade)=>{
      this.commandeDetail=detailleCommade;
    },
    (error)=>{
      console.error(error);
    }
  );


  }

  hundleAjouterLigneCommande() {
   let produitId:string=this.ajoutLigneForm.value.produit;
   let quantite : number=this.ajoutLigneForm.value.quantite;
   this.commandeService.saveLigneCommande(this.idCommande,produitId,quantite).subscribe(
     ()=>{
       console.log('Ligne de commande ajoutée avec succès !');
     },
     (error) => {
       // Gérer l'erreur si nécessaire
       console.error('Erreur lors de l\'ajout de la ligne de commande :', error);
     }
   );

    this.handlRechercheCommande();
  }

  annulerLigneCommande(id: string) {



  }
}
