package neordinaryHackathon.neordinaryHackathon.converter;

import neordinaryHackathon.neordinaryHackathon.domain.Member;

public class MemberConverter {
    public static Member toMember(String nickName){
        return Member.builder()
                .name(nickName)
                .build();
    }
}
