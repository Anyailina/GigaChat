package org.annill.gigachat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.annill.gigachat.enums.Model;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class RequestAiDto {
    private Model model;
    private List<MessageDto> messages;
}
