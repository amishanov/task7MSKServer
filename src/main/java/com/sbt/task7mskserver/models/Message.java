package com.sbt.task7mskserver.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Dialog dialog;
    private String text;
    private String nickname;
    public Message(Dialog dialog, String text, String nickname) {
        this.dialog = dialog;
        this.text = text;
        this.nickname = nickname;
    }
}
