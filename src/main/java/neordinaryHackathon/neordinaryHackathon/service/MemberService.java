package neordinaryHackathon.neordinaryHackathon.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.MemberHandler;
import neordinaryHackathon.neordinaryHackathon.converter.MemberConverter;
import neordinaryHackathon.neordinaryHackathon.domain.Member;
import neordinaryHackathon.neordinaryHackathon.dto.MemberResponseDTO;
import neordinaryHackathon.neordinaryHackathon.repository.MemberRepository;
import org.springframework.stereotype.Service;

import static neordinaryHackathon.neordinaryHackathon.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND;
import static neordinaryHackathon.neordinaryHackathon.apiPayload.code.status.ErrorStatus.NICKNAME_ALREADY_EXIST;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDTO.loginDto login(String nickname) {
        Member member = memberRepository.findByName(nickname)
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));

        return new MemberResponseDTO.loginDto(member.getMemberId(), member.getCreatedAt());
    }

    public MemberResponseDTO.signupDto signup(String nickname) {
        // 닉네임 중복 확인
        if (memberRepository.findByName(nickname).isPresent()) {
            throw new MemberHandler(NICKNAME_ALREADY_EXIST);
        }

        // 멤버 생성
        Member member = MemberConverter.toMember(nickname);
        memberRepository.save(member);

        return MemberResponseDTO.signupDto.builder()
                .memberId(member.getMemberId())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
