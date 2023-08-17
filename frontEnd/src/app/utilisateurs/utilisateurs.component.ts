import { Component, OnInit } from '@angular/core';
import {UserCredential} from "../model/user";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {UtilisateursService} from "../services/utilisateurs.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-utilisateurs',
  templateUrl: './utilisateurs.component.html',
  styleUrls: ['./utilisateurs.component.css']
})
export class UtilisateursComponent implements OnInit {
users!: Array<UserCredential>;
filtredUsers!:Array<UserCredential>;
searchControl!:FormControl;
idUser!:string;
  showEditForm: boolean = false; // Variable pour contrôler l'affichage du formulaire de modification
  selectedUser!: UserCredential; // Variable pour stocker le produit sélectionné pour la modification
  modifierUser!:FormGroup;
  user!:UserCredential;
  ajoutUser!:FormGroup;
  showaddForm :boolean = false;
  userTST!:UserCredential;

  constructor(private  userService : UtilisateursService,private fb: FormBuilder,private f: FormBuilder,private fbb: FormBuilder,private router:Router,private  route:ActivatedRoute) {
    this.searchControl=this.fb.control('');
  }

  ngOnInit(): void {



    this.modifierUser=this.f.group({
      id : this.fb.control(""),
      nom : this.fb.control(""),
      email : this.fb.control(""),
      username : this.fb.control(""),
      password : this.fb.control(""),

      role : this.fb.control("")
    });
    this.ajoutUser=this.fbb.group({
      id : this.fb.control(""),
      nom : this.fb.control(""),
      email : this.fb.control(""),
      username : this.fb.control(""),
      password : this.fb.control(""),
      role : this.fb.control("")
    })







    this.getAllUser();
    this.searchControl.valueChanges.subscribe(() => {
      this.filterUser();



    });





  }


  getAllUser(){
    this.userService.getAllUsers()
      .subscribe((data: UserCredential[]) => {
        this.users = data;
        this.filtredUsers = data;
      });
  }




  filterUser(){
    const searchId = this.searchControl.value;
    if (!searchId) {
      this.filtredUsers = this.users;
    } else {
      this.filtredUsers = this.users.filter(commande => commande.id === searchId);
    }
  }




  updateUser(user: UserCredential) {
    this.selectedUser = user;
    this.showEditForm = true;
    this.modifierUser.patchValue({
      id:user.id,
      nom:user.nom,
     email:user.email,
      username:user.username,
      password:user.password,
      role:user.role,
    })
  }

  deteteUser(id: string) {
    this.userService.deleteUser(id).subscribe(
      () => {
        // Suppression du produit réussie, mettez à jour la liste des produits
        this.userService.getAllUsers().subscribe((data: UserCredential[]) => {
          this.filtredUsers = data;
        });
      },
      (error) => {
        // Gérer les erreurs de la requête de suppression ici si nécessaire
        console.error('Erreur lors de la suppression du produit:', error);
      }
    );
  }













  ajouterUser() {
    this.showaddForm=true;
    this.showEditForm = false;
  }
  finishEdit() {
    this.showEditForm = false;
  }





  enregistrerModification() {
    // Mettre à jour le produit sélectionné avec les valeurs du formulaire
    this.selectedUser.id = this.modifierUser.value.id;
    this.selectedUser.nom = this.modifierUser.value.nom;
    this.selectedUser.email = this.modifierUser.value.email;
    this.selectedUser.username = this.modifierUser.value.username;
    this.selectedUser.password = this.modifierUser.value.password;
    this.selectedUser.role = this.modifierUser.value.role;
    // Appeler le service pour mettre à jour le produit
    this.userService.updateUser(this.selectedUser.id, this.selectedUser).subscribe(
      (produitUpdated) => {
        // Mise à jour du produit réussie, mettez à jour la liste des produits
        this.userService.getAllUsers().subscribe((data: UserCredential[]) => {
          this.filtredUsers = data;
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


  ajouterUserr() {
    const nouveauProduit: UserCredential = {
      id: this.ajoutUser.value.id,
      nom: this.ajoutUser.value.nom,
      email: this.ajoutUser.value.email,
      username: this.ajoutUser.value.username,
      password: this.ajoutUser.value.password,
      role: this.ajoutUser.value.role,

    };
    console.log("*****les valeurs de l'ajout produit*******");
    console.log(nouveauProduit);

    this.userService.saveUser(nouveauProduit).subscribe(
      (produit)=>{
        this.userService.getAllUsers().subscribe((data:UserCredential[])=>{
          this.filtredUsers=data;
        });

      },
      (error)=>{
        console.error('erreur:',error);
      }
    );
    this.showaddForm=false;
  }
}
