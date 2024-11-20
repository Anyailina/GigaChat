package org.annill.gigachat.dto.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.annill.gigachat.dto.MessageDto;
import org.annill.gigachat.dto.RequestAiDto;
import org.annill.gigachat.enums.Model;

import java.util.List;

public class RequestImageAiDto extends RequestAiDto {
    @JsonProperty(value = "function_call")
    private final String functionCall;

    public RequestImageAiDto(Model model, List<MessageDto> messages, String functionCall) {
        super(model, messages);
        this.functionCall = functionCall;
    }
}
