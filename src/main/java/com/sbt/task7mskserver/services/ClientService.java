package com.sbt.task7mskserver.services;

import com.sbt.task7mskserver.dto.NickNameDTO;
import com.sbt.task7mskserver.models.Client;

import java.util.List;

public interface ClientService {
    Long registerNewClient(NickNameDTO nickNameDTO);

    List<Long> getAllDialogsId(Long id);

    List<Client> getAllClients();
}
