package com.sbt.task7mskserver.services;

import com.sbt.task7mskserver.dto.ClientToClientFromDTO;
import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Dialog;
import com.sbt.task7mskserver.repositories.ClientRepository;
import com.sbt.task7mskserver.repositories.DialogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class DialogServiceImpl implements DialogService {
    private DialogRepository dialogRepository;
    private ClientRepository clientRepository;

    /**
     * Метод производит проверку наличия клиентов в системе и создание диалога для них
     *
     * @param clientToClientFromDTO id клиента-получателя и id клиента-отправителя
     * @return ID диалога в случае создания или -1L если кого-то из клиентов нет в системе и диалог не был создан
     */
    @Override
    public Long startDialog(ClientToClientFromDTO clientToClientFromDTO) {
        Optional<Client> clientFrom = clientRepository.findById(clientToClientFromDTO.getClientFromId());
        Optional<Client> clientTo = clientRepository.findById(clientToClientFromDTO.getClientToId());
        if (clientFrom.isEmpty() || clientTo.isEmpty())
            return -1L;
        Set<Client> clientSet = new HashSet<>();
        clientSet.add(clientFrom.get());
        clientSet.add(clientTo.get());
        Dialog dialog = new Dialog();
        dialog.setClientSet(clientSet);
        dialog = dialogRepository.save(dialog);
        return dialog.getId();
    }

    @Override
    public void stopDialog(Long dialogId) {
        if (dialogRepository.existsById(dialogId))
            dialogRepository.deleteById(dialogId);
    }
}
