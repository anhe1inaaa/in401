package ua.com.reactive.kovalova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.kovalova.entity.Message;
import ua.com.reactive.kovalova.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repo;

    public Flux<Message> list()          { return repo.findAll(); }
    public Mono<Message> get(Long id)    { return repo.findById(id); }
    public Mono<Message> create(Message m){ return repo.save(m); }
    public Mono<Message> update(Long id, Message m){
        return repo.findById(id)
                .flatMap(ex -> { ex.setData(m.getData()); return repo.save(ex); });
    }
    public Mono<Void> delete(Long id)    { return repo.deleteById(id); }
}