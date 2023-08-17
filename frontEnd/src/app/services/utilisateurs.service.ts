import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserCredential} from "../model/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UtilisateursService {

  constructor(private http:HttpClient) { }


  public getAllUsers ():Observable<Array<UserCredential>>{
    return this.http.get<Array<UserCredential>>("http://localhost:8090/users/all");
  }


  public updateUser(id : string, user:UserCredential): Observable<UserCredential>{
    return this.http.put<UserCredential>("http://localhost:8090/users/useer/"+id ,user);
  }
  public  deleteUser(id:string){
    return this.http.delete("http://localhost:8090/users/user/"+id);
  }
  public saveUser(user:UserCredential):Observable<UserCredential>{
    return this.http.post<UserCredential>("http://localhost:8090/users/save",user);
  }

}
