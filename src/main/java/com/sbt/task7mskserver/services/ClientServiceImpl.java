package com.sbt.task7mskserver.services;

import com.sbt.task7mskserver.dto.NickNameDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Dialog;
import com.sbt.task7mskserver.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    @Override
    public Long registerNewClient(NickNameDTO nickNameDTO) {
        Client client = new Client(nickNameDTO.getNickname());
        client = clientRepository.save(client);
        return client.getId();
    }

    @Override
    public List<Long> getAllDialogsId(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty())
            return new ArrayList<>(); // throw NotFoundException
        List<Long> IdList = new ArrayList<>();
        for (Dialog dialog : client.get().getDialogsList())
            IdList.add(dialog.getId());
        return IdList;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
