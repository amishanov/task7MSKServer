package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.IdDTO;
import com.sbt.task7mskserver.dto.NickNameDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    /**
     * Регистрация нового клиента в системе
     *
     * @param nickNameDTO - DTO, содержащий псевдоним клиента
     * @return id созданного клиента
     */
    @PostMapping
    public ResponseEntity<IdDTO> registerNewClient(@RequestBody NickNameDTO nickNameDTO) {
        IdDTO idDTO = new IdDTO(clientService.registerNewClient(nickNameDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(idDTO);
    }

    /**
     * Получение списка диалогов, в которых участвует клиента
     * @param clientId - id клиента
     * @return OK и список диалогов или NOT FOUND, если клиента нет в системе
     */
    // TODO возрат не пустого списка (он и так может быть пустым), а проброс исключения
    // и в случае этого исключение бросать NOT FOUND
    @GetMapping("/{clientId}/dialogs")
    public ResponseEntity<List<IdDTO>> getAllDialogsId(@PathVariable Long clientId) {
        List<IdDTO> response = clientService.getAllDialogsId(clientId).
                stream().map(IdDTO::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Находит всех клиентов системы
     * @return Список всех клиентов системы
     */
    @GetMapping
    public ResponseEntity<List<IdDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<IdDTO> response = new ArrayList<>();
        for (Client client: clients)
            response.add(new IdDTO(client.getId()));
        return ResponseEntity.ok(response);
    }

}
