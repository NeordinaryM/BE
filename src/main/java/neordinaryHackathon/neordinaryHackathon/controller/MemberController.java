package neordinaryHackathon.neordinaryHackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.dto.MemberResponseDTO;
import neordinaryHackathon.neordinaryHackathon.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 API", description = "쌍따옴표를 제외하고 테스트해주세요. (쌍따옴표도 DB에 들어갑니다.)")
    @PostMapping("/signup")
    public BaseResponse<MemberResponseDTO.signupDto> signUp(@RequestBody String nickname) {
        MemberResponseDTO.signupDto signupDTO = memberService.signup(nickname);
        return BaseResponse.onSuccess(signupDTO);
    }

    @Operation(summary = "로그인 API", description = "쌍따옴표를 제외하고 테스트해주세요. (쌍따옴표도 DB에 들어갑니다.)")
    @PostMapping("/login")
    public BaseResponse<MemberResponseDTO.loginDto> login(@RequestBody String nickname) {
        MemberResponseDTO.loginDto loginDto = memberService.login(nickname);
        return BaseResponse.onSuccess(loginDto);
    }
}
