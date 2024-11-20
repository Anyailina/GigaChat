package org.annill.gigachat.dto.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.annill.gigachat.dto.MessageDto;
import org.annill.gigachat.dto.RequestAiDto;
import org.annill.gigachat.enums.Model;

import java.util.List;

@Getter
public class RequestTextAiDto extends RequestAiDto {

    private final boolean stream;
    @JsonProperty(value = "update_interval")
    private final int updateInterval;

    public RequestTextAiDto(Model model, List<MessageDto> messages, boolean stream, int updateInterval) {
        super(model, messages);
        this.updateInterval = updateInterval;
        this.stream = stream;
    }
}