package neordinaryHackathon.neordinaryHackathon.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class GuestRequestDTO {

    @Getter
    public static class CreateGuestDTO {
        private Long houseId;
        @NotNull
        @Size(min = 1, max = 50)
        private String guestName;
    }
}
