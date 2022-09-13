package com.filRouge.filRouge.repository;

import com.filRouge.filRouge.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    // find channel with id 1
    Optional<Channel> findById(Long id);
    Channel save(Channel channel);

}
