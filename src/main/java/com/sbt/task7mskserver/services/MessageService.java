package com.sbt.task7mskserver.services;

import com.sbt.task7mskserver.dto.MessageDTO;
import com.sbt.task7mskserver.models.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageDTO messageDTO);

    List<Message> getMessagesByDialogId(Long dialogId, Long clientId);
}
