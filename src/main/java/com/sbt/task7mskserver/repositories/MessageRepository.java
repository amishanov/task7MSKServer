package com.sbt.task7mskserver.repositories;

import com.sbt.task7mskserver.models.Dialog;
import com.sbt.task7mskserver.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteAllByDialog_Id(Long id);
    List<Message> findAllByDialog(Dialog dialog);
}
