package com.sbt.task7mskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDialogDTO {
    @NonNull
    private Long clientFromId;
    @NonNull
    private Long clientToId;
}
