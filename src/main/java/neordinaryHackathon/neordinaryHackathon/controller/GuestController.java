package neordinaryHackathon.neordinaryHackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.converter.GuestConverter;
import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import neordinaryHackathon.neordinaryHackathon.dto.GuestRequestDTO;
import neordinaryHackathon.neordinaryHackathon.dto.GuestResponseDTO;
import neordinaryHackathon.neordinaryHackathon.service.GuestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class GuestController {

    private final GuestService guestService;

    @Operation(description = "guest 입장 API")
    @PostMapping("/guests/join")
    public BaseResponse<?> joinRoom(@RequestBody GuestRequestDTO.CreateGuestDTO guestRequestDTO) {
        Guest guest = guestService.joinRoom(guestRequestDTO);
        return BaseResponse.onSuccess(GuestConverter.toJoinGuestResultDTO(guest));
    }
}
