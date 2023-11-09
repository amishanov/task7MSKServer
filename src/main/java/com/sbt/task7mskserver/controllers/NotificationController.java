package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.ResponseNotificationDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Notification;
import com.sbt.task7mskserver.repositories.ClientRepository;
import com.sbt.task7mskserver.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    NotificationRepository notificationRepository;
    ClientRepository clientRepository;

    @GetMapping("/{clientId}")
    @Transactional
    public ResponseEntity<List<ResponseNotificationDTO>> getUpdatesForMe(@PathVariable Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        List<Notification> notifications = notificationRepository.findAllByClient_Id(clientId);
        List<ResponseNotificationDTO> response = new ArrayList<>();
        for(Notification notification: notifications)
            response.add(new ResponseNotificationDTO(notification.getDialog().getId()));
        return ResponseEntity.ok(response);
    }
}
