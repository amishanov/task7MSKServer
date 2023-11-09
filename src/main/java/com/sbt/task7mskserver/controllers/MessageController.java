package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.RequestMessageDTO;
import com.sbt.task7mskserver.dto.RequestNotificationDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Dialog;
import com.sbt.task7mskserver.models.Message;
import com.sbt.task7mskserver.models.Notification;
import com.sbt.task7mskserver.repositories.ClientRepository;
import com.sbt.task7mskserver.repositories.DialogRepository;
import com.sbt.task7mskserver.repositories.MessageRepository;
import com.sbt.task7mskserver.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {
    MessageRepository messageRepository;
    DialogRepository dialogRepository;
    NotificationRepository notificationRepository;
    ClientRepository clientRepository;

    //TODO точно перенести в отдельный сервис, слишком сложная бизнес логика (или просто сдаться уже и сделать по ТЗ)

    // Следите за руками: находим диалог, в который хотим добавить сообщение, если нет - и суда нет, если есть
    // - создаём новое соообщение для него.
    // А теперь начинается шизофрения: все уведомления,
    // связанные с этим диалогом (потому что мы типа делаем гибко и с не фиксированным количеством пользователей)
    // мы находим и ИЗВЛЕКАЕМ список пользователей, для которых существует уведомления
    // (то есть тех, кто ещё не смотрел свои новые сообщения в этом диалоге).
    // Теперь у нас остались участники диалога, у которых нет уведомлений из этого диалога, поэтому мы из ДИАЛОГА
    // достаём СПИСОК КЛИЕНТОВ и сравниваем его с списком клиентов, который мы ранее извлекли из существующих уведомлений
    // наконец, мы создаём новые уведомления для всех клиентов, для которых этих уведомлений ранее не было
    // Этот функционал выглядет особо смешно, если понимать, что в реальности это вообще не так должно работать
    @PostMapping
    @Transactional
    public ResponseEntity<?> sendMessage(@RequestBody RequestMessageDTO requestMessageDTO) {
        Optional<Dialog> dialog = dialogRepository.findById(requestMessageDTO.getDialogSessionId());
        if (dialog.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        messageRepository.saveAndFlush(new Message(dialog.get(),
                requestMessageDTO.getText(), requestMessageDTO.getNickname()));

        Set<Client> clientSet = dialog.get().getClientSet();
        List<Notification> notificationSet = notificationRepository.findAllByDialog_Id(dialog.get().getId());

        // Убираем клиентов, которые не ещё не посмотрели сообщения из тех, на кого мы будем создавать уведомления
        for (Notification notification: notificationSet) {
            clientSet.remove(notification.getClient());
        }
        // Создаём уведомления для всех клиентов диалога, у которых их нет
        for(Client client: clientSet) {
            Notification notification = new Notification(client, dialog.get());
            notificationRepository.save(notification);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Получение всех сообщений диалога и удаление уведомлений по этому диалогу для пользователя, запросившего диалог
     * @param dialogId диалог, для которого запрашиваются сообщения
     * @param requestNotificationDTO id клиента, запросившего диалог. Используется для отчистки уведомлений
     * @return
     */
    @GetMapping("/{dialogId}")
    @Transactional
    public ResponseEntity<List<Message>> getMessagesByDialogId(@PathVariable Long dialogId,
                                               @RequestBody RequestNotificationDTO requestNotificationDTO) {
        Optional<Dialog> dialog = dialogRepository.findById(dialogId);
        Optional<Client> client = clientRepository.findById(requestNotificationDTO.getClientId());
        if (dialog.isEmpty() || client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
        notificationRepository.deleteByClient_AndDialog(client.get(), dialog.get());
        return ResponseEntity.status(HttpStatus.FOUND).body(messageRepository.findAllByDialog(dialog.get()));

    }
}
