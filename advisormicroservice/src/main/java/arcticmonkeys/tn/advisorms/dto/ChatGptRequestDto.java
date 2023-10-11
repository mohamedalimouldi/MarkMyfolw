package arcticmonkeys.tn.advisorms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatGptRequestDto {
    private String prompt;
    private String model;
    private Integer maxTokens;
    // other properties and getters/setters omitted for brevity
}