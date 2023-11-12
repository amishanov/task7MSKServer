package com.sbt.task7mskserver.services;

import com.sbt.task7mskserver.dto.MessageDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Dialog;
import com.sbt.task7mskserver.models.Message;
import com.sbt.task7mskserver.models.Notification;
import com.sbt.task7mskserver.repositories.ClientRepository;
import com.sbt.task7mskserver.repositories.DialogRepository;
import com.sbt.task7mskserver.repositories.MessageRepository;
import com.sbt.task7mskserver.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository;
    DialogRepository dialogRepository;
    NotificationRepository notificationRepository;
    ClientRepository clientRepository;

    /**
     * Фиксация нового сообщения в системе. В процессе фиксации также создаются новые уведомления для клиентов диалога
     * При этом возможен случай, когда клиент уже имеет уведомление по этому диалогу, в этом случае новое уведомление
     * не создаётся
     *
     * @param messageDTO - DTO для сообщения. Содержит ID диалога, автора сообщения и само сообщение
     */
    @Override
    @Transactional
    public void sendMessage(MessageDTO messageDTO) {
        Optional<Dialog> dialog = dialogRepository.findById(messageDTO.getDialogSessionId());
        if (dialog.isEmpty())
            return; // throw NotFoundException;
        messageRepository.save(new Message(dialog.get(),
                messageDTO.getText(), messageDTO.getNickname()));
        Set<Client> clientSet = dialog.get().getClientSet();
        // Находим все уведомления, которые существуют для этого диалога
        List<Notification> notificationSet = notificationRepository.findAllByDialog_Id(dialog.get().getId());

        Set<Client> clientsWithNotifications = new HashSet<>();
        // Убираем клиентов, которые не ещё не посмотрели сообщения из тех, на кого мы будем создавать уведомления
        for (Notification notification : notificationSet) {
            if (clientSet.contains(notification.getClient()))
                clientsWithNotifications.add(notification.getClient());
//            clientSet.remove(notification.getClient());
        }
        // Создаём уведомления для всех клиентов диалога, у которых их нет
        for (Client client : clientSet) {
            if (!clientsWithNotifications.contains(client)) {
                Notification notification = new Notification(client, dialog.get());
                notificationRepository.save(notification);
            }
        }
    }

    /**
     * Нахождение всех сообщений в диалоге. При этом учитывается, кто запросил сообщение (clientID), это необходимо
     * для отчистки уведомлений по этому диалогу у этого пользователя
     *
     * @param dialogId диалог, для которого запрашиваются сообщения
     * @param clientId id клиента, запросившего диалог. Используется для отчистки уведомлений по этому диалогу
     *                 для этого пользователя
     * @return список найденных сообщений
     */
    @Override
    @Transactional
    public List<Message> getMessagesByDialogId(Long dialogId, Long clientId) {
        Optional<Dialog> dialog = dialogRepository.findById(dialogId);
        Optional<Client> client = clientRepository.findById(clientId);
        if (dialog.isEmpty() || client.isEmpty()) {
            return new ArrayList<>();
        }
        List<Message> messages = messageRepository.findAllByDialog(dialog.get());
        notificationRepository.deleteByClient_AndDialog(client.get(), dialog.get());
        return messages;
    }
}
