package org.annill.gigachat.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.annill.gigachat.service.AiRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai/request")
@AllArgsConstructor
@Slf4j
public class AiRequestController {
    private final AiRequestService aiRequestService;

    @SneakyThrows
    @PostMapping("/text")
    public String aiTextRequest(@RequestBody String word) {
        return aiRequestService.getTextAnswer(word);
    }

    @GetMapping("/image")
    @CrossOrigin
    public byte[] aiImageRequest() {
        return aiRequestService.getImage("cat");
    }
}
