package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.RequestClientDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Dialog;
import com.sbt.task7mskserver.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<List<Long>> getAllDialogsId(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        List<Long> response = new ArrayList<>();
        for (Dialog dialog: client.get().getDialogsList())
            response.add(dialog.getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
