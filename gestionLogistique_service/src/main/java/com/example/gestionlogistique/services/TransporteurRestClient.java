package com.example.gestionlogistique.services;

import com.example.gestionlogistique.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERS-SERVICE")
public interface TransporteurRestClient {
    @GetMapping(path="/users/provides/{id}")
    public User findTransporteurById(@PathVariable Long id);
}
