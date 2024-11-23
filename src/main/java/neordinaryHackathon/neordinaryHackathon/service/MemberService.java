package neordinaryHackathon.neordinaryHackathon.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.ExceptionHandler;
import neordinaryHackathon.neordinaryHackathon.domain.Member;
import neordinaryHackathon.neordinaryHackathon.repository.MemberRepository;
import org.springframework.stereotype.Service;

import static neordinaryHackathon.neordinaryHackathon.apiPayload.code.status.ErrorStatus.NICKNAME_ALREADY_EXIST;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(String nickname) {
        // 닉네임 중복 확인
        if (memberRepository.findByName(nickname).isPresent()) {
            throw new ExceptionHandler(NICKNAME_ALREADY_EXIST);
        }

        // 새로운 멤버 생성 및 저장
        Member member = Member.builder().name(nickname).build();
        memberRepository.save(member);
    }
}