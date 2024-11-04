package org.annill.gigachat.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.annill.gigachat.service.AiRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@AllArgsConstructor
@Slf4j
public class AiRequestController {
    private final AiRequestService aiRequestService;

    @SneakyThrows
    @PostMapping("/ai/request")
    public String sell(@RequestBody String word) {
        return aiRequestService.getAnswer(word);
    }
}
