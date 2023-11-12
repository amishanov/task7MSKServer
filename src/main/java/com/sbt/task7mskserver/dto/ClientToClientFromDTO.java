package com.sbt.task7mskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class ClientToClientFromDTO {
    @Positive
    @NotNull
    private Long clientFromId;
    @Positive
    @NotNull
    private Long clientToId;
}
