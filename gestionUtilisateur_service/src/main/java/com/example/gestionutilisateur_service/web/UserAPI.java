package com.example.gestionutilisateur_service.web;

import com.example.gestionutilisateur_service.dto.User;
import com.example.gestionutilisateur_service.dto.UserLogin;
import com.example.gestionutilisateur_service.entities.UserCredential;
import com.example.gestionutilisateur_service.enums.Role;
import com.example.gestionutilisateur_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("/users/providers")
    public List<User>  listProviders(){
       return userService.getTransporteurs(Role.TRANSPORTEUR) ;
    }
    @GetMapping("/users/administrateurs")
    public List<User>  listAdministrateurs(){
        return userService.getTransporteurs(Role.ADMINISTRATEUR) ;
    }
    @GetMapping("/users/agent_logistique")
    public List<User>  listAgentLogistique(){
        return userService.getTransporteurs(Role.AGENTLOGISTIQUE) ;
    }

    @PostMapping("/users/save")
    public UserCredential save( @RequestBody UserCredential userCredential){
        return userService.addUser(userCredential);
    }
    @DeleteMapping("users/user/{id}")
    public  void deleteProduit (@PathVariable Long id){
        userService.deleteUser(id);
    }

  @PutMapping("users/useer/{id}")
  public UserCredential updateUser(@PathVariable Long id ,@RequestBody UserCredential userCredential){
        userCredential.setId(id);
        return  userService.updateUser(userCredential);
  }

    @GetMapping("/users/all")
    public List<UserCredential> getAll(){
           return userService.getAll();
    }

    @GetMapping("/users/provides/{id}")
    public User getProvider(@PathVariable(name="id") long userId){

        return userService.getUser(userId);
    }

@PostMapping("/users/login")
public ResponseEntity<?> loginUser(@RequestBody UserLogin userLogin) {
    try {
        UserCredential userCredential = userService.login(userLogin);
        return ResponseEntity.ok(userCredential);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

}
