package com.sbt.task7mskserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private Integer newMessagesCount;
    @ManyToOne
    private Client client;
    @OneToOne
    private Dialog dialog;
}
