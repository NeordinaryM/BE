package neordinaryHackathon.neordinaryHackathon.converter;

import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import neordinaryHackathon.neordinaryHackathon.dto.GuestResponseDTO;

public class GuestConverter {

    public static GuestResponseDTO.JoinGuestResultDTO toJoinGuestResultDTO(Guest guest) {
        return GuestResponseDTO.JoinGuestResultDTO.builder()
                .guestId(guest.getGuestId())
                .build();
    }
}
