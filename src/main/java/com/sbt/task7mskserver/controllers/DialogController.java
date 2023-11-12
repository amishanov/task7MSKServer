package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.ClientToClientFromDTO;
import com.sbt.task7mskserver.dto.IdDTO;
import com.sbt.task7mskserver.repositories.ClientRepository;
import com.sbt.task7mskserver.repositories.DialogRepository;
import com.sbt.task7mskserver.repositories.MessageRepository;
import com.sbt.task7mskserver.repositories.NotificationRepository;
import com.sbt.task7mskserver.services.DialogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/dialogs")
@AllArgsConstructor
public class DialogController {

    DialogRepository dialogRepository;
    ClientRepository clientRepository;
    NotificationRepository notificationRepository;
    MessageRepository messageRepository;
    private DialogService dialogService;

    /**
     * Метод для начала нового диалога
     *
     * @param clientToClientFromDTO id клиента-получателя и id клиента-отправителя
     * @return OK и id нового диалога, если оба клиента в системе
     * или NOT_FOUND и -1, если кто-то из клиентов не в системе
     */
    @PostMapping
    public ResponseEntity<IdDTO> startDialog(@Valid @RequestBody ClientToClientFromDTO clientToClientFromDTO) {
        IdDTO idDTO = new IdDTO(dialogService.startDialog(clientToClientFromDTO));
        if (idDTO.getId() == -1L) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(idDTO.getId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(idDTO);
    }

    /**
     * Метод для удаления диалога. В процессе удаляются все связанные с диалогом сообщения и уведомления
     *
     * @param dialogId - Id удаляемого диалога
     * @return OK
     */
    @DeleteMapping("/{dialogId}")
    @Transactional
    public ResponseEntity<?> stopDialog(@PathVariable Long dialogId) {
        dialogService.stopDialog(dialogId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
