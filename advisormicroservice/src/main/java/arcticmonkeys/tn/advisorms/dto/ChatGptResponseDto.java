package arcticmonkeys.tn.advisorms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatGptResponseDto {
    private List<String> choices;
    // other properties and getters/setters omitted for brevity
}