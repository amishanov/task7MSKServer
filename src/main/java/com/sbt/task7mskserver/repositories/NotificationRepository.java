package com.sbt.task7mskserver.repositories;

import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Dialog;
import com.sbt.task7mskserver.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByClient_Id(Long id);
    List<Notification> findAllByDialog_Id(Long id);
    void deleteByClient_AndDialog(Client client, Dialog dialog);
    void deleteAllByDialog_Id(Long id);
}
