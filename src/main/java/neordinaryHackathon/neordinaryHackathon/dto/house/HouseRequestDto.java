package neordinaryHackathon.neordinaryHackathon.dto.house;

import lombok.Getter;

import java.util.List;

public class HouseRequestDto {
    @Getter
    public static class getHouses {
        String nickname; // 하우스를 조회할 닉네임
    }
}
