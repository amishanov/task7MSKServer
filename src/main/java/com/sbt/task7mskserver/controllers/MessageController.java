package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.MessageDTO;
import com.sbt.task7mskserver.dto.ResponseMessageDTO;
import com.sbt.task7mskserver.models.Message;
import com.sbt.task7mskserver.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {
    MessageService messageService;


    /**
     * Отправка нового сообщения в диалог. При этом создаются новые уведомления для пользователей, состоящих в диалоге.
     *
     * @param messageDTO - DTO для сообщения. Содержит ID диалога, автора сообщения и само сообщение
     * @return CREATED если сообщение создано, NOT FOUND, если пользователя диалога нет в система
     */
    // TODO возврат NOT FOUND через исключение
    @PostMapping
    @Transactional
    public ResponseEntity<?> sendMessage(@Valid @RequestBody MessageDTO messageDTO) {
        messageService.sendMessage(messageDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Получение всех сообщений диалога. В процессе удаляются уведомления по этому диалогу для клиента, который запросил
     * диалог, поскольку считается, что уведомление стало для него неактуальным
     *
     * @param dialogId диалог, для которого запрашиваются сообщения
     * @param clientId id клиента, запросившего диалог. Используется для отчистки уведомлений по этому диалогу
     *                 для этого пользователя
     * @return OK и список сообщений, если такой диалог сущесвтует и NOT_FOUND, если диалога или клиента не существует
     */
    // TODO возврат NOT FOUND через исключение
    // Можно вынести метод в DialogController
    @GetMapping("/{dialogId}/{clientId}")
    public ResponseEntity<List<ResponseMessageDTO>> getMessagesByDialogId(@PathVariable Long dialogId,
                                                                          @PathVariable Long clientId) {
        List<Message> messages = messageService.getMessagesByDialogId(dialogId, clientId);
        List<ResponseMessageDTO> response = new ArrayList<>();
        for (Message message : messages)
            response.add(new ResponseMessageDTO(message.getText(), message.getNickname()));
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
