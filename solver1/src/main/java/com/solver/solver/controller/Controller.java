package com.solver.solver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/chat")
public class Controller {
    @Autowired
    private OllamaChatModel chatModel;

    @PostMapping("")
    public ResponseEntity<?> postMethodName(@RequestBody String entity) {
        ChatResponse response = chatModel.call(
                new Prompt(
                        "List 3 countries in Europe",
                        OllamaChatOptions.builder()
                                .model("gpt-oss:latest")
                                .thinkHigh()
                                .build()));
        String thinking = response.getResult().getMetadata().get("thinking");
        String answer = response.getResult().getOutput().getText();
        return ResponseEntity.status(HttpStatus.OK).body(thinking+"/n"+answer);
    }

}
