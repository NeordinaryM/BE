package neordinaryHackathon.neordinaryHackathon.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class LetterRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class letterDto {
        @Size(min = 1, max = 50)
        @NotNull
        private String writer;

        @Size(min = 1, max = 3000)
        @NotNull
        private String content;
    }
}
