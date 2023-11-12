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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    @OneToOne
    private Dialog dialog;

    public Notification(Client client, Dialog dialog) {
        this.client = client;
        this.dialog = dialog;
    }
}
