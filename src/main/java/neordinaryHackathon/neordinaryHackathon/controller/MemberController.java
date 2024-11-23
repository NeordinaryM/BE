package neordinaryHackathon.neordinaryHackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 API")
    @PostMapping("/signup")
    public BaseResponse<String> signUp(@RequestBody String nickname) {
        //닉네임 중복 확인
        memberService.signup(nickname);
        return BaseResponse.onSuccess("회원가입에 성공하였습니다.");
    }
}
