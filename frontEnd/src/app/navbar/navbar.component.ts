import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
isADMINISTRATEUR!:boolean;
isAGENTLOGISTIQUE!:boolean;
isTRANSPORTEUR!:boolean;
  constructor(private authService :AuthService,private router: Router) {
    this.isADMINISTRATEUR=authService.isADMINISTRATEUR;
    this.isTRANSPORTEUR=authService.isTRANSPORTEUR;
    this.isAGENTLOGISTIQUE=authService.isAGENTLOGISTIQUE;
  }

  ngOnInit(): void {
    this.isADMINISTRATEUR=this.authService.isADMINISTRATEUR;
    this.isTRANSPORTEUR=this.authService.isTRANSPORTEUR;
    this.isAGENTLOGISTIQUE=this.authService.isAGENTLOGISTIQUE;
  }


  logout() {
    this.router.navigate(['/login']);
  this.authService.logout();
  }
}
