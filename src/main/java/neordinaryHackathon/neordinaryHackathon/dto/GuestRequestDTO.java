package neordinaryHackathon.neordinaryHackathon.dto;

import lombok.Getter;

public class GuestRequestDTO {

    @Getter
    public static class CreateGuestDTO {
        private Long houseId;
        private String guestName;
    }
}
