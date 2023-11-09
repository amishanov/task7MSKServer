package com.sbt.task7mskserver.controllers;

import com.sbt.task7mskserver.models.Notification;
import com.sbt.task7mskserver.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    NotificationRepository notificationRepository;

    //TODO добавить обработку отсутствия клиента в системе
    // Ещё можно возвращать тоже DTO, а не модель
    @GetMapping("/{id}")
    public ResponseEntity<List<Notification>> getUpdatesForMe(@PathVariable Long id) {
        return ResponseEntity.ok(notificationRepository.findAllByClient_Id(id));
    }
}
