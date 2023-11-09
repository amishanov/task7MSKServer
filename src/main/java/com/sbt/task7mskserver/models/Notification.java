package com.sbt.task7mskserver.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
//    private Integer newMessagesCount; Можно добавить, но сложно логику под это писать
//    (работа с существующими уведомлениями + создание новых)
    @ManyToOne
    private Client client;
    @OneToOne
    private Dialog dialog;

    public Notification(Client client, Dialog dialog) {
        this.client = client;
        this.dialog = dialog;
    }
}
