package neordinaryHackathon.neordinaryHackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.dto.LetterRequestDTO;
import neordinaryHackathon.neordinaryHackathon.dto.LetterResponseDTO;
import neordinaryHackathon.neordinaryHackathon.service.LetterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/letter")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;

    @Operation(summary = "편지 생성 API")
    @PostMapping
    public BaseResponse<LetterResponseDTO.letterDto> createLetter(
            @RequestParam String nickname,
            @RequestBody LetterRequestDTO.letterDto letterRequestDTO) {
        LetterResponseDTO.letterDto letterDto = letterService.createLetter(nickname, letterRequestDTO);
        return BaseResponse.onSuccess(letterDto);
    }

    @Operation(summary = "편지 전체 조회 API")
    @GetMapping("/{roomId}")
    public BaseResponse<List<LetterResponseDTO.letterListDto>> letterList(
            @PathVariable Long roomId
    ) {

        List<LetterResponseDTO.letterListDto> letterListDtos = letterService.letterList(roomId);
        return BaseResponse.onSuccess(letterListDtos);
    }

    @Operation(summary = "편지 상세 조회 API")
    @GetMapping("/{letterId}")
    public BaseResponse<LetterResponseDTO.letterDetailDto> letterDetail(
            @PathVariable Long letterId
    ) {
        LetterResponseDTO.letterDetailDto letterDetailDto= letterService.letterDetail(letterId);
        return BaseResponse.onSuccess(letterDetailDto);
    }

    @Operation(summary = "편지 수정")
    @PatchMapping(value = "/edit/{letterId}")
    public BaseResponse<Void> editLetter(
            @PathVariable("letterId") Long letterId,
            @RequestBody String content
    ) {
        letterService.editLetter(letterId, content);
        return BaseResponse.onSuccess(null);
    }

    @Operation(summary = "편지 삭제")
    @DeleteMapping(value = "delete/{letterId}")
    public BaseResponse<Void> deletePet(
            @PathVariable("letterId") Long letterId
    ) {
        letterService.deleteLetter(letterId);
        return BaseResponse.onSuccess(null);
    }
}