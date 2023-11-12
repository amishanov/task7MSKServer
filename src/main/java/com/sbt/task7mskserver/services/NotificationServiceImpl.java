package com.sbt.task7mskserver.services;

import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Notification;
import com.sbt.task7mskserver.repositories.ClientRepository;
import com.sbt.task7mskserver.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    NotificationRepository notificationRepository;
    ClientRepository clientRepository;

    /**
     * Находит список уведомлений для клиента.
     *
     * @param id - id клиента
     * @return список уведомлений для клипнте
     */
    // TODO возврат NOT FOUND через throw
    @Override
    public List<Notification> getUpdatesForMe(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty())
            return new ArrayList<>(); // throw NotFoundException
        return notificationRepository.findAllByClient_Id(id);
    }
}
