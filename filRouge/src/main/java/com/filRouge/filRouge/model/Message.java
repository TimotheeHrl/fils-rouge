package com.filRouge.filRouge.model;


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
//@JsonSerialize(using = CustomerSerializer.class)

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String text;

@Column(length = 1000)
    private String avatar;

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

