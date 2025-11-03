package ua.com.reactive.kovalova.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.kovalova.entity.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {}