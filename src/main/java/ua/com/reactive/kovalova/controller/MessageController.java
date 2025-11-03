package ua.com.reactive.kovalova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.kovalova.entity.Message;
import ua.com.reactive.kovalova.service.MessageService;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @GetMapping
    public Flux<Message> all() { return service.list(); }

    @GetMapping("/{id}")
    public Mono<Message> one(@PathVariable Long id) { return service.get(id); }

    @PostMapping
    public Mono<Message> create(@RequestBody Message m) { return service.create(m); }

    @PutMapping("/{id}")
    public Mono<Message> update(@PathVariable Long id, @RequestBody Message m) { return service.update(id, m); }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id)

}