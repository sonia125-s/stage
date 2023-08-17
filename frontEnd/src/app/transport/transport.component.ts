import { Component, OnInit } from '@angular/core';
import {CommandeService} from "../services/commande.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Commande} from "../model/Commande";
import {ActivatedRoute, Router} from "@angular/router";
import {ProduitDTO} from "../model/DetailleCommande";
import {VehiculeDTO} from "../model/Vehicule";

import {TransporteurService} from "../services/transporteur.service";
import {TransportDTO} from "../model/Transport";
import {User} from "../model/Transporteur";

@Component({
  selector: 'app-transport',
  templateUrl: './transport.component.html',
  styleUrls: ['./transport.component.css']
})
export class TransportComponent implements OnInit {
   commandes!: Array<Commande>;
  filteredCommandes!:Array<Commande>;
  searchControl ! : FormControl;
  idCommande!:string;
  id:string='';
  valeurPar :number=0;
  idCommandePourProgTransport!:string;
  AjoutTransportFrom !:FormGroup;
  vihecules ! : Array<VehiculeDTO>;
  transporteur!: Array<User>;
  showaddForm :boolean = false;
  showList:boolean = false;
  transport!:Array<TransportDTO>;
  idTransportTest!:string;
  constructor(private transporteurService:TransporteurService,private commandeService : CommandeService,private fb: FormBuilder, private router:Router, private  route:ActivatedRoute,private fbl: FormBuilder) {
    this.searchControl= this.fb.control('');
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.valeurPar = params['id'];
      this.getAllCommandes();
      this.searchControl.valueChanges.subscribe(() => {
        this.filterCommandes();
      });

      if (this.valeurPar >0) {
        //this.id = this.idCommande;
        this.searchControl.patchValue(this.valeurPar);
        this.filteredCommandes=[];
       this.commandeService.getCommandesById(this.valeurPar).subscribe((data: Commande[]) => {
         //this.commandes = data;
         this.filteredCommandes = data;
       });
        this.filterCommandes(); // Appel de la méthode pour filtrer les commandes par l'ID dès le chargement du composant
      }
    });


    this.AjoutTransportFrom=this.fbl.group({
      transporteur: [null, Validators.required],
      vehicule: [null, Validators.required],
      date: [null, Validators.required],
      lieu: [null, Validators.required]
    })


  this.transporteurService.getAllVehicule().subscribe(
    (vehicules)=>{
      this.vihecules=vehicules;
    },
    (error)=>{
      console.error(("erreur de get all vehicule"));
    }
  );

this.transporteurService.getAllTransporteur().subscribe(
  (transporteur)=>{
    this.transporteur=transporteur;
  },
  (error)=>{
    console.error(("erreur de get all transporetur"));
  }
);



  }


  getAllCommandes(): void {
    this.commandeService.getAllCommandes()
      .subscribe((data: Commande[]) => {
        this.commandes = data;
        this.filteredCommandes = data;
      });
  }

  filterCommandes(): void {
    const searchId = this.searchControl.value;
    if (!searchId) {
      this.filteredCommandes = this.commandes;
    } else {
      this.filteredCommandes = this.commandes.filter(commande => commande.id === searchId);
    }
  }

  handleAjoutTransport(id: string) {

this.idCommandePourProgTransport=id;
    this.showaddForm=true;
    this.showList=false;

  }

  finishajout() {
    this.showaddForm=false;
  }

  ajoutTransport() {
        let idTransporteur: string=this.AjoutTransportFrom.value.transporteur;
    let idVehicule: string=this.AjoutTransportFrom.value.vehicule;
    let type: string=this.AjoutTransportFrom.value.lieu;
    let dateSortie: Date=this.AjoutTransportFrom.value.date;
    this.transporteurService.ajoutTransport(this.idCommandePourProgTransport,type,dateSortie,idTransporteur,idVehicule);
    this.showaddForm=false;
  }

  handleListerTransport() {
    this.showList=true;

    this.showaddForm=false;
    this.transporteurService.getAllTransport().subscribe(
      (transports)=>{
        this.transport=transports;
      },
      (error)=>{
        console.error(("erreur de get all transport"));
      }
    );
  }

  finishLister() {
    this.showList=false;
  }


  HandleAjoutCommandeDansCeTransport(id: string) {
    this.idTransportTest=id;
    this.transporteurService.AttributCommandeTransport(this.idCommandePourProgTransport,id).subscribe(
      ()=>{
        console.log('Ligne de commande ajoutée avec succès !');
      },
      (error) => {
        // Gérer l'erreur si nécessaire
        console.error('Erreur lors de l\'ajout de la ligne de commande :', error);
      }
    );
    this.showList=false;
  }
}
