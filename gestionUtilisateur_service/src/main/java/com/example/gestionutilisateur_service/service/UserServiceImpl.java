package com.example.gestionutilisateur_service.service;

import com.example.gestionutilisateur_service.dto.User;
import com.example.gestionutilisateur_service.dto.UserLogin;
import com.example.gestionutilisateur_service.entities.UserCredential;
import com.example.gestionutilisateur_service.enums.Role;
import com.example.gestionutilisateur_service.mappsers.UsersMapper;
import com.example.gestionutilisateur_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<User> getTransporteurs(Role role){
       List<UserCredential> list= userRepository.findByRole(role);
       return  list.stream().map(u->usersMapper.fromUserCredential(u)).collect(Collectors.toList());
    }
    @Override
    public User getUser(Long id){
        UserCredential userCredential= userRepository.findById(id).orElse(null);
        return usersMapper.fromUserCredential(userCredential);

    }
    @Override
    public UserCredential addUser(UserCredential userCredential){
        System.out.println("ajout*********");
        System.out.println(userCredential.getEmail());
        return userRepository.save(userCredential);
    }
    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    @Override
    public UserCredential updateUser(UserCredential userCredential){
        return userRepository.save(userCredential);
    }
  @Override
    public List<UserCredential> getAll(){
       return userRepository.findAll();
  }
@Override
    public UserCredential login(UserLogin userLogin){
    UserCredential userCredential = userRepository.findByUsername(userLogin.getUsername());

    if (userCredential == null) {
        throw new RuntimeException("Nom d'utilisateur n'existe pas");
    } else {
        if (userCredential.getPassword().equals(userLogin.getPassword())) {
            return userCredential;
        } else {
            throw new RuntimeException("Mot de passe incorrect");
        }
    }
}
}
