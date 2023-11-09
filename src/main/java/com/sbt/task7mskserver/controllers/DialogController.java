package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.RequestDialogDTO;
import com.sbt.task7mskserver.dto.RequestNotificationDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Dialog;
import com.sbt.task7mskserver.repositories.ClientRepository;
import com.sbt.task7mskserver.repositories.DialogRepository;
import com.sbt.task7mskserver.repositories.MessageRepository;
import com.sbt.task7mskserver.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/dialogs")
@AllArgsConstructor
public class DialogController {

    //TODO рассмотреть вынос логики в сервисы
    DialogRepository dialogRepository;
    ClientRepository clientRepository;
    NotificationRepository notificationRepository;
    MessageRepository messageRepository;

    /**
     * Метод проверяет наличие ID клиента отправителя и клиента получателя в БД и создаёт новый диалог с ним
     * @param requestDialogDTO for client-sender id and client-receiver id
     * @return OK и id нового диалога, если оба клиента в системе
     * или NOT_FOUND и -1, если один из клиентов не в системе
     */
    @PostMapping
    @Transactional
    public ResponseEntity<Long> startDialog(@RequestBody RequestDialogDTO requestDialogDTO) {
        Optional<Client> clientFrom = clientRepository.findById(requestDialogDTO.getClientFromId());
        Optional<Client> clientTo = clientRepository.findById(requestDialogDTO.getClientToId());
        if (clientFrom.isEmpty() || clientTo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1L);
        }
        Set<Client> clientSet = new HashSet<>();
        clientSet.add(clientFrom.get());
        clientSet.add(clientTo.get());
        Dialog dialog = new Dialog();
        dialog.setClientSet(clientSet);
        dialog = dialogRepository.saveAndFlush(dialog);
        return ResponseEntity.status(HttpStatus.CREATED).body(dialog.getId());
    }

    // Удаление диалога, всех связанных с ним сообщений и уведомлений
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Long> stopDialog(@PathVariable Long id) {
        dialogRepository.deleteById(id);
        notificationRepository.deleteAllByDialog_Id(id);
        messageRepository.deleteAllByDialog_Id(id);
        return ResponseEntity.ok(id);
    }
}
