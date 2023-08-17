import { Component, OnInit } from '@angular/core';
import {catchError, Observable} from "rxjs";
import {ClientMorale} from "../model/ClientMorale";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommandeService} from "../services/commande.service";
import {Router} from "@angular/router";
import {ClientPhysique} from "../model/ClientPhysique";
import {Commande} from "../model/Commande";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-commande',
  templateUrl: './commande.component.html',
  styleUrls: ['./commande.component.css']
})
export class CommandeComponent implements OnInit {
  var1 : number=1;
  val2 !: string;
  val3 : string ="";
   commandesClient!:Observable<Array<Commande>>;
  clientsMorale!: Observable<Array<ClientMorale>>;
  clientsPhysique!: Observable<Array<ClientPhysique>>;
  errorMessage!:string;
  searchFormGroupeCM : FormGroup |undefined;
  searchFormGroupeCP : FormGroup |undefined;
  ajoutCommande !: FormGroup ;
  constructor(private commandeService :CommandeService,private fb : FormBuilder,private  fbb :FormBuilder, private router :Router, private f :FormBuilder) { }

  ngOnInit(): void {
    this.searchFormGroupeCP=this.fb.group( {
      keyword : this.fb.control("")
      });
     this.handleSearchClientPhysique();
    this.searchFormGroupeCM=this.fbb.group( {
      keyword : this.fbb.control("")
    });
    this.handleSearchClientMorale();

    //*********************formulaire ajout commande ************
    this.ajoutCommande=this.f.group({
      date: this.f.control(null),
      lieu: this.f.control(null)
    });

  }





  handleSearchClientPhysique(){
    let kw =this.searchFormGroupeCP?.value.keyword;
    this.clientsPhysique=this.commandeService.searchClientPhysique(kw);


  }

   handleSearchClientMorale() {
    let kw =this.searchFormGroupeCM?.value.keyword;
    this.clientsMorale=this.commandeService.searchClientMorale(kw);

  }


  hundleAffichCommande(idClient: string) {
    this.var1=2;

    this.commandesClient=this.commandeService.affichCommandesClient(idClient);
    this.val2=idClient;


  }

  hundleAjouterCommande(id: string) {
    this.var1=3;
    this.val3=id;
  }

  hundledeleteCommande(id: string) {

   this.commandeService.deleteCommande(id).subscribe({
     next :(resp )=>{
       this.hundleAffichCommande(this.val2);
       this.val3=id;
     }
   });

  }

  hundleDetailCommande(id: string) {
    this.router.navigateByUrl(`lignesCommande/${id}`);

  }

  handleAjoutCommnade() {

  let date :Date = this.ajoutCommande.value.date;



  let lieu : string =this.ajoutCommande.value.lieu;
   this.commandeService.ajoutCommande(date, lieu,this.val3).subscribe({
     next:()=>{
       alert("Success ADD")
     }
   });


    //this.router.navigateByUrl(`lignesCommande/${this.val3}`);
  }
}
