package com.filRouge.filRouge.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.filRouge.filRouge.controller.serialiser.MessageSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="message")
@JsonSerialize(using = MessageSerializer.class)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String text;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime localDateTime;
    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = true)
    private Channel channel;


}

