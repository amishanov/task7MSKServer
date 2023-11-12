package com.sbt.task7mskserver.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Dialog dialog;
    @NotBlank
    private String text;
    @NotBlank
    private String nickname;
    public Message(Dialog dialog, String text, String nickname) {
        this.dialog = dialog;
        this.text = text;
        this.nickname = nickname;
    }
}
