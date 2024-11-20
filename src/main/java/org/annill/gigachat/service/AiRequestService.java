package org.annill.gigachat.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.annill.gigachat.dto.impl.RequestImageAiDto;
import org.annill.gigachat.feignClient.ExternalAi;
import org.annill.gigachat.dto.MessageDto;
import org.annill.gigachat.dto.impl.RequestTextAiDto;
import org.annill.gigachat.enums.Model;
import org.annill.gigachat.enums.Role;
import org.annill.gigachat.feignClient.ExternalDownloadImage;
import org.annill.gigachat.parsing.ChatCompletion;
import org.annill.gigachat.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiRequestService {
    private final AiTokenService aiTokenService;
    private final ExternalAi externalAiApi;
    private final ExternalDownloadImage externalDownloadImage;
    private final Utils utils;
    @Value("${prompt.translate}")
    private String promptTranslateText;

    @Value("${prompt.image}")
    private String promptImage;


    @SneakyThrows
    public String getTextAnswer(String word) {
        String token = aiTokenService.getTokenService();
        String requestString = String.format(promptTranslateText, word);

        List<MessageDto> messages = List.of(new MessageDto(requestString, Role.user));
        RequestTextAiDto requestAi = new RequestTextAiDto(Model.GigaChat, messages, false, 0);

        String answerFromAi = externalAiApi.getAnswer(requestAi, token);
        ChatCompletion chatCompletion = utils.getObjectMapper().readValue(answerFromAi, ChatCompletion.class);
        ChatCompletion.Choice choice = chatCompletion.getChoices()
                .stream()
                .findFirst()
                .orElseThrow();

        return choice.getMessage().getContent();
    }

    @SneakyThrows
    public byte[] getImage(String word) {
        String token = aiTokenService.getTokenService();
        String requestString = promptImage + word;
        List<MessageDto> messages = List.of(new MessageDto(requestString, Role.user));
        RequestImageAiDto requestAi = new RequestImageAiDto(Model.GigaChat, messages, "auto");
        String answerFromAi = externalAiApi.getAnswer(requestAi, token);
        String teg = getTegForDownloading(answerFromAi);

        ByteArrayResource byteArray  = externalDownloadImage.getImage(token,teg).getBody();
        return Optional.ofNullable(byteArray)
                .map(ByteArrayResource::getByteArray)
                .orElse(null);
    }

    @SneakyThrows
    private String getTegForDownloading(String answerFromAi){
        ChatCompletion response = utils.getObjectMapper().readValue(answerFromAi, ChatCompletion.class);
        ChatCompletion.Message message = response.getChoices().get(0).getMessage();
        String teg ="";
        if (message != null && message.getContent() != null) {
            String content = message.getContent();
            int startIndex = content.indexOf("\"") + 1;
            int endIndex = content.indexOf("\"", startIndex);
            teg = content.substring(startIndex, endIndex);
        }
        return teg;
    }
}
