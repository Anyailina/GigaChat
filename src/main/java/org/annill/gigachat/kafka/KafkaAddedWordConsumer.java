package org.annill.gigachat.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.annill.gigachat.service.AiRequestService;
import org.annill.gigachat.utils.Utils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaAddedWordConsumer {
    private final Utils utils;
    private final String topicName = "addWord";
    private final AiRequestService aiRequestService;

    @KafkaListener(topics = topicName)
    public void listen(String stringJson) throws JsonProcessingException {
        WordTranslation wordTranslation = utils.getObjectMapper().readValue(stringJson, WordTranslation.class);
        aiRequestService.getImage(wordTranslation.getWord());
    }
}

