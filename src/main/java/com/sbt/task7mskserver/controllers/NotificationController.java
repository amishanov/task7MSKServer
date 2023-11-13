package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.dto.IdDTO;
import com.sbt.task7mskserver.models.Notification;
import com.sbt.task7mskserver.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    NotificationService notificationService;

    /**
     * Возвращает список id диалогов, в которых есть непросмотренные сообщения для клиента
     * @param clientId - id клиента, который запросил уведомления
     * @return OK и список Id диалогов с уведомлениями или же NOT FOUND если клиент не присутствует в системе
     */
    @GetMapping("/{clientId}")
    @Transactional
    public ResponseEntity<List<IdDTO>> getUpdatesForMe(@PathVariable Long clientId) {
        List<Notification> notifications = notificationService.getUpdatesForMe(clientId);
        List<IdDTO> response = new ArrayList<>();
        for(Notification notification: notifications)
            response.add(new IdDTO(notification.getDialog().getId()));
        return ResponseEntity.ok(response);
    }
}
