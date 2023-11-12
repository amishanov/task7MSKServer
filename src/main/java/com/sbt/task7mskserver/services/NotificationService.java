package com.sbt.task7mskserver.services;

import com.sbt.task7mskserver.models.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getUpdatesForMe(Long id);
}
