package com.filRouge.filRouge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="channel")
//@JsonSerialize(using = CustomerSerializer.class)

public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 200)
    private String description;

    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime localDateTime;


    @Column(nullable=true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Message> messages;



}
