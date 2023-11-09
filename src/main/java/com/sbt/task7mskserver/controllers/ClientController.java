package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.RequestClientDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private ClientRepository clientRepository;

    //TODO передлать возвращаемый тип
    @PostMapping
    public ResponseEntity<Long> registerNewClient(@RequestBody RequestClientDTO requestClientDTO) {
        Client client = new Client(requestClientDTO.getNickname());
        client = clientRepository.saveAndFlush(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client.getId());
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

}
