package com.example.gestionutilisateur_service.service;

import com.example.gestionutilisateur_service.dto.User;
import com.example.gestionutilisateur_service.dto.UserLogin;
import com.example.gestionutilisateur_service.entities.UserCredential;
import com.example.gestionutilisateur_service.enums.Role;

import java.util.List;

public interface UserService {


    List<User> getTransporteurs(Role role);

    User getUser(Long id);

    UserCredential addUser(UserCredential userCredential);

    void deleteUser(Long id);

    UserCredential updateUser(UserCredential userCredential);

    List<UserCredential> getAll();

    UserCredential login(UserLogin userLogin);
}
