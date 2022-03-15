package com.property.emlakburada.model;;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;
    private LocalDateTime sentAt;
    @OneToOne(fetch = FetchType.EAGER)
    private Person sentFrom;
    @OneToOne(fetch = FetchType.EAGER)
    private Person sentTo;
}
