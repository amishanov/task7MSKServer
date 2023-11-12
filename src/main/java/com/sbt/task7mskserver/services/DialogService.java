package com.sbt.task7mskserver.services;

import com.sbt.task7mskserver.dto.ClientToClientFromDTO;

public interface DialogService {
    Long startDialog(ClientToClientFromDTO clientToClientFromDTO);

    void stopDialog(Long dialogId);
}
