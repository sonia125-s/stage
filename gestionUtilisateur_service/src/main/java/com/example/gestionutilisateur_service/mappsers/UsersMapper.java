package com.example.gestionutilisateur_service.mappsers;

import com.example.gestionutilisateur_service.dto.User;
import com.example.gestionutilisateur_service.entities.UserCredential;
import org.springframework.stereotype.Service;

@Service
public class UsersMapper {


    public User fromUserCredential(UserCredential userCredential){
        User user=new User();
        user.setId(userCredential.getId());
        user.setNom(userCredential.getNom());
        user.setEmail(userCredential.getEmail());
        user.setRole(userCredential.getRole());
        return user;

    }

    public UserCredential fromUser(User user){
        UserCredential userCredential=new UserCredential();
        userCredential.setId(user.getId());
        userCredential.setNom(user.getNom());
        userCredential.setEmail(user.getEmail());
        userCredential.setRole(user.getRole());
        return userCredential;

    }

}
