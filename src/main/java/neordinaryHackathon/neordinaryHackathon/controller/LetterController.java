package neordinaryHackathon.neordinaryHackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.dto.LetterRequestDTO;
import neordinaryHackathon.neordinaryHackathon.dto.LetterResponseDTO;
import neordinaryHackathon.neordinaryHackathon.service.LetterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/letter")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LetterController {

    private final LetterService letterService;

    @Operation(summary = "편지 생성 API")
    @PostMapping
    public BaseResponse<LetterResponseDTO.letterDto> createLetter(@RequestParam String nickname,
                                                        @RequestBody LetterRequestDTO.letterDto letterRequestDTO) {
        LetterResponseDTO.letterDto letterDto = letterService.createLetter(nickname, letterRequestDTO);
        return BaseResponse.onSuccess(letterDto);
    }
}
