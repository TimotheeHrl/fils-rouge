package com.filRouge.filRouge.repository;

import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository  extends JpaRepository<Message, Long> {
    Message save(Message message);

}
