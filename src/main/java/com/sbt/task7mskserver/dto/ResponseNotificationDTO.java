package com.sbt.task7mskserver.dto;

import com.sbt.task7mskserver.models.Dialog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNotificationDTO {
    @NonNull
    private Long DialogId;
}
