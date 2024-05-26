package com.example.demoollama;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ChatController{

    private final OllamaChatClient ollamaChatClient;

    @Autowired
    public ChatController(OllamaChatClient ollamaChatClient) {
        this.ollamaChatClient = ollamaChatClient;
    }


    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "Conte uma piada em português") String message) {
        return Map.of("generation", ollamaChatClient.call(message));
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return ollamaChatClient.stream(prompt);
    }
}
