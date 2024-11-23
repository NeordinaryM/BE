package neordinaryHackathon.neordinaryHackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.dto.MemberResponseDTO;
import neordinaryHackathon.neordinaryHackathon.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 API")
    @PostMapping("/signup")
    public BaseResponse<MemberResponseDTO.signupDto> signUp(@RequestBody String nickname) {
        MemberResponseDTO.signupDto signupDTO = memberService.signup(nickname);
        return BaseResponse.onSuccess(signupDTO);
    }
}
