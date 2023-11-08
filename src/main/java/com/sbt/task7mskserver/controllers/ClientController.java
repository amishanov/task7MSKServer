package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Clients")
public class ClientController {
    //TODO репозиторий
    // ClientRepository clientRep...
    @PostMapping
    public ResponseEntity<Client> createClient() {
        return ResponseEntity.status(HttpStatus.OK).body(new Client());
    }

}
